package cn.dbdj1201.exam.dao;

import cn.dbdj1201.exam.entity.User;

/**
 * @author yz1201
 * @date 2020-07-31 18:19
 **/
public interface IUserDao {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 如果有返回该用户，无返回null
     */
    User login(String username, String password);
}
