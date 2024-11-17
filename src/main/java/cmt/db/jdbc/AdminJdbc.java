package cmt.db.jdbc;

import cmt.db.api.AdminHandler;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Admin数据操作接口的实现，使用Jdbc
 */
public class AdminJdbc implements AdminHandler {

    private JdbcTemplate jdbcTemplate;

}

