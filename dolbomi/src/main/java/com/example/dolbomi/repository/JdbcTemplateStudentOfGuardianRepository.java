package com.example.dolbomi.repository;

import com.example.dolbomi.domain.StudentOfGuardian;
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

public class JdbcTemplateStudentOfGuardianRepository implements StudentOfGuardianRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentOfGuardianRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public StudentOfGuardian save(StudentOfGuardian studentOfGuardian) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("student_of_guardian").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("guardian_id", studentOfGuardian.getGuardian_id());
        parameters.put("student_id", studentOfGuardian.getStudent_id());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        studentOfGuardian.setId(key.longValue());
        return studentOfGuardian;
    }

    @Override
    public Optional<StudentOfGuardian> findById(Long id) {
        List<StudentOfGuardian> result = jdbcTemplate.query("select * from student_of_guardian where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<StudentOfGuardian> findByGuardian_idStudent_id(Long guardian_id, Long student_id) {
        List<StudentOfGuardian> result = jdbcTemplate.query("select * from student_of_guardian where guardian_id = ? and student_id = ?",memberRowMapper(), guardian_id, student_id);
        return result;
    }

    @Override
    public List<StudentOfGuardian> findByGuardian_id(Long guardian_id) {
        List<StudentOfGuardian> result = jdbcTemplate.query("select * from student_of_guardian where guardian_id = ?",memberRowMapper(),guardian_id);
        return result;
    }

    @Override
    public boolean deleteStudentOfGuardian(Long id) {
        int result = jdbcTemplate.update("delete from student_of_guardian where id = ?;",id);
        if(result==1){
            return true;
        }
        return false;
    }

    @Override
    public List<StudentOfGuardian> findAll() {
        return jdbcTemplate.query("select * from student_of_guardian",memberRowMapper());
    }

    private RowMapper<StudentOfGuardian> memberRowMapper(){
        return new RowMapper<StudentOfGuardian>() {
            @Override
            public StudentOfGuardian mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentOfGuardian studentOfGuardian = new StudentOfGuardian();
                studentOfGuardian.setId(rs.getLong("id"));
                studentOfGuardian.setGuardian_id(rs.getLong("guardian_id"));
                studentOfGuardian.setStudent_id(rs.getLong("student_id"));

                return studentOfGuardian;
            }
        };
    }
}
