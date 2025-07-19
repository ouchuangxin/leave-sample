package ddd.leave.domain.rule.service;

import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import ddd.leave.domain.rule.entity.ApprovalRule;
import ddd.leave.domain.rule.repository.facade.ApprovalRuleRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalRuleDomainService {

    @Autowired
    ApprovalRuleRepositoryInterface repositoryInterface;

    public int getLeaderMaxLevel(PersonType personType, LeaveType leaveType, int duration) {
        ApprovalRule rule = new ApprovalRule();
        rule.setPersonType(personType);
        rule.setLeaveType(leaveType);
        rule.setDuration(duration);
        return repositoryInterface.getLeaderMaxLevel(rule);
    }
}
