package com.reward_project.credit.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditRecord {
    private int id;
    private int userId;
    private String type;
    private String subType;
    private long amount;
    private String status;
    // outBizNo用来做幂等 下游发奖也需要保证每个阶段只能发一次 上游插入数据记录也需要保证每个阶段只能发一次
    // 上下游约定做幂等的字段为outBizNo
    private String outBizNo;
    // 上游不同的业务场景都可能会调用此服务发credit
    private String theme;
    private Date prizeTime;
    private Date createTime;
    private Date updateTime;
}
