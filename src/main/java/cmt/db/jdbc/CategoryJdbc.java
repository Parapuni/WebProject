package cmt.db.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryJdbc {
    private final String SELECT_CATEGORIES_BY_ID = "select name from Category_Item where iid = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getCategories(long iid) {
        return jdbcTemplate.query(SELECT_CATEGORIES_BY_ID, new StringRowMapper());
    }

    private static final class StringRowMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet resultSet, int i) throws SQLException {
            return new String(resultSet.getString("name"));
        }
    }
}
