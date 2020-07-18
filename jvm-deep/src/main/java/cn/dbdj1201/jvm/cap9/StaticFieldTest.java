package cn.dbdj1201.jvm.cap9;

import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-07-18 16:08
 **/
public class StaticFieldTest {
    private static byte[] arr = new byte[1024 * 1024 * 100];

    public static void main(String[] args) {
        System.out.println(StaticFieldTest.arr);
//        try {
//            TimeUnit.SECONDS.sleep(1000000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
