package com.reward_project.credit.controller;

import com.reward_project.credit.controller.converter.CreditRecordVOConverter;
import com.reward_project.credit.controller.vo.BaseVO;
import com.reward_project.credit.controller.vo.CreditRecordPageVO;
import com.reward_project.credit.entity.CreditRecord;
import com.reward_project.credit.service.CreditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/credit/record")
public class CreditRecordController {

    @Autowired
    private CreditRecordService creditRecordService;

    @PostMapping("/add")
    // @RequestBody - 把前端传入的json序列化 转换为对象
    public BaseVO addCreditRecord(@RequestBody CreditRecord creditRecord) {
        long start = System.currentTimeMillis();
        long end;
        try {
            creditRecordService.addCreditRecord(creditRecord);
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(200, end - start, true, null);
        } catch (Exception e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(500, end - start, false, "添加发奖流水失败");
        }
    }

    @GetMapping("/search")
    public CreditRecordPageVO searchByUserIdAndCreditTypeAndDate(int userId, String type, String subType, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date endTime) {
        long start = System.currentTimeMillis();
        long end;
        CreditRecordPageVO creditRecordPageVO = new CreditRecordPageVO();
        try {
            List<CreditRecord> creditRecordList = creditRecordService.queryByUserIdAndCreditTypeAndDate(userId, type, subType, startTime, endTime);
            creditRecordPageVO.setCreditRecordVOList(CreditRecordVOConverter.creditRecordVOList(creditRecordList));
            end = System.currentTimeMillis();
            creditRecordPageVO.setBaseVO(BaseVO.buildBaseVo(200, end - start, true, null));
            return creditRecordPageVO;
        } catch (Exception e) {
            end = System.currentTimeMillis();
            creditRecordPageVO.setBaseVO(BaseVO.buildBaseVo(500, end - start, false, "查询发奖流水失败"));
            return creditRecordPageVO;
        }
    }
}
