package cmt.db.jdbc;

import cmt.db.api.CommentHandler;
import cmt.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommentJdbc implements CommentHandler {
    private final String COUNT_TOTAL = "select COUNT(*) from Comment";
    private final String INSERT_COMMENT = "insert into Comment(`iid`, `uid`,`cdate`, `content`,rating) values(?, ?, ?, ?,?);";
    private final String DELETE_COMMENT = "delete from Comment where `iid` = ? and `uid` = ?;";
    private final String UPDATE_COMMENT_CONTENT = "update Comment set `content` = ?,rating = ? where iid = ? and uid = ?;";
    private final String SELECT_COMMENT = "select c.*,i.title,u.nickname\n" +
            "from  `comment` c join `item` i on(c.iid = i.iid) join `user` u on(c.uid = u.uid) " +
            "where c.iid = ? and c.uid = ?;";
    private final String SELECT_COMMENTS_BY_ITEM_ID = "select c.*,i.title,u.nickname\n" +
                                "from  `comment` c join `item` i on(c.iid = i.iid) join `user` u on(c.uid = u.uid) " +
                                "where c.iid = ? limit ? offset ?;";
    private final String SELECT_COMMENTS_BY_USER_ID = "select c.*,i.title,u.nickname\n" +
            "from  `comment` c join `item` i on(c.iid = i.iid) join `user` u on(c.uid = u.uid) " +
            "where c.uid = ? limit ? offset ?;";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int countTotal() {
        return jdbcTemplate.queryForInt(COUNT_TOTAL);
    }

    @Override
    public void addComment(Comment comment) {
        jdbcTemplate.update(INSERT_COMMENT, comment.getIid(), comment.getUid(), comment.getCdate(), comment.getContent(), comment.getRating());
    }

    @Override
    public void removeComment(long iid, long uid) {
        jdbcTemplate.update(DELETE_COMMENT, iid, uid);
    }

    @Override
    public void updateCommentContent(long iid, long uid, String newContent, int newRating) {
        jdbcTemplate.update(UPDATE_COMMENT_CONTENT, newContent,newRating, iid, uid);
    }

    @Override
    public Comment findComment(long uid, long iid) {
        Comment comment = null;
        try {
            comment = jdbcTemplate.queryForObject(SELECT_COMMENT, new CommentRowMapper(), iid, uid);
        }catch (DataAccessException dae){
        }finally {
            return comment;
        }
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
            comment.setCdate(resultSet.getDate("cdate"));
            //先Join表User和Item
            comment.setUserName(resultSet.getString("nickname"));
            comment.setItemTitle(resultSet.getString("title"));
            comment.setRating(resultSet.getInt("rating"));
            comment.setContent(resultSet.getString("content"));
            return comment;
        }
    }
}
