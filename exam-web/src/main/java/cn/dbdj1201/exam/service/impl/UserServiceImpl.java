package cn.dbdj1201.exam.service.impl;

import cn.dbdj1201.exam.dao.IUserDao;
import cn.dbdj1201.exam.dao.impl.UserDaoImpl;
import cn.dbdj1201.exam.entity.User;
import cn.dbdj1201.exam.service.IUserService;

/**
 * @author yz1201
 * @date 2020-07-31 18:28
 **/
public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        System.out.println("现在开始调用登录业务");
        User login = this.userDao.login(username, password);
        System.out.println("登录业务调用结束");
        return login;
    }
}
