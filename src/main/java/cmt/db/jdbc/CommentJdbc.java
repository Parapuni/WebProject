package cmt.db.jdbc;

import cmt.db.api.CommentHandler;
import cmt.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommentJdbc implements CommentHandler {

    private final String INSERT_COMMENT = "insert into Comment(iid, uid, content) values(?, ?, ?);";
    private final String DELETE_COMMENT = "delete from Comment where iid = ? and uid = ?;";
    private final String UPDATE_COMMENT_CONTENT = "update Comment set content = ? where iid = ? and uid = ?;";
    private final String SELECT_COMMENTS_BY_ITEM_ID = "select * from Comment where iid = ? limit ? offset ?;";
    private final String SELECT_COMMENTS_BY_USER_ID = "select * from Comment where uid = ? limit ? offset ?;";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addComment(Comment comment) {
        jdbcTemplate.update(INSERT_COMMENT, comment.getIid(), comment.getUid(), comment.getContent());
    }

    @Override
    public void removeComment(long iid, long uid) {
        jdbcTemplate.update(DELETE_COMMENT, iid, uid);
    }

    @Override
    public void updateCommentContent(String newContent) {
        // 需要补充评论ID和用户ID作为参数，根据需求可以修改方法签名
        // 例如：传入iid和uid来确定要更新的具体评论
        throw new UnsupportedOperationException("Method updateCommentContent needs specific parameters to identify the comment.");
    }

    @Override
    public List<Comment> findCommentsByItemId(long iid, int offset, int length) {
        return jdbcTemplate.query(SELECT_COMMENTS_BY_ITEM_ID, new CommentRowMapper(), iid, length, offset);
    }

    @Override
    public List<Comment> findCommentsByUserId(long uid, int offset, int length) {
        return jdbcTemplate.query(SELECT_COMMENTS_BY_USER_ID, new CommentRowMapper(), uid, length, offset);
    }

    private static final class CommentRowMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            Comment comment = new Comment();
            comment.setIid(resultSet.getLong("iid"));
            comment.setUid(resultSet.getLong("uid"));
            comment.setContent(resultSet.getString("content"));
            return comment;
        }
    }
}
