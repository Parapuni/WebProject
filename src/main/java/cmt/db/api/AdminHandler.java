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
    void addAdmin(Admin admin);

    /**
     * 根据id删除指定管理员
     *
     * @param aid
     */
    void removeAdmin(Long aid);

    /**
     * 更新指定Admin的数据
     *
     * @param admin
     */
    void updateAdmin(Admin admin);
    /**
     * 根据id查找管理员
     *
     * @param id 管理员id
     * @return id对应的管理员对象
     */
    Admin findAdminById(long id);

    /**
     * 根据用户名和密码查找管理员
     *
     * @param name
     * @param password
     * @return
     */
    Admin findAdminByNameAndPassword(String name, String password);

    /**
     * 获取全部管理员的列表
     *
     * @return 全体管理员列表
     */
    List<Admin> getAllAdmins();

    /**
     * 查询指定长度的Admin列表
     *
     * @param offset 列表起始Admin在全部Admin中的位置
     * @param length 列表长度
     * @return 查询结果
     */
    List<Admin> getAdmins(int offset, int length);

}
