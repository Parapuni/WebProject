package cmt.db.api;


import cmt.entity.Comment;

import java.util.List;

/**
 * Comment数据操作接口
 */
public interface CommentHandler {
    int countTotal();
    void addComment(Comment comment);

    void removeComment(long iid, long uid);

    void updateCommentContent(long iid, long uid, String newContent);

    List<Comment> findCommentsByItemId(long iid, int offset, int length);

    List<Comment> findCommentsByUserId(long uid, int offset, int length);
}
