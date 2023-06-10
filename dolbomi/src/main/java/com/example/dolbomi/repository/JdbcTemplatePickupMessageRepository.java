package com.example.dolbomi.repository;

import com.example.dolbomi.domain.PickupMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.*;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplatePickupMessageRepository implements PickupMessageRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePickupMessageRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PickupMessage save(PickupMessage pickupMessage) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("pickup_message").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pickup_man_id", pickupMessage.getPickup_man_id());
        parameters.put("pickup_man_name", pickupMessage.getPickup_man_name());
        parameters.put("student_id", pickupMessage.getStudent_id());
        parameters.put("student_name", pickupMessage.getStudent_name());
        parameters.put("student_grade", pickupMessage.getStudent_grade());
        parameters.put("student_gender", pickupMessage.getStudent_gender());
        parameters.put("teacher_id", pickupMessage.getTeacher_id());
        parameters.put("teacher_name", pickupMessage.getTeacher_name());
        parameters.put("date", pickupMessage.getDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        pickupMessage.setId(key.longValue());
        return pickupMessage;
    }

    @Override
    public Optional<PickupMessage> findById(Long id) {
        List<PickupMessage> result = jdbcTemplate.query("select * from pickup_message where id = ?",memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<PickupMessage> findAll() {
        return jdbcTemplate.query("select * from pickup_message",memberRowMapper());
    }

    private RowMapper<PickupMessage> memberRowMapper(){
        return new RowMapper<PickupMessage>() {
            @Override
            public PickupMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
                PickupMessage pickupMessage = new PickupMessage();
                pickupMessage.setId(rs.getLong("id"));
                pickupMessage.setPickup_man_id(rs.getLong("pickup_man_id"));
                pickupMessage.setPickup_man_name(rs.getString("pickup_man_name"));
                pickupMessage.setStudent_id(rs.getLong("student_id"));
                pickupMessage.setStudent_name(rs.getString("student_name"));
                pickupMessage.setStudent_grade(rs.getLong("student_grade"));
                pickupMessage.setStudent_gender(rs.getLong("student_gender"));
                pickupMessage.setTeacher_id(rs.getLong("teacher_id"));
                pickupMessage.setTeacher_name(rs.getString("teacher_name"));
                pickupMessage.setDate(rs.getTimestamp("date"));
                return pickupMessage;
            }
        };
    }

    public void exportPickupLog(Long teacher_id, HttpServletResponse response){

        List<PickupMessage> result = jdbcTemplate.query("select * from pickup_message where teacher_id = ?",memberRowMapper(), teacher_id);
        if(result.size()==0){
            return;
        }
        String teacher_name = result.get(0).getTeacher_name();
        try {
            File file = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\exportedLogs\\돌봄교사 " + teacher_name + " 학생 픽업 로그.csv\\");
            BufferedWriter bw = new BufferedWriter(new FileWriter((file)));

            bw.write("교사명, 보호자명, 학생명, 픽업 시간\n");

            for (PickupMessage pickupMessage : result){
                bw.write(pickupMessage.getTeacher_name());
                bw.write(", ");
                bw.write(pickupMessage.getPickup_man_name());
                bw.write(", ");
                bw.write(pickupMessage.getStudent_name());
                bw.write(", ");
                bw.write(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(pickupMessage.getDate()));
                bw.write("\n");
            }
            bw.flush();
            bw.close();

            String filename = "돌봄교사 " + teacher_name + " 학생 픽업 로그.csv";
            String filename_chrome = new String(filename.getBytes("UTF-8"), "ISO-8859-1");

            response.setContentType("application/download");
            response.setContentLength((int)file.length());
            response.setHeader("Content-disposition", "attachment;filename=\""+filename_chrome+"\"");
            // response 객체를 통해서 서버로부터 파일 다운로드
            OutputStream os = response.getOutputStream();
            // 파일 입력 객체 생성
            FileInputStream fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, os);
            fis.close();
            os.close();

        } catch(Exception e){
            e.printStackTrace();
        }



    }
}
