package cmt.entity;


import com.sun.istack.internal.NotNull;

import java.net.URL;

/**
 * 管理员类
 *
 * @author chu
 */
public class Admin {
    @NotNull
    private long aid;//管理员id，主码，identity创建，自动递增
    @NotNull
    private String adminName;//管理员登录用的用户名
    @NotNull
    private String email;//邮箱
    @NotNull
    private String password;//登录密码
    private String number;//手机号码
    private String avatar;//头像，存储在服务器中，但是在数据库中只存放其URI


    /**
     * 无参构造函数
     */
    public Admin() {

    }

    /**
     * 有参构造器，创建Admin对象时，除了手机号都不应当留空
     *
     * @param aid       管理员id
     * @param adminName 管理员用户名
     * @param email     邮箱
     * @param password  密码
     * @param number    手机号
     * @param avatar    头像的URL
     */
    public Admin(long aid, String adminName, String email, String password, String number, String avatar) {
        this.aid = aid;
        this.adminName = adminName;
        this.email = email;
        this.password = password;
        this.number = number;
        this.avatar = avatar;
    }

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
