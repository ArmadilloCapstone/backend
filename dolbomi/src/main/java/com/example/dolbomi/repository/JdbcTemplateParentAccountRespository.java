package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Parent;
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

public class JdbcTemplateParentAccountRespository implements ParentAccountRespository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateParentAccountRespository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Parent> login(String user_id, String user_pw) {
        return jdbcTemplate.query("select P.* from parent_account PS inner join parent P on PS.parent_id = P.id where PS.user_id = ? and PS.user_pw = ?", memberTRowMapper(), user_id, user_pw);
    }

    @Override
    public Boolean signup(String user_id, String user_pw, String name) {
        List<Parent> list =  jdbcTemplate.query("select * from parent where name = ?", memberTRowMapper(), name);
        if(list.size() == 1){
            Long tid = list.get(0).getId();

            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("parent_account").usingGeneratedKeyColumns("id");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("parent_id", tid);;
            parameters.put("user_id", user_id);;
            parameters.put("user_pw", user_pw);;

            jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean changePw(String user_id, String user_pw, String user_new_pw) {
        int result = jdbcTemplate.update("update parent_account set user_pw = ? where user_id = ? and user_pw = ?;", user_new_pw, user_id, user_pw);
        if(result == 1){
            return true;
        }
        return false;
    }

    private RowMapper<Parent> memberTRowMapper() {
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
