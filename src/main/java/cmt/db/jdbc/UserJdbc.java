package cmt.db.jdbc;

import cmt.db.api.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbc implements UserHandler {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
