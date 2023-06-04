package com.example.dolbomi.repository;

import com.example.dolbomi.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateTeacherAccountRespository implements TeacherAccountRespository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTeacherAccountRespository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Teacher> login(String user_id, String user_pw) {
        return jdbcTemplate.query("select T.* from teacher_account TS inner join teacher T on TS.teacher_id = T.id where TS.user_id = ? and TS.user_pw = ?", memberTRowMapper(), user_id, user_pw);
    }

    @Override
    public String signup(String user_id, String user_pw, String name, String phone_num) {
        List<Teacher> validation1 =  jdbcTemplate.query("select * from teacher where name = ? and phone_num = ?", memberTRowMapper(), name, phone_num);
        if(validation1.size() != 1){
            return "일치하는 선생님이 없습니다.";
        }
        Long parent_id = validation1.get(0).getId();
        List<TeacherAccount> validation2 =  jdbcTemplate.query("select * from teacher_account where teacher_id = ?", memberTCRowMapper(), parent_id);
        List<TeacherAccount> validation3 =  jdbcTemplate.query("select * from teacher_account where user_id = ?", memberTCRowMapper(), user_id);
        if(validation2.size() != 0){
            return "이미 등록된 회원입니다.";
        }
        if(validation3.size() != 0){
            return "이미 존재하는 아이디입니다.";
        }
        if(validation1.size() == 1 && validation2.size() == 0 && validation3.size() == 0){
            Long tid = validation1.get(0).getId();

            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("teacher_account").usingGeneratedKeyColumns("id");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("teacher_id", tid);;
            parameters.put("user_id", user_id);;
            parameters.put("user_pw", user_pw);;

            jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

            return "success";
        }
        else{
            return "error";
        }
    }
    @Override
    public Boolean changePw(String user_id, String user_pw, String user_new_pw) {
        int result = jdbcTemplate.update("update teacher_account set user_pw = ? where user_id = ? and user_pw = ?;", user_new_pw, user_id, user_pw);
        if(result == 1){
            return true;
        }
        return false;
    }

    private RowMapper<TeacherAccount> memberRowMapper() {
        return new RowMapper<TeacherAccount>() {
            @Override
            public TeacherAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

                TeacherAccount teacherAccount = new TeacherAccount();
                teacherAccount.setId(rs.getLong("id"));
                teacherAccount.setTeacher_id(rs.getLong("teacher_id"));
                teacherAccount.setUser_id(rs.getString("user_id"));
                teacherAccount.setUser_pw(rs.getString("user_pw"));

                return teacherAccount;
            }
        };
    }

    private RowMapper<Teacher> memberTRowMapper() {
        return new RowMapper<Teacher>() {
            @Override
            public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {

                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("id"));
                teacher.setName(rs.getString("name"));
                teacher.setPhone_num(rs.getString("phone_num"));
                teacher.setGender(rs.getLong("gender"));
                teacher.setBirth_date(rs.getDate("birth_date"));
                teacher.setClass_id(rs.getLong("class_id"));
                teacher.setDisable(rs.getLong("disable"));

                return teacher;
            }
        };
    }


    private RowMapper<TeacherAccount> memberTCRowMapper() {
        return new RowMapper<TeacherAccount>() {
            @Override
            public TeacherAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

                TeacherAccount teacherAccount = new TeacherAccount();
                teacherAccount.setId(rs.getLong("id"));
                teacherAccount.setTeacher_id(rs.getLong("teacher_id"));
                teacherAccount.setUser_id(rs.getString("user_id"));
                teacherAccount.setUser_pw(rs.getString("user_pw"));

                return teacherAccount;
            }
        };
    }


}
