package com.reward_project.credit.controller.vo;

import java.util.List;

public class MultipleCreditPageVO {
    private List<CreditVO> creditVOList;
    private BaseVO baseVO;

    public List<CreditVO> getCreditVOList() {
        return creditVOList;
    }

    public void setCreditVOList(List<CreditVO> creditVOList) {
        this.creditVOList = creditVOList;
    }

    public BaseVO getBaseVO() {
        return baseVO;
    }

    public void setBaseVO(BaseVO baseVO) {
        this.baseVO = baseVO;
    }

    public MultipleCreditPageVO(List<CreditVO> creditVOList, BaseVO baseVO) {
        this.creditVOList = creditVOList;
        this.baseVO = baseVO;
    }

    public MultipleCreditPageVO() {
    }

    @Override
    public String toString() {
        return "MultipleCreditPageVO{" +
                "creditVOList=" + creditVOList +
                ", baseVO=" + baseVO +
                '}';
    }
}
