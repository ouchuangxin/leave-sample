package ddd.leave.domain.person.repository.po;

import ddd.leave.domain.person.entity.valueobject.PersonStatus;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "person")
public class PersonPO {

    @Id
    String personId;
    String personName;
    String departmentId;
    @Enumerated(EnumType.STRING)
    PersonType personType;
    @Transient
    String leaderId;
    int roleLevel;
    Date createTime;
    Date lastModifyTime;
    @Enumerated(EnumType.STRING)
    PersonStatus status;
    @OneToOne
    RelationshipPO relationshipPO;
}
