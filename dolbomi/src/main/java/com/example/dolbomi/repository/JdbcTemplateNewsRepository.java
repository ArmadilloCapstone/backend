package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Admin;
import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Parent;
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

public class JdbcTemplateNewsRepository implements NewsRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateNewsRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }



    private RowMapper<News> memberRowMapper() {
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
    public List<News> findAllByTeacherID(Long id) {
        return jdbcTemplate.query("select N.* from news N inner join teacher T on N.class_id = T.class_id where T.id = ?",memberRowMapper(), id);
    }

    @Override
    public Optional<News> findById(Long id) {
        List<News> result = jdbcTemplate.query("select * from news where id = ?",memberRowMapper(), id);
        return result.stream().findAny();
    }

    public void delete(Long id) {
        //List<News> result =
        jdbcTemplate.update("delete from news where id = ?", id);
        //return result.stream().findAny();
    }

    @Override
    public News save(News news) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("news").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", news.getTitle());
        parameters.put("writer_id",news.getWriter_id());
        parameters.put("class_id",news.getClass_id());
        parameters.put("uploaded_date",news.getDate());
        parameters.put("contents",news.getText());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        news.setId(key.longValue());
        return news;
    }

}
