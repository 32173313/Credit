package com.reward_project.credit.service;

import com.reward_project.credit.entity.CreditRecord;

import java.util.Date;
import java.util.List;

public interface CreditRecordService {
    void addCreditRecord(CreditRecord creditRecord);
    void addCreditRecordWithoutException(CreditRecord creditRecord);
    List<CreditRecord> queryByUserIdAndCreditTypeAndDate(int userId, String type, String subType, Date startTime, Date endTime);
}
