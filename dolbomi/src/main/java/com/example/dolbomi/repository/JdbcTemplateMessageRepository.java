package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Message;
import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMessageRepository implements MessageRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMessageRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }



    private RowMapper<Message> memberRowMapper() {
        return new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message message = new Message();
                message.setId(rs.getLong("id"));
                message.setSender_id(rs.getString("sender_id"));
                message.setSender_name(rs.getString("sender_name"));
                message.setReceiver_id(rs.getString("receiver_id"));
                message.setReceiver_name(rs.getString("receiver_name"));
                message.setText(rs.getString("text"));
                message.setDate(rs.getTimestamp("date"));

                return message;
            }
        };
    }


    @Override
    public Optional<Message> findById(Long id) {
        List<Message> result = jdbcTemplate.query("select * from news where id = ?",memberRowMapper(), id);
        return result.stream().findAny();
    }

    public void delete(Long id) {
        jdbcTemplate.update("delete from news where id = ?", id);
    }

    @Override
    public Message save(Message message) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("message").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("sender_id", message.getSender_id());
        parameters.put("sender_name",message.getSender_name());
        parameters.put("receiver_id", message.getReceiver_id());
        parameters.put("receiver_name",message.getReceiver_name());
        parameters.put("text", message.getText());
        parameters.put("date",message.getDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        message.setId(key.longValue());
        return message;
    }
}
