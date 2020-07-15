package cn.dbdj1201.service.impl;

import cn.dbdj1201.dao.UserDao;
import cn.dbdj1201.entity.PageBean;
import cn.dbdj1201.entity.User;
import cn.dbdj1201.service.IUserService;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<User> findAll() {
        return new UserDao().findAll();
    }

    @Override
    public void addUser(User newUser) {
        new UserDao().createUser(newUser);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        new UserDao().deleteById(userId);
    }

    @Override
    public User findByUserId(Integer id) {
        return new UserDao().findByUSerId(id);
    }

    @Override
    public void updateUser(User updateUser) {
        new UserDao().updateUser(updateUser);
    }

    @Override
    public void delSelectedUsers(List<Integer> ids) {
        new UserDao().deleteUserByIds(ids);
    }

    @Override
    public PageBean<User> findByPages(Integer currentPage, Integer rows) {
//        return new UserDao().findUserPages(currentPage,rows);

        UserDao userDao = new UserDao();
        List<User> userPages = userDao.findUserPages((currentPage - 1) * rows, rows);
        int totalCount = userDao.findCounts();
        PageBean<User> pb = new PageBean<>();
        pb.setList(userPages);
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1);
        return pb;
    }

    @Override
    public PageBean<User> findByPagesConditional(int currentPage, int rows, Map<String, String[]> condition) {
        PageBean<User> pb = new PageBean<>();
        UserDao userDao = new UserDao();
        int totalCount = userDao.findCountsConditionally(condition);
        pb.setTotalCount(totalCount);
        List<User> users = userDao.findUserPagesConditionally((currentPage - 1) * rows, rows, condition);
        pb.setCurrentPage(currentPage);
        pb.setTotalPage(totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1);
        pb.setRows(rows);
        pb.setList(users);
        return pb;
    }
}
