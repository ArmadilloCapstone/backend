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
        parameters.put("name", student.getName());

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
    public Optional<Student> findByName(String name) {
        List<Student> result = jdbcTemplate.query("select * from student where name = ?", memberRowMapper(), name);
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

                return student;
            }
        };
    }
}
