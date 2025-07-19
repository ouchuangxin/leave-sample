package ddd.leave.interfaces.dto;

import lombok.Data;

@Data
public class ApprovalInfoDTO {

    String approvalInfoId;
    ApproverDTO approverDTO;
    String approvalType;
    String msg;
    long time;
}
