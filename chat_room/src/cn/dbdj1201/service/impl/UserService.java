package cn.dbdj1201.service.impl;

import cn.dbdj1201.dao.UserDao;
import cn.dbdj1201.entity.User;
import cn.dbdj1201.service.IUserService;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author yz1201
 * @date 2020-07-09 8:41
 **/
public class UserService implements IUserService {

    /**
     * @param username
     * @param password
     * @return true 成功 false 失败
     */
    @Override
    public boolean login(String username, String password) {
        //先从缓存中拿，如果没有，则请求数据库，如果有则保存，登录成功，没有则登录失败。
        Jedis jedis = new Jedis("localhost");
        if (!password.equals(jedis.get(username))) {
            if (new UserDao().login(username, password) == null) {
                return false;
            } else {
                jedis.set(username, password);
            }
        }
        return true;
    }

    @Override
    public boolean register(String username, String password) {
        System.out.println(username);
        List<User> users = this.findByUsername(username);
        if (users.size() > 0) {
            System.out.println("已有相应用户名");
            return false;
        }

        int rows = new UserDao().register(username, password);
        System.out.println("rows - " + rows);
        return rows == 1;
    }

    @Override
    public List<User> findByUsername(String username) {
        return new UserDao().findByUsername(username);
    }
}
