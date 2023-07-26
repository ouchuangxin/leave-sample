package ddd.leave.interfaces.dto;

import lombok.Data;

@Data
public class PersonDTO {

    String personId;
    String personName;
    String personType;
    String createTime;
    String lastModifyTime;
    String status;
}
