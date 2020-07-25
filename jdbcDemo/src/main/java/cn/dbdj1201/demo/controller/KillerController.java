package cn.dbdj1201.demo.controller;

import cn.dbdj1201.demo.entities.CommonResult;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author yz1201
 * @date 2020-07-25 18:53
 **/
@RestController
@Slf4j
public class KillerController {

    @PostMapping("/secKill")
    public CommonResult<String> secKillTest() {
        String userId = IdUtil.simpleUUID();
//        log.info("new userId: " + userId);

        int result = doSecKill(userId);

        if (result == 444) {
            return new CommonResult<>(444, "秒杀还没开始，急啥呢？┭┮﹏┭┮");
        } else if (result == 456) {
            return new CommonResult<>(456, "秒杀过了");
        } else
            return new CommonResult<>(200, "秒杀成功，冲，┗|｀O′|┛ 嗷~~");
    }

    public int doSecKill(String userId) {
        Jedis jedis = RedisDS.create().getJedis();
        log.info("welcome user: " + userId);

        String stockKey = "secKill:dbdj1201:stock";
        String userKey = "secKill:dbdj1201:user";
        String spop = jedis.get(stockKey);

        if (StrUtil.isEmpty(spop)) {
            log.info("没这种货");
            return 444;
        }

        int stock = Integer.parseInt(spop);
        if (stock <= 0) {
            log.info("断货了");
            return 444;
        }

        if (jedis.sismember(userKey, userId)) {
            log.info("你秒杀过了哥哥");
            return 456;
        }

        jedis.decr(stockKey);
        jedis.sadd(userKey, userId);

        log.info("秒杀成功");
        return 200;

    }
}
