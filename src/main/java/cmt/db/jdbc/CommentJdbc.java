package cmt.db.jdbc;

import cmt.db.api.CommentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentJdbc implements CommentHandler {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CommentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
