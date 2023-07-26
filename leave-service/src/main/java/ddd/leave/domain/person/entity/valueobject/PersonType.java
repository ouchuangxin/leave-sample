package ddd.leave.domain.person.entity.valueobject;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import ddd.leave.domain.leave.entity.valueobject.Status;

public enum PersonType {

    GRASS("grass"), MEDIUM("medium"), SENIOR("senior");
    @EnumValue
    private String val;

    private PersonType(String val) {
        this.val = val;
    }

    public static PersonType from(String val) {
        for (PersonType status : PersonType.values()) {
            if (StringUtils.equals(val, status.val)) {
                return status;
            }
        }
        return PersonType.GRASS;
    }
}
