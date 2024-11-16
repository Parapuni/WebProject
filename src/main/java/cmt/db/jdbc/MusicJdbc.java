package cmt.db.jdbc;

import cmt.db.api.MusicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MusicJdbc implements MusicHandler {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MusicJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
