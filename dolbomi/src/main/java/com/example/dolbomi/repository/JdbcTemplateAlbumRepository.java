package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateAlbumRepository implements  AlbumRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAlbumRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Album> memberRowMapper() {
        return new RowMapper<Album>() {
            @Override
            public Album mapRow(ResultSet rs, int rowNum) throws SQLException {

                Album album = new Album();
                album.setId(rs.getLong("id"));
                album.setTitle(rs.getString("title"));
                album.setWriter_id(rs.getLong("writer_id"));
                album.setClass_id(rs.getLong("class_id"));
                album.setUploaded_date(rs.getDate("uploaded_date"));
                album.setContents(rs.getString("contents"));

                return album;
            }
        };
    }


    @Override
    public List<Album> findAllByTeacherID(Long id){
        return jdbcTemplate.query("select A.* from album A inner join teacher T on A.class_id = T.class_id where T.id = ?",memberRowMapper(), id);
    }



}
