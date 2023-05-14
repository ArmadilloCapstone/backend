package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcTemplateTeacherRepository implements TeacherRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTeacherRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Teacher save(Teacher teacher) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("teacher").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", teacher.getName());
        parameters.put("phone_num", teacher.getPhone_num());
        parameters.put("gender", teacher.getGender());
        parameters.put("birth_date", teacher.getBirth_date());
        parameters.put("class_id", teacher.getClass_id());
        parameters.put("disable", teacher.getDisable());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        teacher.setId(key.longValue());
        return teacher;
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        List<Teacher> result = jdbcTemplate.query("select * from teacher where id = ?",memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Teacher> findByNameBirth(String name, Date birth_date) {
        List<Teacher> result = jdbcTemplate.query("select * from teacher where name = ? and birth_date = ?",memberRowMapper(), name, birth_date);
        return result;
    }

    @Override
    public boolean activationTeacher(Long id) {
        int result = jdbcTemplate.update("update teacher set disable = ? where id = ?;", 1, id);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean disableTeacher(Long id) {
        int result = jdbcTemplate.update("update teacher set disable = ? where id = ?;",0,id);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<Teacher> findActivationTeacher() {
        List<Teacher> result = jdbcTemplate.query("select * from teacher where disable = ?", memberRowMapper(), 1);
        return result;
    }

    @Override
    public List<Teacher> findAll() {
        return jdbcTemplate.query("select * from teacher", memberRowMapper());
    }

    private RowMapper<Teacher> memberRowMapper(){
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
}
