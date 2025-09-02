package com.reward_project.credit.controller;

import com.reward_project.credit.controller.cmd.SendCreditCmd;
import com.reward_project.credit.controller.vo.BaseVO;
import com.reward_project.credit.exception.CreditDeactiveException;
import com.reward_project.credit.exception.CreditInsufficientException;
import com.reward_project.credit.exception.CreditNotExistException;
import com.reward_project.credit.exception.OutBizNoDuplicateException;
import com.reward_project.credit.service.SendCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class SendCreditController {

    @Autowired
    private SendCreditService sendCreditService;

    @PostMapping("/send")
    public BaseVO sendCredit(@RequestBody SendCreditCmd sendCreditCmd) {
        long start = System.currentTimeMillis();
        long end;
        try {
            sendCreditService.sendCredit(sendCreditCmd);
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(200, end - start, true, null);
        } catch (OutBizNoDuplicateException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(201, end - start, true, e.getMessage());
        } catch (CreditNotExistException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(501, end - start, false, e.getMessage());
        } catch (CreditDeactiveException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(502, end - start, false, e.getMessage());
        } catch (CreditInsufficientException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(503, end - start, false, e.getMessage());
        } catch (Exception e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(500, end - start, false, "金币发放失败，未知异常");
        }
    }
}
