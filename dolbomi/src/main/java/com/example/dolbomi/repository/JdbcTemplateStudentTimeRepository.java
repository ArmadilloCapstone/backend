package com.example.dolbomi.repository;

import com.example.dolbomi.domain.StudentState;
import com.example.dolbomi.domain.StudentTime;
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

public class JdbcTemplateStudentTimeRepository implements StudentTimeRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentTimeRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public StudentTime save(StudentTime studentTime) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("student_time").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("student_id", studentTime.getStudent_id());;
        parameters.put("entry1", studentTime.getEntry_1());;
        parameters.put("entry2", studentTime.getEntry_2());;
        parameters.put("entry3", studentTime.getEntry_3());;
        parameters.put("entry4", studentTime.getEntry_4());;
        parameters.put("entry5", studentTime.getEntry_5());;
        parameters.put("off1", studentTime.getOff_1());;
        parameters.put("off2", studentTime.getOff_2());;
        parameters.put("off3", studentTime.getOff_3());;
        parameters.put("off4", studentTime.getOff_4());;
        parameters.put("off5", studentTime.getOff_5());;

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        studentTime.setId(key.longValue());
        return studentTime;
    }

    @Override
    public Optional<StudentTime> findById(Long id) {
        List<StudentTime> result = jdbcTemplate.query("select * from student_time where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<StudentTime> findByStudent_id(Long student_id) {
        List<StudentTime> result = jdbcTemplate.query("select * from student_time where student_id = ?",memberRowMapper(), student_id);
        return result;
    }

    @Override
    public List<StudentTime> findAll() {
        return jdbcTemplate.query("select * from student_time", memberRowMapper());
    }

    private RowMapper<StudentTime> memberRowMapper() {
        return new RowMapper<StudentTime>() {
            @Override
            public StudentTime mapRow(ResultSet rs, int rowNum) throws SQLException {

                StudentTime studentTime = new StudentTime();
                studentTime.setId(rs.getLong("id"));
                studentTime.setStudent_id(rs.getLong("student_id"));
                studentTime.setEntry_1(rs.getTime("entry1"));
                studentTime.setEntry_2(rs.getTime("entry2"));
                studentTime.setEntry_3(rs.getTime("entry3"));
                studentTime.setEntry_4(rs.getTime("entry4"));
                studentTime.setEntry_5(rs.getTime("entry5"));
                studentTime.setOff_1(rs.getTime("off1"));
                studentTime.setOff_2(rs.getTime("off2"));
                studentTime.setOff_3(rs.getTime("off3"));
                studentTime.setOff_4(rs.getTime("off4"));
                studentTime.setOff_5(rs.getTime("off5"));

                return studentTime;
            }
        };
    }
}
