package ddd.leave.interfaces.assembler;

import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.leave.entity.valueobject.Status;
import ddd.leave.infrastructure.util.DateUtil;
import ddd.leave.interfaces.dto.LeaveDTO;

import java.text.ParseException;

public class LeaveAssembler {

    public static LeaveDTO toDTO(Leave leave){
        LeaveDTO dto = new LeaveDTO();
        dto.setLeaveId(leave.getId());
        dto.setLeaveType(leave.getType().toString());
        dto.setStatus(leave.getStatus().toString());
        dto.setStartTime(DateUtil.formatDateTime(leave.getStartTime()));
        dto.setEndTime(DateUtil.formatDateTime(leave.getEndTime()));
        dto.setCurrentApprovalInfoDTO(ApprovalInfoAssembler.toDTO(leave.getCurrentApprovalInfo()));
        dto.setHistoryApprovalInfoDTOList(ApprovalInfoAssembler.approvalInfoDTOListFromDO(leave.getHistoryApprovalInfos()));
        dto.setDuration(leave.getDuration());
        return dto;
    }

    public static Leave toDO(LeaveDTO dto) throws ParseException {
        Leave leave = new Leave();
        leave.setId(dto.getLeaveId());
        leave.setDuration(dto.getDuration());
        leave.setStartTime(DateUtil.parseDateTime(dto.getStartTime()));
        leave.setEndTime(DateUtil.parseDateTime(dto.getEndTime()));
        leave.setType(LeaveType.from(dto.getLeaveType()));
        leave.setStatus(Status.from(dto.getStatus()));
        leave.setApplicant(ApplicantAssembler.toDO(dto.getApplicantDTO()));
        leave.setApprover(ApproverAssembler.toDO(dto.getApproverDTO()));
        leave.setCurrentApprovalInfo(ApprovalInfoAssembler.toDO(dto.getCurrentApprovalInfoDTO()));
        leave.setHistoryApprovalInfos(ApprovalInfoAssembler.approvalInfoDOListFromDTO(dto.getHistoryApprovalInfoDTOList()));
        return leave;
    }

}
