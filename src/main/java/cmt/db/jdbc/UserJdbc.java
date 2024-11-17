package cmt.db.jdbc;

import cmt.db.api.UserHandler;
import cmt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserJdbc implements UserHandler {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
