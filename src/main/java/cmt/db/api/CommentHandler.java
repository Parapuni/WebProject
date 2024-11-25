package cmt.db.api;


import cmt.entity.Comment;

import java.util.List;

/**
 * Comment数据操作接口
 */
public interface CommentHandler {
    public  List<Comment> findAll(int limit , int offset);
    int countTotal();
    void addComment(Comment comment);

    void removeComment(long iid, long uid);

    void updateCommentContent(long iid, long uid, String newContent, int newRating);
    Comment findComment(long uid,long iid);

    List<Comment> findCommentsByItemId(long iid, int offset, int length);

    int  countByItemId(long iid);

    List<Comment> findCommentsByUserId(long uid, int offset, int length);

    int  countByUserId(long uid);

}
