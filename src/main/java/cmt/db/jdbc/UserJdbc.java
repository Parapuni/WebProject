package cmt.db.jdbc;

import cmt.db.api.UserHandler;
import cmt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbc implements UserHandler {

    private final String INSERT_USER = "insert into User(nickname,firstName,lastName,`password`,email,`number`,birthday,avatar) values(?,?,?,?,?,?,?,?);";
    private final String DELETE_USER = "delete from User where uid = ?;";
    private final String UPDATE_USER = "update User set nickname = ?,firstName = ?,lastname = ?,`password` = ?,email = ?,`number` = ?,birthday = ?,avatar = ? where uid = ?;";
    private final String SELECT_USER_BY_ID = "select * from User where uid = ?;";
    private final String SELECT_USER_BY_NAME_AND_PASSWORD = "select * from User where nickname = ? and `password` = ?;";
    private final String SELECT_USERS = "select * from User limit ? offset ?;";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 添加一个User
     *
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getNumber());
            ps.setDate(7, user.getBirthday());
            ps.setString(8, user.getAvatar().toString());
            return ps;
        }, keyHolder);
        user.setUid(keyHolder.getKey().longValue());
        return user;
    }

    /**
     * 删除一个User
     *
     * @param uid
     */
    @Override
    public void removeUser(long uid) {
        jdbcTemplate.update(DELETE_USER, uid);
    }

    /**
     * 更新指定User的信息
     *
     * @param user 待更新的user对象，包含其最新信息
     */
    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER,
                user.getNickname(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getNumber(),
                user.getBirthday(),
                user.getAvatar().toString(),
                user.getUid());
    }

    /**
     * 根据id查找User
     *
     * @param uid
     * @return
     */
    @Override
    public User findUserById(long uid) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new UserRowMapper(), uid);
    }

    /**
     * 根据用户名（昵称）和密码查找User
     *
     * @param nickname
     * @param password
     * @return
     */
    @Override
    public User findUserByNameAndPassword(String nickname, String password) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_NAME_AND_PASSWORD, new UserRowMapper(), nickname, password);
    }

    /**
     * 查找指定User列表片段
     *
     * @param offset 列表起始位置
     * @param length 列表长度
     * @return
     */
    @Override
    public List<User> findUsers(int offset, int length) {
        return jdbcTemplate.query(SELECT_USERS, new UserRowMapper(), length, offset);
    }

    private static final class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUid(resultSet.getLong("uid"));
            try {
                user.setAvatar(new URL(resultSet.getString("avatar")));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            user.setNickname(resultSet.getString("nickname"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setBirthday(resultSet.getDate("birthday"));
            user.setNumber(resultSet.getString("number"));
            return user;
        }
    }

    // TODO: 2024/11/20 0020 未实现的方法
    //检查顺序，检查url
}
