package ddd.leave.domain.leave.repository.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ApprovalInfoPO {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    String approvalInfoId;
    String leaveId;
    String applicantId;
    String approverId;
    int approverLevel;
    String approverName;
    String msg;
    long time;

}
