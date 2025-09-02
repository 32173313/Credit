package com.reward_project.credit.service.impl;

import com.reward_project.credit.entity.CreditRecord;
import com.reward_project.credit.exception.OutBizNoDuplicateException;
import com.reward_project.credit.mapper.CreditRecordMapper;
import com.reward_project.credit.service.CreditRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CreditRecordServiceImpl implements CreditRecordService {

    @Autowired
    private CreditRecordMapper creditRecordMapper;

    Logger logger = LoggerFactory.getLogger(CreditRecordServiceImpl.class);

    @Override
    public void addCreditRecord(CreditRecord creditRecord) {
        try {
            addCreditRecordWithoutException(creditRecord);
        } catch (DuplicateKeyException e) {
            logger.error(String.format("该记录已存在，外部业务号为%s", creditRecord.getOutBizNo()));
            throw new OutBizNoDuplicateException(String.format("该记录已存在，外部业务号为%s", creditRecord.getOutBizNo()));
        } catch (Exception e) {
            logger.error(String.format("该记录插入失败，外部业务号为%s，未知异常", creditRecord.getOutBizNo()));
        }
    }

    @Override
    public void addCreditRecordWithoutException(CreditRecord creditRecord) {
        creditRecordMapper.addCreditRecord(creditRecord);
    }

    @Override
    public List<CreditRecord> queryByUserIdAndCreditTypeAndDate(int userId, String type, String subType, Date startTime, Date endTime) {
        return creditRecordMapper.queryByUserIdAndCreditTypeAndDate(userId, type, subType, startTime, endTime);
    }
}
