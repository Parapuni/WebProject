package cmt.db.jdbc;

import cmt.db.api.BookHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookJdbc implements BookHandler {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BookJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
