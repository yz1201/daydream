package cn.dbdj1201.exam.service;

import cn.dbdj1201.exam.entity.User;

/**
 * @author yz1201
 * @date 2020-07-31 18:27
 **/
public interface IUserService {

    /**
     * 登录业务
     * @param username
     * @param password
     * @return 登录成功返回该用户信息，失败返回空数据
     */
    User login(String username, String password);
}
