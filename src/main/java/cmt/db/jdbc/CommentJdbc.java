package cmt.db.jdbc;

import cmt.db.api.CommentHandler;
import cmt.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommentJdbc implements CommentHandler {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public void removeComment(long iid, long uid) {

    }

    @Override
    public void updateCommentContent(String newContent) {

    }

    @Override
    public List<Comment> findCommentsByItemId(long iid, int offset, int length) {
        return null;
    }

    @Override
    public List<Comment> findCommentsByUserId(long uid, int offset, int length) {
        return null;
    }

    private static final class CommentRowMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Comment(resultSet.getLong("iid"), resultSet.getLong("uid"), resultSet.getString("content"));
        }
    }
}
