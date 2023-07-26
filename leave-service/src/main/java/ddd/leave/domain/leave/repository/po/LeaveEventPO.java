package ddd.leave.domain.leave.repository.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import ddd.leave.domain.leave.event.LeaveEventType;
import lombok.Data;
import java.util.Date;

@Data
@TableName("t_leave_event")
public class LeaveEventPO {
    @TableId
    String id;

    @TableField(typeHandler = MybatisEnumTypeHandler.class)
    LeaveEventType leaveEventType;

    Date timestamp;
    String source;
    String data;
}
