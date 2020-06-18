package cn.dbdj1201.demo.controller;

import cn.dbdj1201.demo.entities.CommonResult;
import cn.dbdj1201.demo.entities.UserModel;
import cn.dbdj1201.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yz1201
 * @date 2020-06-18 14:02
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public CommonResult<String> login(String username, String password) {
        boolean loginRes = this.userService.login(username, password);
        log.info(username + "-> " + password);
        if (loginRes)
            return new CommonResult<>(200, "登录成功", username + "， 欢迎登录");
        else
            return new CommonResult<>(444, "登录失败", null);
    }

}
