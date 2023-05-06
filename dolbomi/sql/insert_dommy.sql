insert into student(id, name, grade, phone_num, gender, class_id, birth_date, disable)
values(20230001, '가가가', 1, '01000000001',1 , 1, '2003-01-01',1);
insert into student(id, name, grade, phone_num, gender, class_id, birth_date, disable)
values(20230002, '가가나', 1, '01000000002',1 , 1, '2003-01-02',1);
insert into student(id, name, grade, phone_num, gender, class_id, birth_date, disable)
values(20230003, '가가다', 1, '01000000003',1 , 1, '2003-01-03',1);

insert into after_school_class(id, class_name, start_time, end_time, day)
values(001, '종이접기반A', '15:00:00', '17:00:00',1);
insert into after_school_class(id, class_name, start_time, end_time, day)
values(002, '종이접기반A', '15:30:00', '17:30:00',3);
insert into after_school_class(id, class_name, start_time, end_time, day)
values(003, '종이접기반B', '15:00:00', '17:00:00',2);
insert into after_school_class(id, class_name, start_time, end_time, day)
values(004, '종이접기반B', '15:30:00', '17:30:00',5);

insert into student_schedule(id, student_id, class_id)
values(0001, 20230001, 001);
insert into student_schedule(id, student_id, class_id)
values(0002, 20230001, 002);
insert into student_schedule(id, student_id, class_id)
values(0003, 20230002, 001);
insert into student_schedule(id, student_id, class_id)
values(0004, 20230002, 002);
insert into student_schedule(id, student_id, class_id)
values(0005, 20230003, 003);
insert into student_schedule(id, student_id, class_id)
values(0006, 20230003, 004);

insert into student_state(id, student_id, state)
values(001, 20230001, 2);
insert into student_state(id, student_id, state)
values(002, 20230002, 2);
insert into student_state(id, student_id, state)
values(003, 20230003, 2);

insert into student_time(id, student_id, entry1, entry2, entry3, entry4, entry5, off1, off2, off3, off4, off5)
values(001, 20230001, '14:00:00', '14:00:00', '14:00:00', '14:00:00', '14:00:00', '18:30:00', '18:30:00', '18:30:00', '18:30:00', '18:30:00');
insert into student_time(id, student_id, entry1, entry2, entry3, entry4, entry5, off1, off2, off3, off4, off5)
values(002, 20230002, '14:00:00', '14:00:00', '14:00:00', '14:00:00', '14:00:00', '18:30:00', '18:30:00', '18:30:00', '18:30:00', '18:30:00');
insert into student_time(id, student_id, entry1, entry2, entry3, entry4, entry5, off1, off2, off3, off4, off5)
values(003, 20230003, '14:00:00', '14:00:00', '14:00:00', '14:00:00', '14:00:00', '18:30:00', '18:30:00', '18:30:00', '18:30:00', '18:30:00');

insert into guardian(id, name, serial_num ,info)
values(0001,"하루윌", 1750298361, "가나다 태권도장의 셔틀버스 기사");

insert into student_of_guardian(guardian_id, student_id)
values(0001, 20230001);
insert into student_of_guardian(guardian_id, student_id)
values(0001, 20230002);