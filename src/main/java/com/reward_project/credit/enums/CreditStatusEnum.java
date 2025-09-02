package com.reward_project.credit.enums;

public enum CreditStatusEnum {
    ACTIVE("ACTIVE", "上架"),
    DEACTIVE("DEACTIVE", "下架");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    CreditStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    CreditStatusEnum() {
    }
}


