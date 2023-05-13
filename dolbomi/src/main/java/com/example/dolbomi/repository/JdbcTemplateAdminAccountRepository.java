package com.example.dolbomi.repository;

import com.example.dolbomi.domain.AdminAccount;
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

public class JdbcTemplateAdminAccountRepository implements AdminAccountRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAdminAccountRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public AdminAccount save(AdminAccount adminAccount) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("admin_account").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", adminAccount.getName());
        parameters.put("user_id", adminAccount.getUser_id());
        parameters.put("user_pw", adminAccount.getUser_pw());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        adminAccount.setId(key.longValue());
        return adminAccount;
    }

    @Override
    public Optional<AdminAccount> findById(Long id) {
        List<AdminAccount> result = jdbcTemplate.query("select * from admin_account where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<AdminAccount> findAll() {
        return jdbcTemplate.query("select * from admin_account", memberRowMapper());
    }

    private RowMapper<AdminAccount> memberRowMapper(){
        return new RowMapper<AdminAccount>() {
            @Override
            public AdminAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

                AdminAccount adminAccount = new AdminAccount();
                adminAccount.setId(rs.getLong("id"));
                adminAccount.setName(rs.getString("name"));
                adminAccount.setUser_id(rs.getString("user_id"));
                adminAccount.setUser_pw(rs.getString("user_pw"));

                return adminAccount;
            }
        };
    }
}
