package ddd.leave.domain.leave.entity.valueobject;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public enum Status {
    APPROVING("approving"), APPROVED("approved"), REJECTED("rejected");
    @EnumValue
    private String val;

    private Status(String val) {
        this.val = val;
    }

    public static Status from(String val) {
        for (Status status : Status.values()) {
            if (StringUtils.equals(val, status.val)) {
                return status;
            }
        }
        return Status.APPROVING;
    }
}
