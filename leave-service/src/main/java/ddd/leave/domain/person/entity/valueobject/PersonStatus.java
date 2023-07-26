package ddd.leave.domain.person.entity.valueobject;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import ddd.leave.domain.leave.entity.valueobject.Status;

public enum PersonStatus {

    ENABLE("enabled"), DISABLE("disabled");

    @EnumValue
    private String val;

    private PersonStatus(String val) {
        this.val = val;
    }

    public static PersonStatus from(String val) {
        for (PersonStatus status : PersonStatus.values()) {
            if (StringUtils.equals(val, status.val)) {
                return status;
            }
        }
        return PersonStatus.ENABLE;
    }
}
