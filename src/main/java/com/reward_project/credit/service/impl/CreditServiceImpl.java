package com.reward_project.credit.service.impl;

import com.reward_project.credit.controller.cmd.AddCreditCmd;
import com.reward_project.credit.controller.cmd.UpdateCreditCmd;
import com.reward_project.credit.entity.Credit;
import com.reward_project.credit.exception.CreditDuplicateException;
import com.reward_project.credit.exception.CreditNotExistException;
import com.reward_project.credit.mapper.CreditMapper;
import com.reward_project.credit.service.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreditServiceImpl implements CreditService {
    
    @Autowired
    private CreditMapper creditMapper;

    Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);

    @Override
    public void addCredit(AddCreditCmd addCreditCmd) {
        Credit credit = buildAddCredit(addCreditCmd);
        // 要把技术异常转换成业务异常 这样用户可以看懂
        try {
            creditMapper.addCredit(credit);
        } catch (DuplicateKeyException e) {
            throw new CreditDuplicateException(String.format("subType为%s的credit已存在，请勿重复添加", addCreditCmd.getSubType()));
        }
    }

    private static Credit buildAddCredit(AddCreditCmd addCreditCmd) {
        Credit credit = new Credit();
        credit.setType(addCreditCmd.getType());
        credit.setSubType(addCreditCmd.getSubType());
        credit.setStatus(addCreditCmd.getStatus());
        credit.setStock(addCreditCmd.getStock());
        credit.setSource(addCreditCmd.getSource());
        credit.setIcon(addCreditCmd.getIcon());
        return credit;
    }

    @Override
    // 为了扩展性和通用性 就算add和update用的参数一样 也要分开建cmd
    public void updateCredit(UpdateCreditCmd updateCreditCmd) {
        Credit credit = queryByType(updateCreditCmd.getType(), updateCreditCmd.getSubType());
        if(credit == null) {
            throw new CreditNotExistException(String.format("要修改的积分%s不存在", updateCreditCmd.getSubType()));
        }

        // 加一步判空 只有传入参数不为空时进行更新 enable只更新一个字段
        if(updateCreditCmd.getIcon() != null) {
            credit.setIcon(updateCreditCmd.getIcon());
        }
        if(updateCreditCmd.getStatus() != null) {
            credit.setStatus(updateCreditCmd.getStatus());
        }
        if(updateCreditCmd.getStock() != null) {
            credit.setStock(updateCreditCmd.getStock());
        }

        try {
            creditMapper.updateCredit(credit);
        } catch (Exception e) {
            logger.error(String.format("子类型为%s的credit更新失败", updateCreditCmd.getSubType()));
        }

    }

    @Override
    public Credit queryByType(String type, String subType) {
        return creditMapper.queryByType(type, subType);
    }

    @Override
    public Credit queryByTypeForUpdate(String type, String subType) {
        return creditMapper.queryByTypeForUpdate(type, subType);
    }

    @Override
    public List<Credit> queryByStatus(String status) {
        return creditMapper.queryByStatus(status);
    }

    @Override
    public List<Credit> queryBySource(String source) {
        return creditMapper.queryBySource(source);
    }
}
