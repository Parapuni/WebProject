package cmt.db.jdbc;

import cmt.db.api.AdminHandler;
import cmt.entity.Admin;
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
public class AdminJdbc implements AdminHandler {
    private final String COUNT_TOTAL = "select COUNT(*) from Admin";
    private final String INSERT_ADMIN = "insert into Admin(`adminName`, `email`, `password`, `number`, `avatar`) values(?, ?, ?, ?, ?);";
    private final String DELETE_ADMIN = "delete from Admin where aid = ?;";
    private final String UPDATE_ADMIN = "update Admin set `adminName` = ?, `email` = ?, `password` = ?, `number` = ?, `avatar` = ? where aid = ?;";
    private final String SELECT_ADMIN_BY_ID = "select * from Admin where aid = ?;";
    private final String SELECT_ADMIN_BY_NAME_AND_PASSWORD = "select * from Admin where `adminName` = ? and `password` = ?;";
    private final String SELECT_ADMINS = "select * from Admin limit ? offset ?;";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int countTotal() {
        return jdbcTemplate.queryForInt(COUNT_TOTAL);
    }

    @Override
    public void addAdmin(Admin admin) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ADMIN, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, admin.getAdminName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getNumber());
            ps.setString(5, admin.getAvatar() != null ? admin.getAvatar().toString() : null);
            return ps;
        }, keyHolder);
        admin.setAid((Long) keyHolder.getKey());
    }

    @Override
    public void removeAdmin(Long aid) {
        jdbcTemplate.update(DELETE_ADMIN, aid);
    }

    @Override
    public void updateAdmin(Admin admin) {
        jdbcTemplate.update(UPDATE_ADMIN,
                admin.getAdminName(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getNumber(),
                admin.getAvatar() != null ? admin.getAvatar().toString() : null,
                admin.getAid());
    }

    @Override
    public Admin findAdminById(long id) {
        return jdbcTemplate.queryForObject(SELECT_ADMIN_BY_ID, new AdminRowMapper(), id);
    }

    @Override
    public Admin findAdminByNameAndPassword(String adminName, String password) {
        return jdbcTemplate.queryForObject(SELECT_ADMIN_BY_NAME_AND_PASSWORD, new AdminRowMapper(), adminName, password);
    }

    @Override
    public List<Admin> getAdmins(int offset, int length) {
        return jdbcTemplate.query(SELECT_ADMINS, new AdminRowMapper(), length, offset);
    }

    private static final class AdminRowMapper implements RowMapper<Admin> {
        @Override
        public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
            Admin admin = new Admin();
            admin.setAid(resultSet.getLong("aid"));
            admin.setAdminName(resultSet.getString("adminName"));
            admin.setPassword(resultSet.getString("password"));
            admin.setEmail(resultSet.getString("email"));
            try {
                admin.setAvatar(new URL(resultSet.getString("avatar")));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            admin.setNumber(resultSet.getString("number"));
            return admin;
        }
    }
}
