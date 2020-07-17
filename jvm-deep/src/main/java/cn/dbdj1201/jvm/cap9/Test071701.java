package cn.dbdj1201.jvm.cap9;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-07-17 9:58
 **/
@Slf4j(topic = "c.Test071701")
public class Test071701 {

    public static void main(String[] args) {
        String str = "       ";
        String trim = str.trim();
        log.debug("||{}||", trim);
        log.debug("{}","".equals(trim));
    }
}
