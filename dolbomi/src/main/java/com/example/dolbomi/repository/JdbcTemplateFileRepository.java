package com.example.dolbomi.repository;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.UploadedFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
                file.setNewsId(rs.getLong("newsid"));
                file.setOriginFileName(rs.getString("originFileName"));

                return file;
            }
        };
    }


    public UploadedFile saveFileInfo(Long newsId, String originfilename){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("uploadedfile").usingGeneratedKeyColumns("id");

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


}
