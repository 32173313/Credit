package com.reward_project.credit.controller.vo;

public class SingleCreditPageVO {
    private CreditVO creditVO;
    private BaseVO baseVO;

    public CreditVO getCreditVO() {
        return creditVO;
    }

    public void setCreditVO(CreditVO creditVO) {
        this.creditVO = creditVO;
    }

    public BaseVO getBaseVO() {
        return baseVO;
    }

    public void setBaseVO(BaseVO baseVO) {
        this.baseVO = baseVO;
    }

    public SingleCreditPageVO(CreditVO creditVO, BaseVO baseVO) {
        this.creditVO = creditVO;
        this.baseVO = baseVO;
    }

    public SingleCreditPageVO() {
    }

    @Override
    public String toString() {
        return "SingleCreditPageVO{" +
                "creditVO=" + creditVO +
                ", baseVO=" + baseVO +
                '}';
    }
}
