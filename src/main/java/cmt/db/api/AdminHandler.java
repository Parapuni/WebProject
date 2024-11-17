package cmt.db.api;


import cmt.entity.Admin;

import java.util.List;

/**
 * Admin数据操作接口
 */
public interface AdminHandler {
    /**
     * 添加一名管理员
     *
     * @param admin 待添加的管理员对象
     */
    public void addAdmin(Admin admin);

    /**
     * 根据id查找管理员
     *
     * @param id 管理员id
     * @return id对应的管理员对象
     */
    public Admin findAdminById(long id);

    /**
     * 获取全部管理员的列表
     *
     * @return 全体管理员列表
     */
    public List<Admin> getAllAdmins();
}
