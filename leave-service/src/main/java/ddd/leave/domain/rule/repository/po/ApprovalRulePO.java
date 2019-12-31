package ddd.leave.domain.rule.repository.po;

import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class ApprovalRulePO {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    String id;
    @Enumerated(EnumType.STRING)
    LeaveType leaveType;
    @Enumerated(EnumType.STRING)
    PersonType personType;
    long duration;
    String applicantRoleId;
}
