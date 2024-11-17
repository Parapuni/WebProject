package cmt.db.jdbc;

import cmt.db.api.UserHandler;
import cmt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJdbc implements UserHandler {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 添加一个User
     *
     * @param user
     */
    @Override
    public void addUser(User user) {

    }

    /**
     * 删除一个User
     *
     * @param id
     */
    @Override
    public void removeUser(long id) {

    }

    /**
     * 更新指定User的信息
     *
     * @param user 待更新的user对象，包含其最新信息
     */
    @Override
    public void updateUser(User user) {

    }

    /**
     * 根据id查找User
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(long id) {
        return null;
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
        return null;
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
        return null;
    }

    private static final class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUid(resultSet.getLong("uid"));
            user.setAvatar(resultSet.getURL("avatar"));
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
}
