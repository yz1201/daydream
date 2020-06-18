package cn.dbdj1201.demo.service.impl;

import cn.dbdj1201.demo.entities.UserModel;
import cn.dbdj1201.demo.mapper.UserModelMapper;
import cn.dbdj1201.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz1201
 * @date 2020-06-18 14:28
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public List<UserModel> findAll() {
        return userModelMapper.selectAll();
    }

    @Override
    public UserModel findByUserId(Long id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean login(String username, String password) {
        UserModel userModel = new UserModel(username, password);
        UserModel loginResult = userModelMapper.selectOne(userModel);
//        System.out.println(" " + loginResult);
        return loginResult != null;
    }
}
