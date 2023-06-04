package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.Teacher;
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

    private Long sha256(Long num){
        return num*100+123;
    }

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
    public Optional<Guardian> findBySerialNum(Long serial_num) {
        List<Guardian> result = jdbcTemplate.query("select * from guardian where serial_num = ?", memberRowMapper(), serial_num);
        return result.stream().findAny();
    }


    @Override
    public List<Guardian> findAll() {
        return jdbcTemplate.query("select * from guardian", memberRowMapper());
    }

    @Override
    public List<Guardian> findByNameInfo(String name, String info) {
        List<Guardian> result = jdbcTemplate.query("select * from guardian where name = ? and info = ?",memberRowMapper(),name,info);
        return result;
    }

    @Override
    public boolean deleteGuardian(Long id) {
        int result = jdbcTemplate.update("delete from guardian where id = ?",id);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<Guardian> login(Long serial_num) {
        return jdbcTemplate.query("select * from guardian where serial_num = ?",
                memberRowMapper(), sha256(serial_num));
    }

    @Override
    public Boolean signup(Long serialNum, String name, String info) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("guardian").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("serial_num", sha256(serialNum));
        parameters.put("name", name);
        parameters.put("info", info);

        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return true;
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