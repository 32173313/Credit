//package com.reward_project.credit.mapper;
//
//import com.reward_project.credit.entity.Credit;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class CreditMapperTest {
//
//    @Autowired
//    private CreditMapper creditMapper;
//    // 有期待结果 比如查询时 用Assertion验证查询结果
//
//    // @Test是给方法执行的主方法入口
//    @Test
//    public void addCreditTest() {
//        Credit credit = new Credit();
//        credit.setCode("holiday");
//        credit.setIcon("www.music.com");
//        credit.setSource("google");
//        credit.setStatus("Active");
//        credit.setStock(1234L);
//        creditMapper.addCredit(credit);
//    }
//
//    @Test
//    public void updateCreditTest() {
//        Credit credit = creditMapper.queryByType("holiday");
//        credit.setStock(5555L);
//        credit.setCode("holiday");
//        creditMapper.updateCredit(credit);
//    }
//
//    @Test
//    public void queryByTypeTest() {
//        Credit credit = creditMapper.queryByType("holiday");
//        Assertions.assertEquals("holiday", credit.getCode());
//        Assertions.assertEquals("google", credit.getSource());
//    }
//
//    @Test
//    public void queryByStatusTest() {
//        List<Credit> credits = creditMapper.queryByStatus("Active");
//        Assertions.assertEquals(1, credits.size());
//        Assertions.assertEquals("google", credits.get(0).getSource());
//    }
//}