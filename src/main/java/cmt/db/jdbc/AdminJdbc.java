package cmt.db.jdbc;

import cmt.db.api.AdminHandler;
import cmt.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Admin数据操作接口的实现，使用Jdbc
 */
@Repository
public class AdminJdbc implements AdminHandler {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 添加一名管理员
     *
     * @param admin 待添加的管理员对象
     */
    @Override
    public void addAdmin(Admin admin) {

    }

    /**
     * 根据id删除指定管理员
     *
     * @param aid
     */
    @Override
    public void removeAdmin(Long aid) {

    }

    /**
     * 更新指定Admin的数据
     *
     * @param admin
     */
    @Override
    public void updateAdmin(Admin admin) {

    }

    /**
     * 根据id查找管理员
     *
     * @param id 管理员id
     * @return id对应的管理员对象
     */
    @Override
    public Admin findAdminById(long id) {
        return null;
    }

    /**
     * 根据用户名和密码查找管理员
     *
     * @param adminName
     * @param password
     * @return
     */
    @Override
    public Admin findAdminByNameAndPassword(String adminName, String password) {
        return null;
    }

    /**
     * 获取全部管理员的列表
     *
     * @return 全体管理员列表
     */
    @Override
    public List<Admin> getAllAdmins() {
        return null;
    }

    /**
     * 查询指定长度的Admin列表
     *
     * @param offset 列表起始Admin在全部Admin中的位置
     * @param length 列表长度
     * @return 查询结果
     */
    @Override
    public List<Admin> getAdmins(int offset, int length) {
        return null;
    }

    private static final class AdminRowMapper implements RowMapper<Admin> {

        @Override
        public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
            Admin admin = new Admin();
            admin.setAid(resultSet.getLong("aid"));
            admin.setAdminName(resultSet.getString("adminName"));
            admin.setPassword(resultSet.getString("password"));
            admin.setEmail(resultSet.getString("email"));
            admin.setAvatar(resultSet.getURL("avatar"));
            admin.setNumber(resultSet.getString("number"));
            return admin;
        }
    }
}

