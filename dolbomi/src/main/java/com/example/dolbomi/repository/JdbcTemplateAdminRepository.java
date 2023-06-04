package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Admin;
import com.example.dolbomi.domain.Teacher;
import com.example.dolbomi.domain.TeacherAccount;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateAdminRepository implements AdminRepository{
    private final JdbcTemplate jdbcTemplate;

    private String sha256(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());

            StringBuilder builder = new StringBuilder();
            for (byte b : md.digest()) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();

        } catch (NoSuchAlgorithmException ex){
            throw new RuntimeException(ex);
        }
    }

    public JdbcTemplateAdminRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Admin> login(String user_id, String user_pw){
        return jdbcTemplate.query("select * from admin where user_id = ? and user_pw = ?",
                memberRowMapper(), user_id, sha256(user_pw));
    }
    @Override
    public Boolean changePw(String user_id, String user_pw, String user_new_pw) {
        int result = jdbcTemplate.update("update admin set user_pw = ? where user_id = ? and user_pw = ?;",
                sha256(user_new_pw), user_id, sha256(user_pw));
        if(result == 1){
            return true;
        }
        return false;
    }

    private RowMapper<Admin> memberRowMapper() {
        return new RowMapper<Admin>() {
            @Override
            public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {

                Admin admin = new Admin();
                admin.setUser_id(rs.getString("user_id"));
                admin.setUser_pw(rs.getString("user_pw"));
                admin.setName(rs.getString("name"));

                return admin;
            }
        };
    }
}
