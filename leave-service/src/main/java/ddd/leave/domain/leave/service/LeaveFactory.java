package ddd.leave.domain.leave.service;

import com.alibaba.fastjson.JSON;
import ddd.leave.domain.leave.entity.ApprovalInfo;
import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.domain.leave.entity.valueobject.Applicant;
import ddd.leave.domain.leave.entity.valueobject.Approver;
import ddd.leave.domain.leave.event.LeaveEvent;
import ddd.leave.domain.leave.repository.po.ApprovalInfoPO;
import ddd.leave.domain.leave.repository.po.LeaveEventPO;
import ddd.leave.domain.leave.repository.po.LeavePO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LeaveFactory {

    public LeavePO createLeavePO(Leave leave) {
        LeavePO leavePO = new LeavePO();
        leavePO.setId(UUID.randomUUID().toString());
        leavePO.setApplicantId(leave.getApplicant().getPersonId());
        leavePO.setApplicantName(leave.getApplicant().getPersonName());
        leavePO.setApproverId(leave.getApprover().getPersonId());
        leavePO.setApproverName(leave.getApprover().getPersonName());
        leavePO.setStartTime(leave.getStartTime());
        leavePO.setStatus(leave.getStatus());
        List<ApprovalInfoPO> historyApprovalInfoPOList = approvalInfoPOListFromDO(leave);
        leavePO.setHistoryApprovalInfoPOList(historyApprovalInfoPOList);
        return leavePO;
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
        leave.setApprover(approver);
        leave.setStartTime(leavePO.getStartTime());
        leave.setStatus(leavePO.getStatus());
        List<ApprovalInfo> approvalInfos = getApprovalInfos(leavePO.getHistoryApprovalInfoPOList());
        leave.setHistoryApprovalInfos(approvalInfos);
        return leave;
    }

    public LeaveEventPO createLeaveEventPO(LeaveEvent leaveEvent){
        LeaveEventPO eventPO = new LeaveEventPO();
        eventPO.setLeaveEventType(leaveEvent.getLeaveEventType());
        eventPO.setSource(leaveEvent.getSource());
        eventPO.setTimestamp(leaveEvent.getTimestamp());
        eventPO.setData(JSON.toJSONString(leaveEvent.getData()));
        return eventPO;
    }

    private List<ApprovalInfoPO> approvalInfoPOListFromDO(Leave leave) {
        return leave.getHistoryApprovalInfos()
                .stream()
                .map(approvalInfo -> approvalInfoPOFromDO(approvalInfo))
                .collect(Collectors.toList());
    }

    private ApprovalInfoPO approvalInfoPOFromDO(ApprovalInfo approvalInfo){
        ApprovalInfoPO po = new ApprovalInfoPO();
        po.setApproverId(approvalInfo.getApprover().getPersonId());
        po.setApproverLevel(approvalInfo.getApprover().getLevel());
        po.setApproverName(approvalInfo.getApprover().getPersonName());
        po.setApprovalInfoId(approvalInfo.getApprovalInfoId());
        po.setMsg(approvalInfo.getMsg());
        po.setTime(approvalInfo.getTime());
        return po;
    }

    private ApprovalInfo approvalInfoFromPO(ApprovalInfoPO approvalInfoPO){
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

    private List<ApprovalInfo> getApprovalInfos(List<ApprovalInfoPO> approvalInfoPOList){
        return approvalInfoPOList.stream()
                .map(approvalInfoPO -> approvalInfoFromPO(approvalInfoPO))
                .collect(Collectors.toList());
    }
}
