package com.example.dolbomi.repository;

import com.example.dolbomi.domain.DolbomClass;
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

public class JdbcTemplateDolbomClassRepository implements DolbomClassRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateDolbomClassRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public DolbomClass save(DolbomClass dolbomClass) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("DolbomClass").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("class_name",dolbomClass.getClass_name());
        parameters.put("class_num", dolbomClass.getClass_num());
        parameters.put("year_seme", dolbomClass.getYear_seme());
        parameters.put("disable", dolbomClass.getDisable());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        dolbomClass.setId(key.longValue());
        return dolbomClass;
    }

    @Override
    public Optional<DolbomClass> findById(Long id) {
        List<DolbomClass> result = jdbcTemplate.query("select * from dolbom_class where id = ?",memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<DolbomClass> findAll() {
        return jdbcTemplate.query("select * from dolbom_class",memberRowMapper());
    }

    private RowMapper<DolbomClass> memberRowMapper(){
        return new RowMapper<DolbomClass>() {
            @Override
            public DolbomClass mapRow(ResultSet rs, int rowNum) throws SQLException {

                DolbomClass dolbomClass = new DolbomClass();
                dolbomClass.setId(rs.getLong("id"));
                dolbomClass.setClass_name(rs.getString("class_name"));
                dolbomClass.setClass_num(rs.getLong("class_num"));
                dolbomClass.setYear_seme(rs.getString("year_seme"));
                dolbomClass.setDisable(rs.getLong("disable"));

                return dolbomClass;
            }
        };
    }
}
