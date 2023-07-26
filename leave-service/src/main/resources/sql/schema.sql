create database leavedb;

CREATE TABLE IF NOT EXISTS t_leave(
    id varchar(64) NOT NULL PRIMARY KEY,
    applicant_id varchar(64) NOT NULL,
    applicant_name varchar(64),
    applicant_type varchar(32),
    approver_id varchar(64),
    approver_name varchar(64),
    leave_type varchar(32),
    status varchar(32),

    start_time datetime DEFAULT CURRENT_TIMESTAMP,
    end_time datetime DEFAULT CURRENT_TIMESTAMP,
    duration int
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS t_leave_event(
    id varchar(64) NOT NULL PRIMARY KEY,
    leave_event_type varchar(64) NOT NULL,
    source varchar(64),
    data varchar(1280),
    timestamp datetime DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS t_approval_info(
    approval_info_Id varchar(64) NOT NULL,
    leave_id varchar(64) NOT NULL,
    applicant_id varchar(64) NOT NULL,
    approver_id varchar(64) NOT NULL,
    approver_level int,

    approver_name varchar(128),
    msg varchar(128),
    time long
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS t_person(
    person_id varchar(64) NOT NULL PRIMARY KEY,
    person_name varchar(128),
    department_id varchar(64),
    person_type varchar(32),
    leader_id varchar(64),
    role_level int,

    create_time datetime DEFAULT CURRENT_TIMESTAMP,
    last_modify_time datetime DEFAULT CURRENT_TIMESTAMP,
    status varchar(64)
) CHARACTER SET utf8 COLLATE utf8_general_ci;
insert into t_person(person_id, person_name, department_id, person_type, leader_id, role_level, create_time, last_modify_time, status)
values("person001", "justin", "dept001", "grass", "person002", 1, now(), now(), "enabled");
insert into t_person(person_id, person_name, department_id, person_type, leader_id, role_level, create_time, last_modify_time, status)
values("person002", "eason", "dept001", "medium", "person003", 2, now(), now(), "enabled");
insert into t_person(person_id, person_name, department_id, person_type, leader_id, role_level, create_time, last_modify_time, status)
values("person003", "danial", "dept001", "senior", "N/A", 3, now(), now(), "enabled");


CREATE TABLE IF NOT EXISTS t_approval_rule(
    id varchar(64) NOT NULL PRIMARY KEY,
    leave_type varchar(32),
    person_type varchar(32),
    duration int,
    max_leader_level int
) CHARACTER SET utf8 COLLATE utf8_general_ci;

insert into t_approval_rule(id, leave_type, person_type, duration, max_leader_level)
values("rule001", "internal", "grass", 3, 1);
insert into t_approval_rule(id, leave_type, person_type, duration, max_leader_level)
values("rule002", "internal", "grass", 7, 2);
insert into t_approval_rule(id, leave_type, person_type, duration, max_leader_level)
values("rule003", "internal", "grass", 10, 3);


drop table t_leave;
drop table t_leave_event;
drop table t_person;
drop table t_approval_rule;
drop table t_approval_info;
