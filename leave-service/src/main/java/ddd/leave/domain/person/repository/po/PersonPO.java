package ddd.leave.domain.person.repository.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import ddd.leave.domain.person.entity.valueobject.PersonStatus;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_person")
public class PersonPO {
    @TableId
    String personId;

    String personName;
    String departmentId;

    @TableField(typeHandler = MybatisEnumTypeHandler.class)
    PersonType personType;

    String leaderId;

    int roleLevel;
    Date createTime;
    Date lastModifyTime;

    @TableField(typeHandler = MybatisEnumTypeHandler.class)
    PersonStatus status;

    @TableField(exist = false)
    RelationshipPO relationshipPO;
}
