drop table if exists student CASCADE;
drop table if exists after_school_class CASCADE;
drop table if exists student_schedule CASCADE;
drop table if exists student_state CASCADE;
drop table if exists student_time CASCADE;
drop table if exists guardian CASCADE;
drop table if exists student_of_guardian CASCADE;


create table student
(
    id int primary key,
    name char(5),
    grade tinyint,
    phone_num char(11),
    gender tinyint,
    class_id int,
    birth_date date,
    disable tinyint

);
create table after_school_class
(
    id int primary key,
    class_name char(20),
    start_time time,
    end_time time,
    day int
);
create table student_schedule
(
    id int primary key,
    student_id int,
    class_id int
);
create table student_state
(
    id int primary key,
    student_id int,
    state int
);
create table student_time
(
    id int primary key,
    student_id int,
    entry1 time,
    entry2 time,
    entry3 time,
    entry4 time,
    entry5 time,
    off1 time,
    off2 time,
    off3 time,
    off4 time,
    off5 time
);

create table guardian
(
    id int primary key,
    name char(5),
    serial_num int,
    info varchar(80),

);

create table student_of_guardian
(
    guardian_id int,
    student_id int,

);


alter table student convert to charset utf8;
alter table after_school_class convert to charset utf8;
alter table student_schedule convert to charset utf8;
alter table student_state convert to charset utf8;
alter table student_time convert to charset utf8;
alter table guardian convert to charset utf8;
alter table student_of_guardian convert to charset utf8;