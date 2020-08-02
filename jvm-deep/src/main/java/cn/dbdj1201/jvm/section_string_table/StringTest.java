package cn.dbdj1201.jvm.section_string_table;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-08-02 14:04
 **/
@Slf4j(topic = "c.StringTest")
public class StringTest {

    public static void main(String[] args) {
//        test();

//        test1();

        String s1 = "abc";
        String s2 = s1.replace('a','m');
        log.info("result- {}",s2);
        log.info("result- {}",s1);
    }

    private static void test1() {
        String s1 = "abc";
        String s2 = "abc";
        s2 += "def";
        log.info("{}", s2);
        log.info("{}", s1);
    }

    private static void test() {
        String s1 = "abc";
        String s2 = "abc";
//        s1 = "hello";
        System.out.println(s1 == s2);

        log.debug("result - {}", s1);
        log.debug("result - {}", s2);
    }
}
