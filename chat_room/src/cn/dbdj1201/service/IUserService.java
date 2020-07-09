package cn.dbdj1201.service;

import cn.dbdj1201.entity.User;

import java.util.List;

/**
 * @author yz1201
 * @date 2020-07-09 8:40
 **/
public interface IUserService {

    boolean login(String username, String password);

    boolean register(String username, String password);

    List<User> findByUsername(String username);
}
