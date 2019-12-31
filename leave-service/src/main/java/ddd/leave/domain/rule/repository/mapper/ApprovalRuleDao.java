package ddd.leave.domain.rule.repository.mapper;

import ddd.leave.domain.rule.entity.ApprovalRule;
import ddd.leave.domain.rule.repository.po.ApprovalRulePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApprovalRuleDao extends JpaRepository<ApprovalRulePO, String> {

    @Query(value = "select r from ApprovalRulePO r where r.applicantRoleId=?1 and r.leaveType=?2 and duration=?3")
    ApprovalRule findRule(String applicantRoleId, String leaveType, long duration);
}
