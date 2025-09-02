package com.reward_project.credit.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Credit {
    private int id;
    // 一般用枚举定义credit的类型
    private String type;
    private String subType;
    private String icon;
    // 奖品来源
    private String source;
    private long stock;
    private String status;
    private Date createTime;
    private Date updateTime;

}
