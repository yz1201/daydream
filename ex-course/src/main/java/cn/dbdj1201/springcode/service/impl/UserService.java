package cn.dbdj1201.springcode.service.impl;

import cn.dbdj1201.springcode.dao.UserDao;
import cn.dbdj1201.springcode.entity.UserModel;
import cn.dbdj1201.springcode.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yz1201
 * @date 2020-07-03 13:45
 **/

@Slf4j(topic = "c.UserService")
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void insertUser(UserModel userModel) {
        log.debug("not used ? user - {}", userModel);
        this.userDao.insert();
        log.debug("insert successfully!");
        int num = 5/0;
    }
}
