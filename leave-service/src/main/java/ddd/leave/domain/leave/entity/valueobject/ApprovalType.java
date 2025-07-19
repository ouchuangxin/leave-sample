package ddd.leave.domain.leave.entity.valueobject;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public enum  ApprovalType {
    AGREE("agree"), REJECT("reject");

    private String val;
    private ApprovalType(String val) {
        this.val = val;
    }

    public static ApprovalType from(String val) {
        for (ApprovalType type : ApprovalType.values()) {
            if (StringUtils.equals(val, type.val)) {
                return type;
            }
        }
        return ApprovalType.AGREE;
    }

    public String getVal() {
        return val;
    }
}
