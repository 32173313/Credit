package com.reward_project.credit.controller.cmd;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendCreditCmd {
    private int userId;
    private String theme;
    // prizeType = credit code
    private String prizeType;
    private String prizeSubType;
    private int prizeAmount;
    private String outBizNo;

}
