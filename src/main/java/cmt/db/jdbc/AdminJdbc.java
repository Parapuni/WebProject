package cmt.db.jdbc;

import cmt.db.api.AdminHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Admin数据操作接口的实现，使用Jdbc
 */
@Repository
public class AdminJdbc implements AdminHandler {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdminJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

