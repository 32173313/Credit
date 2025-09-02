package com.reward_project.credit.service.impl;

import com.reward_project.credit.controller.cmd.SendCreditCmd;
import com.reward_project.credit.controller.cmd.UpdateCreditCmd;
import com.reward_project.credit.entity.Credit;
import com.reward_project.credit.entity.CreditRecord;
import com.reward_project.credit.enums.CreditRecordStatusEnum;
import com.reward_project.credit.enums.CreditStatusEnum;
import com.reward_project.credit.exception.CreditDeactiveException;
import com.reward_project.credit.exception.CreditInsufficientException;
import com.reward_project.credit.exception.CreditNotExistException;
import com.reward_project.credit.exception.CreditReductionAndAddCreditRecordException;
import com.reward_project.credit.service.CreditRecordService;
import com.reward_project.credit.service.CreditService;
import com.reward_project.credit.service.SendCreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SendCreditServiceImpl implements SendCreditService {

    @Autowired
    private CreditService creditService;

    @Autowired
    private CreditRecordService creditRecordService;

    Logger logger = LoggerFactory.getLogger(SendCreditServiceImpl.class);

    @Override
    public void sendCredit(SendCreditCmd sendCreditCmd) {

        // 1. 检查credit的状态（active）及数量（大于等于发放数量）
        Credit credit = checkCreditStatusAndStock(sendCreditCmd);

        // 2. 以事务的形式进行 1) 对credit进行扣减 2）插入credit发放流水
        try {
            creditReductionAndAddCreditRecord(sendCreditCmd, credit);
        } catch (Exception e) {
            logger.error(String.format("send credit或插入流水失败，外部业务号为%s", sendCreditCmd.getOutBizNo()));
            throw new CreditReductionAndAddCreditRecordException(String.format("send credit或插入流水失败，外部业务号为%s", sendCreditCmd.getOutBizNo()));
        }

    }

    // 事务中的方法都不能catch异常 否则不会回滚 但事务外部被调用的时候需要catch
    // 如果有要catch异常的方法 则封装两个版本：有异常+catch异常

//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
//    private void creditReductionAndAddCreditRecord(SendCreditCmd sendCreditCmd, Credit credit) {
//        UpdateCreditCmd updateCreditCmd = buildUpdateCreditCmd(sendCreditCmd, credit);
//        creditService.updateCredit(updateCreditCmd);
//
//        CreditRecord creditRecord = buildCreditRecord(sendCreditCmd);
//        creditRecordService.addCreditRecordWithoutException(creditRecord);
//    }

    // 多系统多数据库时 手动把更新的数据换回去
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void creditReductionAndAddCreditRecord(SendCreditCmd sendCreditCmd, Credit credit) {
        UpdateCreditCmd updateCreditCmd = buildUpdateCreditCmd(sendCreditCmd, credit);
        creditService.updateCredit(updateCreditCmd);

        CreditRecord creditRecord = buildCreditRecord(sendCreditCmd);
        try {
            creditRecordService.addCreditRecordWithoutException(creditRecord);
        } catch (Exception e) {
            logger.error(String.format("发subType为%s的credit失败，外部业务号为%s，已退还数量为%d的credit", credit.getSubType(), sendCreditCmd.getOutBizNo(), sendCreditCmd.getPrizeAmount()));
            updateCreditCmd.setStock(updateCreditCmd.getStock() + sendCreditCmd.getPrizeAmount());
            creditService.updateCredit(updateCreditCmd);
        }

    }

    private static CreditRecord buildCreditRecord(SendCreditCmd sendCreditCmd) {
        CreditRecord creditRecord = new CreditRecord();
        creditRecord.setUserId(sendCreditCmd.getUserId());
        creditRecord.setType(sendCreditCmd.getPrizeType());
        creditRecord.setSubType(sendCreditCmd.getPrizeSubType());
        creditRecord.setAmount(sendCreditCmd.getPrizeAmount());
        creditRecord.setStatus(CreditRecordStatusEnum.COMPLETE.getCode());
        creditRecord.setOutBizNo(sendCreditCmd.getOutBizNo());
        creditRecord.setTheme(sendCreditCmd.getTheme());
        return creditRecord;
    }

    private UpdateCreditCmd buildUpdateCreditCmd(SendCreditCmd sendCreditCmd, Credit credit) {
        UpdateCreditCmd updateCreditCmd = new UpdateCreditCmd();
        updateCreditCmd.setType(sendCreditCmd.getPrizeType());
        updateCreditCmd.setSubType(sendCreditCmd.getPrizeSubType());
        updateCreditCmd.setStock(credit.getStock() - sendCreditCmd.getPrizeAmount());
        return updateCreditCmd;
    }

    private Credit checkCreditStatusAndStock(SendCreditCmd sendCreditCmd) {
        // 用加了写锁的方法 防止多个项目同时对数据库的数据进行写操作
        Credit credit = creditService.queryByTypeForUpdate(sendCreditCmd.getPrizeType(), sendCreditCmd.getPrizeSubType());
        if(credit == null) {
            logger.error(String.format("type为%s, subType为%s的奖励不存在", sendCreditCmd.getPrizeType(), sendCreditCmd.getPrizeSubType()));
            throw new CreditNotExistException(String.format("type为%s, subType为%s的奖励不存在", sendCreditCmd.getPrizeType(), sendCreditCmd.getPrizeSubType()));
        }
        if(!credit.getStatus().equals(CreditStatusEnum.ACTIVE.getCode())) {
            logger.error(String.format("type为%s, subType为%s的奖励非法", sendCreditCmd.getPrizeType(), sendCreditCmd.getPrizeSubType()));
            throw new CreditDeactiveException(String.format("type为%s, subType为%s的奖励非法", sendCreditCmd.getPrizeType(), sendCreditCmd.getPrizeSubType()));
        }
        if(credit.getStock() < sendCreditCmd.getPrizeAmount()) {
            logger.error(String.format("type为%s, subType为%s的奖励数量不足，stock为%d", sendCreditCmd.getPrizeType(), sendCreditCmd.getPrizeSubType(), credit.getStock()));
            throw new CreditInsufficientException(String.format("type为%s, subType为%s的奖励数量不足，stock为%d", sendCreditCmd.getPrizeType(), sendCreditCmd.getPrizeSubType(), credit.getStock()));
        }
        return credit;
    }
}
