package com.reward_project.credit.controller.converter;

import com.reward_project.credit.controller.vo.CreditVO;
import com.reward_project.credit.entity.Credit;

import java.util.ArrayList;
import java.util.List;

public class CreditVOConverter {
    public static CreditVO convertToVO(Credit credit) {
        CreditVO creditVO = new CreditVO();
        creditVO.setId(credit.getId());
        creditVO.setType(credit.getType());
        creditVO.setSubType(creditVO.getSubType());
        creditVO.setIcon(credit.getIcon());
        creditVO.setSource(credit.getSource());
        creditVO.setStatus(credit.getStatus());
        creditVO.setStock(credit.getStock());
        creditVO.setCreateTime(credit.getCreateTime());
        creditVO.setUpdateTime(credit.getUpdateTime());
        return creditVO;
    }

    public static List<CreditVO> convertToVoList(List<Credit> creditList) {
        List<CreditVO> creditVOList = new ArrayList<>();
        for (Credit credit : creditList) {
            CreditVO creditVO = convertToVO(credit);
            creditVOList.add(creditVO);
        }
        return creditVOList;
    }
}
