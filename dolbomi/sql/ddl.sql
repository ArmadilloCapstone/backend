drop table if exists teacher_account CASCADE;
drop table if exists teacher CASCADE;
drop table if exists parent_account CASCADE;
drop table if exists parent CASCADE;
drop table if exists student_of_guardian CASCADE;
drop table if exists guardian CASCADE;
drop table if exists student_time CASCADE;
drop table if exists student_state CASCADE;
drop table if exists student_schedule CASCADE;
drop table if exists after_school_class CASCADE;
drop table if exists student CASCADE;
drop table if exists dolbom_class CASCADE;
drop table if exists admin CASCADE;

create table admin
(
	user_id char(20),
	user_pw char(20),
	name char(5)
);

create table dolbom_class
(
	id int primary key,
	class_name char(20),
	class_num int,
	year_seme char(10),
	disable tinyint
);

create table student
(
	id int primary key,
	name char(5),
	grade tinyint,
	phone_num char(11),
	gender tinyint,
	class_id int,
	birth_date date,
	disable tinyint,
	original_class_num int,
	foreign key (class_id)
		references dolbom_class(id) on update cascade

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
	state int,
	foreign key (student_id)
		references student(id) on update cascade
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
	info varchar(80)
);

create table student_of_guardian
(
	guardian_id int,
	student_id int
);

create table parent
(
	id int primary key,
	name char(5),
	phone_num char(11),
	gender tinyint,
	birth_date date,
	child_id int,
	class_id int,
	disable tinyint,
	foreign key (child_id)
		references student(id) on update cascade,
	foreign key (class_id)
		references dolbom_class(id) on update cascade
);

create table parent_account
(
	id int primary key,
	parent_id int,
	user_id char(20),
	user_pw char(20),
	foreign key (parent_id)
		references parent(id) on update cascade
);

create table teacher
(
	id int primary key,
	name char(5),
	phone_num char(11),
	gender tinyint,
	birth_date date,
	class_id int,
	disable tinyint
);

create table teacher_account
(
	id int primary key,
	teacher_id int,
	user_id char(20),
	user_pw char(20),
	child_id int,
	class_id int,
	disable tinyint,
	foreign key (teacher_id)
		references teacher(id) on update cascade
);
SET FOREIGN_KEY_CHECKS = 0;
alter table teacher modify id int not null auto_increment;
alter table parent modify id int not null auto_increment;
alter table student modify id int not null auto_increment;
alter table dolbom_class modify id int not null auto_increment;
alter table after_school_class modify id int not null auto_increment;

alter table admin convert to charset utf8;
alter table dolbom_class convert to charset utf8;
alter table student convert to charset utf8;
alter table after_school_class convert to charset utf8;
alter table student_schedule convert to charset utf8;
alter table student_state convert to charset utf8;
alter table student_time convert to charset utf8;
alter table guardian convert to charset utf8;
alter table guardian modify id int not null auto_increment;
alter table student_of_guardian convert to charset utf8;
alter table parent convert to charset utf8;
alter table parent_account convert to charset utf8;
alter table parent_account modify id int not null auto_increment;
alter table teacher convert to charset utf8;
alter table teacher_account convert to charset utf8;
alter table teacher_account modify id int not null auto_increment;
