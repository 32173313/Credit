package com.reward_project.credit.mapper;

import com.reward_project.credit.entity.CreditRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CreditRecordMapper {
    void addCreditRecord(CreditRecord creditRecord);
    List<CreditRecord> queryByUserIdAndCreditTypeAndDate(int userId, String type, String subType, Date startTime, Date endTime);
}
