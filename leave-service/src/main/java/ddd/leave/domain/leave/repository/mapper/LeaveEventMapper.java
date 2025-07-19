package ddd.leave.domain.leave.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ddd.leave.domain.leave.repository.po.LeaveEventPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeaveEventMapper extends BaseMapper<LeaveEventPO> {
}
