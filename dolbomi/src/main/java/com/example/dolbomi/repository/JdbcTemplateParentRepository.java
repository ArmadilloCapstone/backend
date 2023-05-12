package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Parent;
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

public class JdbcTemplateParentRepository implements ParentRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateParentRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Parent save(Parent parent) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("parent").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", parent.getName());
        parameters.put("phone_num", parent.getPhone_num());
        parameters.put("gender", parent.getGender());
        parameters.put("birth_date", parent.getBirth_date());
        parameters.put("child_id", parent.getChild_id());
        parameters.put("class_id", parent.getClass_id());
        parameters.put("disable", parent.getDisable());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        parent.setId(key.longValue());
        return parent;
    }

    @Override
    public Optional<Parent> findById(Long id) {
        List<Parent> result = jdbcTemplate.query("select * from parent where id = ?",memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Parent> findByChildId(Long child_id) {
        List<Parent> result = jdbcTemplate.query("select * from parent where child_id = ?",memberRowMapper(),child_id);
        return result.stream().findAny();
    }

    @Override
    public List<Parent> findAll() {
        return jdbcTemplate.query("select * from parent",memberRowMapper());
    }

    private RowMapper<Parent> memberRowMapper(){
        return new RowMapper<Parent>() {
            @Override
            public Parent mapRow(ResultSet rs, int rowNum) throws SQLException {

                Parent parent = new Parent();
                parent.setId(rs.getLong("id"));
                parent.setName(rs.getString("name"));
                parent.setPhone_num(rs.getString("phone_num"));
                parent.setGender(rs.getLong("gender"));
                parent.setBirth_date(rs.getDate("birth_date"));
                parent.setChild_id(rs.getLong("child_id"));
                parent.setClass_id(rs.getLong("class_id"));
                parent.setDisable(rs.getLong("disable"));

                return parent;
            }
        };
    }
}
