package com.reward_project.credit.service;

import com.reward_project.credit.controller.cmd.AddCreditCmd;
import com.reward_project.credit.controller.cmd.UpdateCreditCmd;
import com.reward_project.credit.entity.Credit;

import java.util.List;

public interface CreditService {
    void addCredit(AddCreditCmd addCreditCmd);
    void updateCredit(UpdateCreditCmd updateCreditCmd);
    Credit queryByType(String type, String subType);
    Credit queryByTypeForUpdate(String type, String subType);
    List<Credit> queryByStatus(String status);
    List<Credit> queryBySource(String source);
}
