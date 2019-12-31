package ddd.leave.domain.person.entity;

import lombok.Data;

@Data
public class Relationship {

    String id;
    String personId;
    String leaderId;
    int leaderLevel;
}
