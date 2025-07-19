package ddd.leave.domain.leave.service;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import ddd.leave.domain.leave.entity.ApprovalInfo;
import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.domain.leave.entity.valueobject.Applicant;
import ddd.leave.domain.leave.entity.valueobject.Approver;
import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.leave.entity.valueobject.Status;
import ddd.leave.domain.leave.event.LeaveEvent;
import ddd.leave.domain.leave.repository.po.ApprovalInfoPO;
import ddd.leave.domain.leave.repository.po.LeaveEventPO;
import ddd.leave.domain.leave.repository.po.LeavePO;
import ddd.leave.interfaces.dto.ApprovalInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LeaveFactory {

    public LeavePO createLeavePO(Leave leave) {
        LeavePO leavePO = new LeavePO();
        leavePO.setId(StringUtils.isBlank(leave.getId()) ? UUID.randomUUID().toString() : leave.getId());
        leavePO.setApplicantId(leave.getApplicant().getPersonId());
        leavePO.setApplicantName(leave.getApplicant().getPersonName());
        leavePO.setApplicantType(leave.getApplicant().getPersonType().getVal());
        leavePO.setApproverId(leave.getApprover().getPersonId());
        leavePO.setApproverName(leave.getApprover().getPersonName());
        leavePO.setStartTime(leave.getStartTime());
        leavePO.setEndTime(leave.getEndTime());
        leavePO.setStatus(leave.getStatus().getVal());
        leavePO.setLeaveType(leave.getType().getVal());
        leavePO.setDuration(leave.getDuration());
        return leavePO;
    }

    public ApprovalInfoPO createApprovalInfoPO(Leave leave) {
        ApprovalInfoPO approvalInfoPO = approvalInfoPOFromDO(leave.getCurrentApprovalInfo());
        approvalInfoPO.setLeaveId(leave.getId());
        approvalInfoPO.setApplicantId(leave.getApplicant().getPersonId());
        return approvalInfoPO;
    }

    public Leave getLeave(LeavePO leavePO) {
        Leave leave = new Leave();
        Applicant applicant = Applicant.builder()
                .personId(leavePO.getApplicantId())
                .personName(leavePO.getApplicantName())
                .build();
        leave.setApplicant(applicant);
        Approver approver = Approver.builder()
                .personId(leavePO.getApproverId())
                .personName(leavePO.getApproverName())
                .build();
        leave.setId(leavePO.getId());
        leave.setType(LeaveType.from(leavePO.getLeaveType()));
        leave.setApprover(approver);
        leave.setStartTime(leavePO.getStartTime());
        leave.setEndTime(leavePO.getEndTime());
        leave.setDuration(leavePO.getDuration());
        leave.setStatus(Status.from(leavePO.getStatus()));
        return leave;
    }

    public LeaveEventPO createLeaveEventPO(LeaveEvent leaveEvent){
        LeaveEventPO eventPO = new LeaveEventPO();
        eventPO.setId(leaveEvent.getId());
        eventPO.setLeaveEventType(leaveEvent.getLeaveEventType().getVal());
        eventPO.setSource(leaveEvent.getSource());
        eventPO.setTimestamp(leaveEvent.getTimestamp());
        eventPO.setData(JSON.toJSONString(leaveEvent.getData()));
        return eventPO;
    }

    public ApprovalInfoPO approvalInfoPOFromDO(ApprovalInfo approvalInfo){
        ApprovalInfoPO po = new ApprovalInfoPO();
        po.setApproverId(approvalInfo.getApprover().getPersonId());
        po.setApproverLevel(approvalInfo.getApprover().getLevel());
        po.setApproverName(approvalInfo.getApprover().getPersonName());
        po.setApprovalInfoId(approvalInfo.getApprovalInfoId());
        po.setMsg(approvalInfo.getMsg());
        po.setTime(approvalInfo.getTime());
        return po;
    }

    public ApprovalInfo approvalInfoFromPO(ApprovalInfoPO approvalInfoPO){
        ApprovalInfo approvalInfo = new ApprovalInfo();
        approvalInfo.setApprovalInfoId(approvalInfoPO.getApprovalInfoId());
        Approver approver = Approver.builder()
                .personId(approvalInfoPO.getApproverId())
                .personName(approvalInfoPO.getApproverName())
                .level(approvalInfoPO.getApproverLevel())
                .build();
        approvalInfo.setApprover(approver);
        approvalInfo.setMsg(approvalInfoPO.getMsg());
        approvalInfo.setTime(approvalInfoPO.getTime());
        return approvalInfo;
    }

    public List<ApprovalInfoPO> approvalInfoPOListFromDO(List<ApprovalInfo> historyApprovalInfos) {
        return historyApprovalInfos
                .stream()
                .map(approvalInfo -> approvalInfoPOFromDO(approvalInfo))
                .collect(Collectors.toList());
    }

    public List<ApprovalInfo> approvalInfoDOListFromPO(List<ApprovalInfoPO> historyApprovalInfos) {
        return historyApprovalInfos
                .stream()
                .map(approvalInfo -> approvalInfoFromPO(approvalInfo))
                .collect(Collectors.toList());
    }
}
