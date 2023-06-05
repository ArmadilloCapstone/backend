package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.File;
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

    private RowMapper<Teacher> teacherRowMapper(){
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
                return teacher;
            }
        };
    }


    @Override
    public List<Album> findAllByTeacherID(Long id){
        return jdbcTemplate.query("select A.* from album A inner join teacher T on A.class_id = T.class_id where T.id = ?",memberRowMapper(), id);
    }

    @Override
    public List<Album> findAllByClassID(Long id) {
        return jdbcTemplate.query("select * from album where class_id = ?",memberRowMapper(),id);
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
    public List<Album> searchAlbumForParent(Long parent_id, String keyword, String option) {
        if(option.equals("title")) {
            return jdbcTemplate.query("select A.* from album A inner join parent P on A.class_id = P.class_id where P.id = ? and A.title like '%" + keyword + "%'",
                    memberRowMapper(), parent_id);
        }
        else {
            return jdbcTemplate.query("select A.* from album A inner join parent P on A.class_id = P.class_id where P.id = ? and A.contents like '%" + keyword + "%'",
                    memberRowMapper(), parent_id);
        }
    }

    @Override
    public Album save(Album album) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("album").usingGeneratedKeyColumns("id");

        List<Teacher> this_teacher = jdbcTemplate.query("select * from teacher where id = ?", teacherRowMapper(), album.getWriter_id());
        Long this_class_id = this_teacher.get(0).getClass_id();
        album.setClass_id(this_class_id);

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
        else {
            Album this_album = jdbcTemplate.query("select * from album where id = ?", memberRowMapper(), album_id).get(0);
            String before_file_path = "C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\album\\"+this_album.getTitle()+"\\"+this_album.getFile_url();
            File file = new File(before_file_path);
            file.delete();
            jdbcTemplate.update("update album set title = ?, contents = ?, file_url = ? where id = ?;", title, text, files.get(0).getOriginalFilename(), album_id);
        }
        return new Album();
    }

    @Override
    public Optional<Album> findById(Long id) {
        List<Album> result = jdbcTemplate.query("select * from album where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }
    @Override
    public void delete(Long id) {
        Album this_album = jdbcTemplate.query("select * from album where id = ?", memberRowMapper(), id).get(0);
        String before_file_path = "C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\album\\"+this_album.getTitle()+"\\"+this_album.getFile_url();

        File file = new File(before_file_path);
        file.delete();
        File folder = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\album\\"+this_album.getTitle()+"\\");
        folder.delete();

        jdbcTemplate.update("delete from album where id = ?", id);
    }



}
