package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Message;
import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Parent;
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

    private RowMapper<Parent> memberPRowMapper(){
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

    private RowMapper<Teacher> memberTRowMapper(){
        return new RowMapper<Teacher>() {
            @Override
            public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {

                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("id"));
                teacher.setName(rs.getString("name"));
                teacher.setPhone_num(rs.getString("phone_num"));
                teacher.setGender(rs.getLong("gender"));
                teacher.setBirth_date(rs.getDate("birth_date"));
                teacher.setClass_id(rs.getLong("class_id"));
                teacher.setDisable(rs.getLong("disable"));

                return teacher;
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
    public List<Parent> getAllParentByTid(Long id) {
        List<Parent> result = jdbcTemplate.query("select P.* from teacher T inner join (select P.* from parent P inner join parent_account PA on P.id = PA.parent_id) P on T.class_id = P.class_id where T.id = ?",memberPRowMapper(), id);
        return result;
    }

    @Override
    public List<Teacher> getAllTeacherByPid(Long id) {
        List<Teacher> result = jdbcTemplate.query("select T.* from parent P inner join (select T.* from teacher T inner join teacher_account TA on T.id = TA.teacher_id) T on T.class_id = P.class_id where P.id = 1",memberTRowMapper(), id);
        return result;
    }

    @Override
    public List<Message> getAllMessageByTid(String s) {
        List<Message> result = jdbcTemplate.query("select * from message where sender_id = ? or receiver_id = ?",memberRowMapper(), s, s);
        return result;
    }

    @Override
    public List<Message> getAllMessageByPid(String s) {
        List<Message> result = jdbcTemplate.query("select * from message where sender_id = ? or receiver_id = ?",memberRowMapper(), s, s);
        return result;
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
