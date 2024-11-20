package cmt.entity;

import com.sun.istack.internal.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;

/**
 * User类，存储用户信息
 *
 * @author chu
 */
public class User {
    @NotNull
    private long uid; //用户id，primary key，使用identity创建，自动递增
    @NotNull
    private String password;
    @NotNull
    private String nickname;//昵称，登录使用的用户名
    private String firstName;//真名的名
    private String lastName;//真名的姓
    @NotNull
    private String email;//邮箱
    private String number;//手机号
    private Date birthday;//生日
    private URL avatar;//头像，存储在服务器中，但是在数据库中只存放其URI

    /**
     * 无参的默认构造函数
     */
    public User() {
    }

    /**
     * 构造器，uid、nickname、email不可留空，其余不要求
     *
     * @param uid       用户的uid
     * @param password  密码
     * @param nickname  昵称
     * @param firstName 名
     * @param lastName  姓
     * @param email     邮箱
     * @param number    电话号码
     * @param birthday  生日
     * @param avatar    头像的URL
     */
    public User(long uid, String password, String nickname, String firstName, String lastName, String email, String number, java.sql.Date birthday, URL avatar) {
        this.uid = uid;
        this.password = password;
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.birthday = birthday;
        this.avatar = avatar;
    }

    public User (String nickname, String password, String email) throws MalformedURLException {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.avatar = new URL("http://localhost:8080/imagines/presplash_background.png");
        this.birthday = new Date(0);
        this.firstName = "";
        this.lastName = "";
        this.number = "";
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public URL getAvatar() {
        return avatar;
    }

    public void setAvatar(URL avatar) {
        this.avatar = avatar;
    }
}
