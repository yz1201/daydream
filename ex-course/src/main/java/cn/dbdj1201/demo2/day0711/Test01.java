package cn.dbdj1201.demo2.day0711;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Launcher;

import java.net.URL;

/**
 * @author yz1201
 * @date 2020-07-11 8:46
 **/
@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) {
//        ClassLoader classLoader = Test01.class.getClassLoader();
//        System.out.println(classLoader);
        for (URL url : Launcher.getBootstrapClassPath().getURLs()) {
            log.debug("{}", url);
        }
    }
}
