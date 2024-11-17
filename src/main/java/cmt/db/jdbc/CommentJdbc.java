package cmt.db.jdbc;

import cmt.db.api.CommentHandler;
import cmt.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CommentJdbc implements CommentHandler {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class CommentRowMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Comment(resultSet.getLong("cid"), resultSet.getLong("uid"), resultSet.getString("content"));
        }
    }
}
