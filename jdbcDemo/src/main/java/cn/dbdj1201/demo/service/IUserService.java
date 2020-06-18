package cn.dbdj1201.demo.service;

import cn.dbdj1201.demo.entities.UserModel;

import java.util.List;

/**
 * @author yz1201
 * @date 2020-06-18 14:26
 **/
public interface IUserService {

    List<UserModel> findAll();

    UserModel findByUserId(Long id);

    boolean login(String username, String password);
}
