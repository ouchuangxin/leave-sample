package ddd.leave.domain.leave.event;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public enum  LeaveEventType {
    CREATE_EVENT("create"),
    AGREE_EVENT("agree"),
    REJECT_EVENT("reject"),
    APPROVED_EVENT("approved");
    @EnumValue
    private String val;

    private LeaveEventType(String val) {
        this.val = val;
    }

    public static LeaveEventType from(String val) {
        for (LeaveEventType type : LeaveEventType.values()) {
            if (StringUtils.equals(val, type.val)) {
                return type;
            }
        }
        return LeaveEventType.CREATE_EVENT;
    }
}
