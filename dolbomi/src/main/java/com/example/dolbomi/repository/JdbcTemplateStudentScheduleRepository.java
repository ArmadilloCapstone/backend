package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentSchedule;
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

public class JdbcTemplateStudentScheduleRepository implements StudentScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentScheduleRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public StudentSchedule save(StudentSchedule studentSchedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("student_schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("student_id", studentSchedule.getStudent_id());;
        parameters.put("class_id", studentSchedule.getClass_id());;

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        studentSchedule.setId(key.longValue());
        return studentSchedule;
    }

    @Override
    public Optional<StudentSchedule> findById(Long id) {
        List<StudentSchedule> result = jdbcTemplate.query("select * from student_schedule where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<StudentSchedule> findByStudent_idClass_id(Long student_id, Long class_id) {
        List<StudentSchedule> result = jdbcTemplate.query("select * from student_schedule where student_id = ? and class_id = ?",memberRowMapper(),student_id,class_id);
        return result;
    }

    @Override
    public List<StudentSchedule> findByClass_id(Long class_id) {
        List<StudentSchedule> result = jdbcTemplate.query("select * from student_schedule where class_id = ?",memberRowMapper(),class_id);
        return result;
    }

    @Override
    public List<StudentSchedule> findByStudent_id(Long student_id) {
        List<StudentSchedule> result = jdbcTemplate.query("select * from student_schedule where student_id = ?",memberRowMapper(),student_id);
        return result;
    }

    @Override
    public boolean deleteStudentSchedule(Long id) {
        int result = jdbcTemplate.update("delete from student_schedule where id = ?",id);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<StudentSchedule> findAll() {
        return jdbcTemplate.query("select * from student_schedule", memberRowMapper());
    }

    private RowMapper<StudentSchedule> memberRowMapper() {
        return new RowMapper<StudentSchedule>() {
            @Override
            public StudentSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {

                StudentSchedule studentSchedule = new StudentSchedule();
                studentSchedule.setId(rs.getLong("id"));
                studentSchedule.setStudent_id(rs.getLong("student_id"));
                studentSchedule.setClass_id(rs.getLong("class_id"));

                return studentSchedule;
            }
        };
    }
}
