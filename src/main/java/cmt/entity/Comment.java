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
     */
    public Comment(long iid, long uid, String uname, Date cdate, String content) {
        this.iid = iid;
        this.uid = uid;
        this.cdate = cdate;
        this.content = content;
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
}
