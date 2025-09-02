package com.reward_project.credit.controller;

import com.reward_project.credit.controller.cmd.AddCreditCmd;
import com.reward_project.credit.controller.cmd.UpdateCreditCmd;
import com.reward_project.credit.controller.converter.CreditVOConverter;
import com.reward_project.credit.controller.vo.BaseVO;
import com.reward_project.credit.controller.vo.MultipleCreditPageVO;
import com.reward_project.credit.controller.vo.SingleCreditPageVO;
import com.reward_project.credit.entity.Credit;
import com.reward_project.credit.exception.CreditDuplicateException;
import com.reward_project.credit.exception.CreditNotExistException;
import com.reward_project.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PostMapping("/add")
    public BaseVO addCredit(@RequestBody AddCreditCmd addCreditCmd) {
        long start = System.currentTimeMillis();
        long end;
        try {
            creditService.addCredit(addCreditCmd);
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(200, end - start, true, null);
        } catch (CreditDuplicateException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(501, end - start, false, e.getMessage());
        } catch (Exception e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(500, end - start, false, "金币添加失败，未知异常");
        }
    }

    @PutMapping("/update")
    public BaseVO updateCredit(@RequestBody UpdateCreditCmd updateCreditCmd) {
        long start = System.currentTimeMillis();
        long end;
        try {
            creditService.updateCredit(updateCreditCmd);
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(200, end - start, true, null);
        } catch (CreditNotExistException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(501, end - start, false, e.getMessage());
        } catch (Exception e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(500, end - start, false, "金币更新失败，未知异常");
        }
    }

    @GetMapping("/search/by/code")
    public SingleCreditPageVO searchByCode(String type, String subType) {
        long start = System.currentTimeMillis();
        long end;
        SingleCreditPageVO singleCreditPageVO = new SingleCreditPageVO();
        try {
            Credit credit = creditService.queryByType(type, subType);
            singleCreditPageVO.setCreditVO(CreditVOConverter.convertToVO(credit));
            end = System.currentTimeMillis();
            singleCreditPageVO.setBaseVO(BaseVO.buildBaseVo(200, end - start, true, null));
            return singleCreditPageVO;
        } catch (Exception e) {
            end = System.currentTimeMillis();
            singleCreditPageVO.setBaseVO(BaseVO.buildBaseVo(500, end - start, false, "金币不存在"));
            return singleCreditPageVO;
        }
    }

    @GetMapping("/search/by/status")
    public MultipleCreditPageVO searchByStatus(String status) {
        long start = System.currentTimeMillis();
        long end;
        MultipleCreditPageVO multipleCreditPageVO = new MultipleCreditPageVO();
        try {
            List<Credit> credits = creditService.queryByStatus(status);
            multipleCreditPageVO.setCreditVOList(CreditVOConverter.convertToVoList(credits));
            end = System.currentTimeMillis();
            multipleCreditPageVO.setBaseVO(BaseVO.buildBaseVo(200, end - start, true, null));
            return multipleCreditPageVO;
        } catch (Exception e) {
            end = System.currentTimeMillis();
            multipleCreditPageVO.setBaseVO(BaseVO.buildBaseVo(500, end - start, false, "金币不存在"));
            return multipleCreditPageVO;
        }
    }

    @GetMapping("/search/by/source")
    public MultipleCreditPageVO searchBySource(String source) {
        long start = System.currentTimeMillis();
        long end;
        MultipleCreditPageVO multipleCreditPageVO = new MultipleCreditPageVO();
        try {
            List<Credit> credits = creditService.queryBySource(source);
            multipleCreditPageVO.setCreditVOList(CreditVOConverter.convertToVoList(credits));
            end = System.currentTimeMillis();
            multipleCreditPageVO.setBaseVO(BaseVO.buildBaseVo(200, end - start, true, null));
            return multipleCreditPageVO;
        } catch (Exception e) {
            end = System.currentTimeMillis();
            multipleCreditPageVO.setBaseVO(BaseVO.buildBaseVo(500, end - start, false, "金币不存在"));
            return multipleCreditPageVO;
        }
    }
}
