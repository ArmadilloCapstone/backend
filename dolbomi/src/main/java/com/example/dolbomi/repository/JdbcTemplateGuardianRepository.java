package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Guardian;
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

public class JdbcTemplateGuardianRepository implements GuardianRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateGuardianRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Guardian save(Guardian guardian) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("guardian").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", guardian.getName());;
        parameters.put("serial_num", guardian.getSerial_num());;
        parameters.put("info", guardian.getInfo());;

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        guardian.setId(key.longValue());
        return guardian;
    }

    @Override
    public Optional<Guardian> findById(Long id) {
        List<Guardian> result = jdbcTemplate.query("select * from guardian where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Guardian> findAll() {
        return jdbcTemplate.query("select * from guardian", memberRowMapper());
    }

    private RowMapper<Guardian> memberRowMapper() {
        return new RowMapper<Guardian>() {
            @Override
            public Guardian mapRow(ResultSet rs, int rowNum) throws SQLException {

                Guardian guardian = new Guardian();
                guardian.setId(rs.getLong("id"));
                guardian.setName(rs.getString("name"));
                guardian.setSerial_num(rs.getLong("serial_num"));
                guardian.setInfo(rs.getString("info"));

                return guardian;
            }
        };
    }
}