package cmt.db.jdbc;

import cmt.db.api.MovieHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MovieJdbc implements MovieHandler {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MovieJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
