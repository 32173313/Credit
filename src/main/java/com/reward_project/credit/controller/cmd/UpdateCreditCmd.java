package com.reward_project.credit.controller.cmd;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateCreditCmd {
    private String icon;
    private Long stock;
    private String status;
    private String type;
    private String subType;

}
