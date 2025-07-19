package ddd.leave.domain.leave.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ddd.leave.domain.leave.repository.po.ApprovalInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalInfoMapper extends BaseMapper<ApprovalInfoPO> {
    List<ApprovalInfoPO> queryByLeaveId(String leaveId);
}
