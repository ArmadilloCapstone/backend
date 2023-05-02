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
        parameters.put("entry_1", studentTime.getEntry_1());;
        parameters.put("entry_2", studentTime.getEntry_2());;
        parameters.put("entry_3", studentTime.getEntry_3());;
        parameters.put("entry_4", studentTime.getEntry_4());;
        parameters.put("entry_5", studentTime.getEntry_5());;
        parameters.put("off_1", studentTime.getOff_1());;
        parameters.put("off_2", studentTime.getOff_2());;
        parameters.put("off_3", studentTime.getOff_3());;
        parameters.put("off_4", studentTime.getOff_4());;
        parameters.put("off_5", studentTime.getOff_5());;

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
                studentTime.setEntry_1(rs.getTime("entry_1"));
                studentTime.setEntry_2(rs.getTime("entry_2"));
                studentTime.setEntry_3(rs.getTime("entry_3"));
                studentTime.setEntry_4(rs.getTime("entry_4"));
                studentTime.setEntry_5(rs.getTime("entry_5"));
                studentTime.setOff_1(rs.getTime("off_1"));
                studentTime.setOff_2(rs.getTime("off_2"));
                studentTime.setOff_3(rs.getTime("off_3"));
                studentTime.setOff_4(rs.getTime("off_4"));
                studentTime.setOff_5(rs.getTime("off_5"));

                return studentTime;
            }
        };
    }
}
