package cmt.db.api;


import cmt.entity.Comment;

import java.util.List;

/**
 * Comment数据操作接口
 */
public interface CommentHandler {

    /**
     * 获取所有的评论
     * @param limit 上限
     * @param offset 偏移量
     * @return 评论列表
     */
    List<Comment> findAll(int limit , int offset);
    /**
     * @return 评论的总数
     */
    int countTotal();

    /**
     * 通过comment对象添加评论
     * @param comment 添加的comment对象
     */
    void addComment(Comment comment);

    /**
     *删除特定用户在某物品上的评论
     * @param iid 指定物品的iid
     * @param uid 指定用户的uid
     */

    void removeComment(long iid, long uid);

    /**
     *更改特定用户在特定物品的评论和评分
     * @param iid 指定物品的iid
     * @param uid 指定用户uid
     * @param newContent 新的评论
     * @param newRating 新评分
     */

    void updateCommentContent(long iid, long uid, String newContent, int newRating);

    /**
     *查找特定用户在特定物品上的评论
     * @param uid 特定用户的uid
     * @param iid 特定物品的iid
     * @return 评论
     */
    Comment findComment(long uid,long iid);

    /**
     *通过物品id查找评论
     * @param iid 物品id
     * @param offset 偏移值
     * @param length 上限
     * @return 评论列表
     */

    List<Comment> findCommentsByItemId(long iid, int offset, int length);

    /**
     *对通过物品id查找到的评论计数
     * @param iid 物品iid
     * @return 评论数
     */

    int  countByItemId(long iid);

    /**
     *通过用户id查找评论
     * @param uid 用户的id
     * @param offset 偏移值
     * @param length 上限
     * @return 评论列表
     */

    List<Comment> findCommentsByUserId(long uid, int offset, int length);

    /**
     *对通过用户id查找到的评论计数
     * @param uid 用户id
     * @return 评论数
     */

    int  countByUserId(long uid);

}
