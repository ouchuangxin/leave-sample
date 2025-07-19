package ddd.leave.domain.rule.entity;

import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;

@Data
public class ApprovalRule {

    PersonType personType;
    LeaveType leaveType;
    int duration;
    int maxLeaderLevel;

    public static ApprovalRule getByLeave(Leave leave){
        ApprovalRule rule = new ApprovalRule();
        rule.setPersonType(leave.getApplicant().getPersonType());
        rule.setLeaveType(leave.getType());
        rule.setDuration(leave.getDuration());
        return rule;
    }
}
