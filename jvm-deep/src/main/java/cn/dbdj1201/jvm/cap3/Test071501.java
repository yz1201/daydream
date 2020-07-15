package cn.dbdj1201.jvm.cap3;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-07-15 9:15
 **/
@Slf4j(topic = "c.Test071501")
public class Test071501 {

    public static void main(String[] args) {
        log.debug("{}", test01(2));
//        test01();
    }

    public static int test01(int num) {
//        byte i = 15;
//        int j = 8;
//        int m = 2000;
//        i++;
//        ++i;
//        int k = i + j;
//        return k;

//        int i = 0;
//        int a = ++i;
//        int b = a++;
        return ++num;
//        log.info("{}", k);
    }

    private void test1() {
        int i = 0;
    }

    static void test2() {
        int a = 1;
    }

    final void test4() {
        int a = 1;
    }

    private void test3() {
        test1();
        test2();
        test4();
        new Thread(()->{
            int a = 0;
            int b = a+1;
        });
        int i=1;
    }
}
