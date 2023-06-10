package com.example.dolbomi.repository;

import com.example.dolbomi.domain.PickupMessage;
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

public class JdbcTemplatePickupMessageRepository implements PickupMessageRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePickupMessageRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PickupMessage save(PickupMessage pickupMessage) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("pickup_message").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pickup_man_id", pickupMessage.getPickup_man_id());
        parameters.put("pickup_man_name", pickupMessage.getPickup_man_name());
        parameters.put("student_id", pickupMessage.getStudent_id());
        parameters.put("student_name", pickupMessage.getStudent_name());
        parameters.put("student_grade", pickupMessage.getStudent_grade());
        parameters.put("student_gender", pickupMessage.getStudent_gender());
        parameters.put("teacher_id", pickupMessage.getTeacher_id());
        parameters.put("teacher_name", pickupMessage.getTeacher_name());
        parameters.put("date", pickupMessage.getDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        pickupMessage.setId(key.longValue());
        return pickupMessage;
    }

    @Override
    public Optional<PickupMessage> findById(Long id) {
        List<PickupMessage> result = jdbcTemplate.query("select * from pickup_message where id = ?",memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<PickupMessage> findAll() {
        return jdbcTemplate.query("select * from pickup_message",memberRowMapper());
    }

    private RowMapper<PickupMessage> memberRowMapper(){
        return new RowMapper<PickupMessage>() {
            @Override
            public PickupMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
                PickupMessage pickupMessage = new PickupMessage();
                pickupMessage.setId(rs.getLong("id"));
                pickupMessage.setPickup_man_id(rs.getLong("pickup_man_id"));
                pickupMessage.setPickup_man_name(rs.getString("pickup_man_name"));
                pickupMessage.setStudent_id(rs.getLong("student_id"));
                pickupMessage.setStudent_name(rs.getString("student_name"));
                pickupMessage.setStudent_grade(rs.getLong("student_grade"));
                pickupMessage.setStudent_gender(rs.getLong("student_gender"));
                pickupMessage.setTeacher_id(rs.getLong("teacher_id"));
                pickupMessage.setTeacher_name(rs.getString("teacher_name"));
                pickupMessage.setDate(rs.getTimestamp("date"));
                return pickupMessage;
            }
        };
    }
}
