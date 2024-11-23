package cmt.db.api;

import cmt.entity.User;

import java.util.List;

/**
 * User数据操作接口
 */
public interface UserHandler {
    int countTotal();
    /**
     * 添加一个User
     *
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 删除一个User
     *
     * @param id
     */
    void removeUser(long id);

    /**
     * 更新指定User的信息
     *
     * @param user 待更新的user对象，包含其最新信息
     */
    void updateUser(User user);

    /**
     * 根据id查找User
     *
     * @param id
     * @return
     */
    User findUserById(long id);

    /**
     * 根据用户名（昵称）和密码查找User
     *
     * @param nickname
     * @param password
     * @return
     */
    User findUserByNameAndPassword(String nickname, String password);


    /**
     * 查找指定User列表片段
     *
     * @param offset 列表起始位置
     * @param length 列表长度
     * @return
     */
    List<User> findUsers(int offset, int length);
}
