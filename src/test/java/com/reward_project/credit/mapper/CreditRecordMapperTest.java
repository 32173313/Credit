package com.reward_project.credit.mapper;

import com.reward_project.credit.entity.CreditRecord;
import com.reward_project.credit.util.TimeConverterUtil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
// 这个类必须是public的
public class CreditRecordMapperTest {

    @Autowired
    private CreditRecordMapper creditRecordMapper;

    @Test
    public void addCreditRecordTest() {
        CreditRecord creditRecord = new CreditRecord();
        creditRecord.setType("holiday");
        creditRecord.setAmount(1234L);
        creditRecord.setStatus("Active");
        creditRecord.setTheme("musicPlay");
        creditRecord.setOutBizNo("123");
        creditRecord.setUserId(5);
        creditRecordMapper.addCreditRecord(creditRecord);
    }

    @Test
    public void queryByUserIdAndCreditTypeAndDateTest() {
        List<CreditRecord> coins = creditRecordMapper.queryByUserIdAndCreditTypeAndDate(5, "holiday", TimeConverterUtil.setTime(0, 0, 0), TimeConverterUtil.setTime(23, 59, 59));
        Assertions.assertEquals(1, coins.size());
        Assertions.assertEquals("123", coins.get(0).getOutBizNo());
    }

}