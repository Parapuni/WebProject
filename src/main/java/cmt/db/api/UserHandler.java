package cmt.db.api;

import cmt.entity.User;

/**
 * User数据操作接口
 */
public interface UserHandler {
    public User findUserByName(String userName, String password);
}
