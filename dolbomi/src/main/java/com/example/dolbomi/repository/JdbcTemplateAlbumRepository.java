package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;
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
                album.setFile_url(rs.getString("file_url"));

                return album;
            }
        };
    }


    @Override
    public List<Album> findAllByTeacherID(Long id){
        return jdbcTemplate.query("select A.* from album A inner join teacher T on A.class_id = T.class_id where T.id = ?",memberRowMapper(), id);
    }

    @Override
    public List<Album> searchAlbum(Long teacher_id, String keyword, String option){
        if(option.equals("title")) {
            return jdbcTemplate.query("select A.* from album A inner join teacher T on A.class_id = T.class_id where T.id = ? and A.title like '%" + keyword + "%'",
                    memberRowMapper(), teacher_id);
        }
        else {
            return jdbcTemplate.query("select A.* from album A inner join teacher T on A.class_id = T.class_id where T.id = ? and A.contents like '%" + keyword + "%'",
                    memberRowMapper(), teacher_id);
        }
    }

    @Override
    public Album save(Album album) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("album").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", album.getTitle());
        parameters.put("writer_id",album.getWriter_id());
        parameters.put("class_id",album.getClass_id());
        parameters.put("uploaded_date",album.getUploaded_date());
        parameters.put("contents",album.getContents());
        parameters.put("file_url",album.getFile_url());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        album.setId(key.longValue());
        return album;
    }

    @Override
    public Album updateAlbum(Long album_id, String title, String text, Boolean file_changed, List<MultipartFile> files){
        if(file_changed==false)
            jdbcTemplate.update("update album set title = ?, contents = ?  where id = ?;", title, text, album_id);
        else
            jdbcTemplate.update("update album set title = ?, contents = ?, file_url = ? where id = ?;", title, text, files.get(0).getOriginalFilename(), album_id);
        return new Album();
    }

    @Override
    public Optional<Album> findById(Long id) {
        List<Album> result = jdbcTemplate.query("select * from album where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }
    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from album where id = ?", id);
    }



}
