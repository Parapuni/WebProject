package cmt.entity;

import com.sun.istack.internal.NotNull;

import java.sql.Date;

/**
 * 评论类
 *
 * @author chu
 */
public class Comment {
    @NotNull
    private long iid;   //评论作品的id
    @NotNull
    private long uid;   //发表评论的用户的id
    @NotNull
    private Date cdate;
    private String userName;
    private String itemTitle;
    private int rating;
    @NotNull
    private String content;  //评论内容

    /**
     * 无参构造函数
     */
    public Comment() {
    }

    /**
     * 有参数的构造函数
     *
     * @param iid     评论id
     * @param uid     发言者id
     * @param cdate 发言时间
     * @param content 内容
     * @param rating 评分
     */
    public Comment(long iid, long uid, Date cdate, String content,int rating) {
        this.iid = iid;
        this.uid = uid;
        this.cdate = cdate;
        this.content = content;
        this.rating = rating;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
