package com.reward_project.credit.controller.cmd;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddCreditCmd {
    // 一般用枚举定义credit的类型
    private String type;
    private String subType;
    private String icon;
    // 奖品来源
    private String source;
    private long stock;
    private String status;

}
