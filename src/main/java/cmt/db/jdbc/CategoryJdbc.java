package cmt.db.jdbc;

import cmt.db.api.CategoryHandler;
import cmt.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryJdbc implements CategoryHandler {
    private final String SELECT_CATEGORIES_BY_ID = "select name from Category_Item where iid = ?";
    private final String ADD_ITEM_CATEGORIES = "insert into Category_Item values(iid,`name`)";
    private final String DELETE_ITEM_CATEGORIES = "delete from Category_Item where iid = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setCategory(List<? extends Item> list) {
        for (Item item : list) {
            item.setCategories(getCategories(item.getIid()));
        }
    }

    public void setCategory(Item item) {
        item.setCategories(getCategories(item.getIid()));
    }

    public List<String> getCategories(long iid) {
        return jdbcTemplate.query(SELECT_CATEGORIES_BY_ID, new StringRowMapper());
    }

    /**
     * 为一个作品（Item）添加分类
     *
     * @param iid
     * @param categories
     */
    @Override
    public void addItemCategories(long iid, List<String> categories) {
        jdbcTemplate.batchUpdate(ADD_ITEM_CATEGORIES, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setLong(1, iid);
                preparedStatement.setString(2, categories.get(i));
            }

            @Override
            public int getBatchSize() {
                return categories.size();
            }
        });
    }

    @Override
    public void removeItem(long iid) {
        jdbcTemplate.update(DELETE_ITEM_CATEGORIES, iid);
    }

    @Override
    public void updateItemCategories(Item item) {
        removeItem(item.getIid());
        addItemCategories(item.getIid(), item.getCategories());
    }

    private static final class StringRowMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet resultSet, int i) throws SQLException {
            return new String(resultSet.getString("name"));
        }
    }
}
