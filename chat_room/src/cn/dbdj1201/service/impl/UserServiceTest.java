package cn.dbdj1201.service.impl;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

/**
 * @author yz1201
 * @date 2020-07-09 10:41
 **/
public class UserServiceTest {

    @Test
    public void login() {
        Jedis jedis  = new Jedis("localhost");
//        jedis.set("test1","yz1201");
//        System.out.println(jedis.get("test1"));
        jedis.setex("test",1200,"test");
    }
}