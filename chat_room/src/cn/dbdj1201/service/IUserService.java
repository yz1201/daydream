package cn.dbdj1201.service;

import cn.dbdj1201.entity.PageBean;
import cn.dbdj1201.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author yz1201
 * @date 2020-07-09 8:40
 **/
public interface IUserService {

    boolean login(String username, String password);

    boolean register(String username, String password);

    List<User> findByUsername(String username);

    /**
     * @return 查询全部用户信息
     */
    List<User> findAll();

    void addUser(User newUser);

    void deleteByUserId(Integer userId);

    User findByUserId(Integer id);

    void updateUser(User updateUser);

    void delSelectedUsers(List<Integer> ids);

    PageBean<User> findByPages(Integer currentPage, Integer rows);

    PageBean<User> findByPagesConditional(int currentPage, int rows, Map<String, String[]> condition);
}
