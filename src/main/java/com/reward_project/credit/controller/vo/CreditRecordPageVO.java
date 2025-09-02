package com.reward_project.credit.controller.vo;

import java.util.List;

public class CreditRecordPageVO {
    private List<CreditRecordVO> creditRecordVOList;
    private BaseVO baseVO;

    public List<CreditRecordVO> getCreditRecordVOList() {
        return creditRecordVOList;
    }

    public void setCreditRecordVOList(List<CreditRecordVO> creditRecordVOList) {
        this.creditRecordVOList = creditRecordVOList;
    }

    public BaseVO getBaseVO() {
        return baseVO;
    }

    public void setBaseVO(BaseVO baseVO) {
        this.baseVO = baseVO;
    }

    public CreditRecordPageVO(List<CreditRecordVO> creditRecordVOList, BaseVO baseVO) {
        this.creditRecordVOList = creditRecordVOList;
        this.baseVO = baseVO;
    }

    public CreditRecordPageVO() {
    }

    @Override
    public String toString() {
        return "CreditRecordPageVO{" +
                "creditRecordVOList=" + creditRecordVOList +
                ", baseVO=" + baseVO +
                '}';
    }
}
