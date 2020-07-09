package cn.dbdj1201.jvm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-07-09 13:49
 **/
@Slf4j(topic = "c.Test070901")
public class Test070901 {
    public static void main(String[] args) {
        int a= 5;
        int b = 7;
        int i = a+b;
        log.debug("{}",i);
    }
}
