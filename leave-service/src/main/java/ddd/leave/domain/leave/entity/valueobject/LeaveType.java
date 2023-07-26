package ddd.leave.domain.leave.entity.valueobject;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public enum LeaveType {
    Internal("internal"), External("external"), Official("official");

    @EnumValue
    private String val;

    private LeaveType(String val) {
        this.val = val;
    }

    public static LeaveType from(String val) {
        for (LeaveType type : LeaveType.values()) {
            if (StringUtils.equals(val, type.val)) {
                return type;
            }
        }
        return LeaveType.Internal;
    }

    public String getVal() {
        return val;
    }
}
