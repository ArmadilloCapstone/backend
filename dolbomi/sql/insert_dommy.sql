insert into admin(user_id, user_pw, name)
values('admin', '1234', '관리자');
insert into dolbom_class(id, class_name, class_num, year_seme, disable)
values(1, '돌봄1반', 1, '2021-1',1);
insert into dolbom_class(id, class_name, class_num, year_seme, disable)
values(2, '돌봄2반', 2, '2021-1',1);
insert into dolbom_class(id, class_name, class_num, year_seme, disable)
values(3, '돌봄3반', 3, '2021-1',1);
insert into student(id, name, grade, phone_num, gender, class_id, birth_date, disable, original_class_num)
values(20230001, '가가가', 1, '01000000001',1 , 1, '2003-01-01',1,1);
insert into student(id, name, grade, phone_num, gender, class_id, birth_date, disable, original_class_num)
values(20230002, '가가나', 1, '01000000002',1 , 1, '2003-01-02',1,2);
insert into student(id, name, grade, phone_num, gender, class_id, birth_date, disable, original_class_num)
values(20230003, '가가다', 1, '01000000003',1 , 1, '2003-01-03',1,3);
insert into student(id, name, grade, phone_num, gender, class_id, birth_date, disable, original_class_num)
values(20230004, '임꺽정', 2, '01000000004',2 , 1, '2003-01-04',1,4);
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
values(1750298361, 1);
insert into student_of_guardian(guardian_id, student_id)
values(1750298361, 2);
insert into parent(id, name, phone_num, gender, birth_date, child_id, class_id, disable)
values(12007050, '가민경', '01055558888', 2,'1973-09-03',20230003,1,1);
insert into parent(id, name, phone_num, gender, birth_date, child_id, class_id, disable)
values(12007051, '가민수', '01047579999', 1,'1973-09-01',20230001,1,1);
insert into parent(id, name, phone_num, gender, birth_date, child_id, class_id, disable)
values(12007052, '가혜지', '01012345678', 2,'1973-09-02',20230002,1,1);
insert into parent_account(id, parent_id, user_id, user_pw)
values(1, '12007050', 'test', 'test');
insert into teacher(id, name, phone_num, gender, class_id, birth_date, disable)
values(1, '정숙히', '01000000001',1 , 1, '2003-01-01',1);
insert into teacher(id, name, phone_num, gender, class_id, birth_date, disable)
values(2, '정숙삼', '01000000002',1 , 2, '2003-01-01',1);
insert into teacher(id, name, phone_num, gender, class_id, birth_date, disable)
values(3, '정숙사', '01000000003',1 , 3, '2003-01-01',1);
insert into teacher_account(id, teacher_id, user_id, user_pw)
values(1, '1', 'admin', 'admin');
insert into news(id, title, writer_id, class_id, uploaded_date, contents, file_url)
values(1, 'newstitle1', 1, 1, '2023-05-23', 'newstextnewstext', 'fileurl');
insert into news(id, title, writer_id, class_id, uploaded_date, contents, file_url)
values(2, 'newstitle2', 1, 1, '2023-05-23', 'newstextnewstext', 'fileurl');
insert into news(id, title, writer_id, class_id, uploaded_date, contents, file_url)
values(3, 'newstitle3', 1, 1, '2023-05-23', 'newstextnewstext', 'fileurl');