package com.reward_project.credit.mapper;

import com.reward_project.credit.entity.Credit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreditMapper {
    void addCredit(Credit credit);
    void updateCredit(Credit credit);
    Credit queryByType(String type, String subType);
    Credit queryByTypeForUpdate(String type, String subType);
    List<Credit> queryByStatus(String status);
    List<Credit> queryBySource(String source);
}
