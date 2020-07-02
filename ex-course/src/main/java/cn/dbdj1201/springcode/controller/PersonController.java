package cn.dbdj1201.springcode.controller;

import cn.dbdj1201.springcode.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author yz1201
 * @date 2020-07-02 10:40
 **/
@Controller
public class PersonController {

    @Autowired
    private IPersonService personService;
}
