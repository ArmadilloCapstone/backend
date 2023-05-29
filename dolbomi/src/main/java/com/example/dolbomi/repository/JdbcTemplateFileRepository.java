package com.example.dolbomi.repository;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.UploadedFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateFileRepository implements FileRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateFileRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<UploadedFile> memberRowMapper() {
        return new RowMapper<UploadedFile>() {
            @Override
            public UploadedFile mapRow(ResultSet rs, int rowNum) throws SQLException {

                UploadedFile file = new UploadedFile();
                file.setId(rs.getLong("id"));
                file.setNewsId(rs.getLong("newsId"));
                file.setOriginFileName(rs.getString("originFileName"));

                return file;
            }
        };
    }

    private RowMapper<News> newsRowMapper() {
        return new RowMapper<News>() {
            @Override
            public News mapRow(ResultSet rs, int rowNum) throws SQLException {

                News news = new News();
                news.setId(rs.getLong("id"));
                news.setTitle(rs.getString("title"));
                news.setWriter_id(rs.getLong("writer_id"));
                news.setClass_id(rs.getLong("class_id"));
                news.setDate(rs.getDate("uploaded_date"));
                news.setText(rs.getString("contents"));

                return news;
            }
        };
    }


    @Override
    public UploadedFile saveFileInfo(Long newsId, String originfilename){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("uploaded_file").usingGeneratedKeyColumns("id");

        UploadedFile file = new UploadedFile();
        file.setNewsId(newsId);
        file.setOriginFileName(originfilename);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", file.getId());
        parameters.put("newsId", newsId);
        parameters.put("originFileName",originfilename);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return file;
    }


    @Override
    public List<UploadedFile> getFilesByNo(Long id) {
        // id로 테이블 접근 - 원본파일명 획득 - multipartfile로 get
        List<UploadedFile> origin_files =
                jdbcTemplate.query("select * from uploaded_file where newsId = ?",memberRowMapper(), id);

        return origin_files;

    }

    @Override
    public void removeFileById(String domain, Long newsid){
        //news_id로 파일명 가져와서 로컬 경로 들어가서 파일 삭제

        if(domain.equals("news")) {
            List<UploadedFile> origin_files =
                    jdbcTemplate.query("select * from uploaded_file where newsId = ?", memberRowMapper(), newsid);
            List<News> news_list =
                    jdbcTemplate.query("select * from news where id = ?", newsRowMapper(), newsid);

            String title = news_list.get(0).getTitle();
            for (UploadedFile uploadedFile : origin_files) {
                String file_name = uploadedFile.getOriginFileName();
                File file = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\news\\" + title + "\\" + file_name);
                file.delete();
            }
            File folder = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\news\\" + title + "\\");
            folder.delete();
        }
    }

    @Override
    public void deleteFileInfo(String domain, Long newsid){
        jdbcTemplate.update("delete from uploaded_file where newsId = ?", newsid);
    }

}