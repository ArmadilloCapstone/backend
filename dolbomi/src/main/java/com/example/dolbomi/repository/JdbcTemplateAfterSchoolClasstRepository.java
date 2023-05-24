package com.example.dolbomi.repository;

import com.example.dolbomi.domain.AfterSchoolClass;
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

public class JdbcTemplateAfterSchoolClasstRepository implements AfterSchoolClassRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAfterSchoolClasstRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public AfterSchoolClass save(AfterSchoolClass afterSchoolClass) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("after_school_class").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("class_name", afterSchoolClass.getClass_name());
        parameters.put("start_time", afterSchoolClass.getStart_time());
        parameters.put("end_time", afterSchoolClass.getEnd_time());
        parameters.put("day", afterSchoolClass.getDay());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        afterSchoolClass.setId(key.longValue());
        return afterSchoolClass;
    }

    @Override
    public Optional<AfterSchoolClass> findById(Long id) {
        List<AfterSchoolClass> result = jdbcTemplate.query("select * from after_school_class where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<AfterSchoolClass> findByClassName(String class_name) {
        List<AfterSchoolClass> result = jdbcTemplate.query("select * from after_school_class where class_name = ?",memberRowMapper(), class_name);
        return result;
    }

    @Override
    public List<AfterSchoolClass> findByClass_nameDay(String class_name, Long day) {
        List<AfterSchoolClass> result = jdbcTemplate.query("select * from after_school_class where class_name = ? and day = ?",memberRowMapper(), class_name, day);
        return result;
    }

    @Override
    public boolean deleteAfterSchoolClass(Long id) {
        int result = jdbcTemplate.update("delete from after_school_class where id = ?",id);
        if(result==1){
            return true;
        }
        return false;
    }

    @Override
    public List<AfterSchoolClass> findAll() {
        return jdbcTemplate.query("select * from after_school_class", memberRowMapper());
    }

    private RowMapper<AfterSchoolClass> memberRowMapper() {
        return new RowMapper<AfterSchoolClass>() {
            @Override
            public AfterSchoolClass mapRow(ResultSet rs, int rowNum) throws SQLException {

                AfterSchoolClass afterSchoolClass = new AfterSchoolClass();
                afterSchoolClass.setId(rs.getLong("id"));
                afterSchoolClass.setClass_name(rs.getString("class_name"));
                afterSchoolClass.setStart_time(rs.getTime("start_time"));
                afterSchoolClass.setEnd_time(rs.getTime("end_time"));
                afterSchoolClass.setDay(rs.getLong("day"));

                return afterSchoolClass;
            }
        };
    }
}
