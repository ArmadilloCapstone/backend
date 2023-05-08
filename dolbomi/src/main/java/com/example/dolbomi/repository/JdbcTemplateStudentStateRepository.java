package com.example.dolbomi.repository;

import com.example.dolbomi.domain.StudentSchedule;
import com.example.dolbomi.domain.StudentState;
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

public class JdbcTemplateStudentStateRepository implements StudentStateRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentStateRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public StudentState save(StudentState studentState) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("student_state").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("student_id", studentState.getStudent_id());;
        parameters.put("state", studentState.getState());;

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        studentState.setId(key.longValue());
        return studentState;
    }

    @Override
    public Boolean changeState(Long student_id, int state) {
        int result = jdbcTemplate.update("update student_state set state = ? where student_id = ?;", state, student_id);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public Optional<StudentState> findById(Long id) {
        List<StudentState> result = jdbcTemplate.query("select * from student_state where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<StudentState> findAll() {
        return jdbcTemplate.query("select * from student_state", memberRowMapper());
    }

    private RowMapper<StudentState> memberRowMapper() {
        return new RowMapper<StudentState>() {
            @Override
            public StudentState mapRow(ResultSet rs, int rowNum) throws SQLException {

                StudentState studentState = new StudentState();
                studentState.setId(rs.getLong("id"));
                studentState.setStudent_id(rs.getLong("student_id"));
                studentState.setState(rs.getLong("state"));

                return studentState;
            }
        };
    }
}
