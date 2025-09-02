package com.reward_project.credit.service;

import com.reward_project.credit.controller.cmd.SendCreditCmd;

public interface SendCreditService {
    void sendCredit(SendCreditCmd sendCreditCmd);
}
