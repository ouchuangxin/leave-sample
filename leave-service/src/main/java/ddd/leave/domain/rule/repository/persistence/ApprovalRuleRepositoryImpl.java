package ddd.leave.domain.rule.repository.persistence;

import ddd.leave.domain.rule.entity.ApprovalRule;
import ddd.leave.domain.rule.repository.facade.ApprovalRuleRepositoryInterface;
import ddd.leave.domain.rule.repository.mapper.ApprovalRuleMapper;
import ddd.leave.domain.rule.repository.po.ApprovalRulePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApprovalRuleRepositoryImpl implements ApprovalRuleRepositoryInterface {

    @Autowired
    ApprovalRuleMapper ruleMapper;

    @Override
    public int getLeaderMaxLevel(ApprovalRule rule) {
        String personType = rule.getPersonType().getVal();
        String leaveType = rule.getLeaveType().getVal();
        ApprovalRulePO rulePo = ruleMapper.findRule(personType, leaveType, rule.getDuration());
        return rulePo.getMaxLeaderLevel();
    }
}
