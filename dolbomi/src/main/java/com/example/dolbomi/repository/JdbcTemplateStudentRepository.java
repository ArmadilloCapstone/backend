package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Student;
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

public class JdbcTemplateStudentRepository implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Student save(Student student) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("student").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", student.getName());;
        parameters.put("grade", student.getGrade());;
        parameters.put("phone_num", student.getPhone_num());;
        parameters.put("gender", student.getGender());;
        parameters.put("class_id", student.getClass_id());;
        parameters.put("birth_date", student.getBirth_date());;
        parameters.put("disable", student.getDisable());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        student.setId(key.longValue());
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        List<Student> result = jdbcTemplate.query("select * from student where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }


    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select * from student", memberRowMapper());
    }

    private RowMapper<Student> memberRowMapper() {
        return new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getLong("grade"));
                student.setPhone_num(rs.getString("phone_num"));
                student.setGender(rs.getLong("gender"));
                student.setClass_id(rs.getLong("class_id"));
                student.setBirth_date(rs.getDate("birth_date"));
                student.setDisable(rs.getLong("disable"));

                return student;
            }
        };
    }
}
