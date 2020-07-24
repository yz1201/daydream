package cn.dbdj1201.demo.controller;

import cn.dbdj1201.demo.entities.CommonResult;
import cn.dbdj1201.demo.entities.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yz1201
 * @date 2020-07-24 17:55
 **/
@Controller("/ajax")
@Slf4j
public class AjaxController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/test1")
    public CommonResult<UserModel> ajaxTest() {
        UserModel userModel = new UserModel();
        userModel.setUserId(124141241L);
        userModel.setUsername("test");
        userModel.setPassword("test");
        return new CommonResult<>(200, "i am a handsome man: " + serverPort, userModel);
    }

    @GetMapping("/test2")
    public String test() {
        return "welcome";
    }
}
