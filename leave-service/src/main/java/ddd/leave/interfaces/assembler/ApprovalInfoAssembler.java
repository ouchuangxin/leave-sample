package ddd.leave.interfaces.assembler;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import ddd.leave.domain.leave.entity.ApprovalInfo;
import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.domain.leave.entity.valueobject.ApprovalType;
import ddd.leave.domain.leave.repository.po.ApprovalInfoPO;
import ddd.leave.interfaces.dto.ApprovalInfoDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ApprovalInfoAssembler {

    public static ApprovalInfo toDO(ApprovalInfoDTO dto){
        ApprovalInfo approvalInfo = new ApprovalInfo();
        if (dto == null) {
            return null;
        }
        approvalInfo.setApprovalInfoId(StringUtils.isBlank(dto.getApprovalInfoId()) ? UUID.randomUUID().toString(): dto.getApprovalInfoId());
        approvalInfo.setApprovalType(ApprovalType.from(dto.getApprovalType()));
        approvalInfo.setMsg(dto.getMsg());
        approvalInfo.setApprover(ApproverAssembler.toDO(dto.getApproverDTO()));
        approvalInfo.setTime(new Date().getTime());
        return approvalInfo;
    }

    public static ApprovalInfoDTO toDTO(ApprovalInfo approvalInfo){
        if (approvalInfo == null) {
            return null;
        }
        ApprovalInfoDTO dto = new ApprovalInfoDTO();
        dto.setApprovalInfoId(approvalInfo.getApprovalInfoId());
        dto.setApprovalType(approvalInfo.getApprovalType() == null ? "": approvalInfo.getApprovalType().getVal());
        dto.setMsg(approvalInfo.getMsg());
        dto.setApproverDTO(ApproverAssembler.toDTO(approvalInfo.getApprover()));
        dto.setTime(approvalInfo.getTime());
        return dto;
    }

    public static List<ApprovalInfoDTO> approvalInfoDTOListFromDO(List<ApprovalInfo> historyApprovalInfos) {
        if (historyApprovalInfos == null) {
            return null;
        }
        return historyApprovalInfos
                .stream()
                .map(approvalInfo -> toDTO(approvalInfo))
                .collect(Collectors.toList());
    }

    public static List<ApprovalInfo> approvalInfoDOListFromDTO(List<ApprovalInfoDTO> historyApprovalInfos) {
        if (historyApprovalInfos == null) {
            return null;
        }
        return historyApprovalInfos
                .stream()
                .map(approvalInfo -> toDO(approvalInfo))
                .collect(Collectors.toList());
    }
}
