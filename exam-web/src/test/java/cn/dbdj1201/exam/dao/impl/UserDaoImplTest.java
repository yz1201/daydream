package cn.dbdj1201.exam.dao.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yz1201
 * @date 2020-07-31 18:24
 **/
public class UserDaoImplTest {

    @Test
    public void login() {
        System.out.println(new UserDaoImpl().login("test", "test"));
    }
}