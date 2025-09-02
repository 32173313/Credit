package com.reward_project.credit.controller.converter;

import com.reward_project.credit.controller.vo.CreditRecordVO;
import com.reward_project.credit.entity.CreditRecord;

import java.util.ArrayList;
import java.util.List;

public class CreditRecordVOConverter {
    public static CreditRecordVO convertToVO (CreditRecord creditRecord) {
        CreditRecordVO creditRecordVO = new CreditRecordVO();
        creditRecordVO.setId(creditRecord.getId());
        creditRecordVO.setCreditType(creditRecord.getType());
        creditRecordVO.setAmount(creditRecord.getAmount());
        creditRecordVO.setStatus(creditRecord.getStatus());
        creditRecordVO.setTheme(creditRecord.getTheme());
        creditRecordVO.setOutBizNo(creditRecord.getOutBizNo());
        creditRecordVO.setPrizeTime(creditRecord.getPrizeTime());
        creditRecordVO.setCreateTime(creditRecord.getCreateTime());
        creditRecordVO.setUpdateTime(creditRecord.getUpdateTime());
        return creditRecordVO;
    }

    public static List<CreditRecordVO> creditRecordVOList(List<CreditRecord> creditRecordList) {
        List<CreditRecordVO> creditRecordVOList = new ArrayList<>();
        for(CreditRecord creditRecord : creditRecordList) {
            CreditRecordVO creditRecordVO = convertToVO(creditRecord);
            creditRecordVOList.add(creditRecordVO);
        }
        return creditRecordVOList;
    }
}
