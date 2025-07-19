package ddd.leave.domain.leave.entity.valueobject;

import ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {

    String personId;
    String personName;
    PersonType personType;
}
