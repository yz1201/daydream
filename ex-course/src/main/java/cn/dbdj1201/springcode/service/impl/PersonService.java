package cn.dbdj1201.springcode.service.impl;

import cn.dbdj1201.springcode.dao.PersonDao;
import cn.dbdj1201.springcode.service.IPersonService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yz1201
 * @date 2020-07-02 10:41
 **/
@Service
@ToString
public class PersonService implements IPersonService {

    @Autowired
    private PersonDao personDao;


}
