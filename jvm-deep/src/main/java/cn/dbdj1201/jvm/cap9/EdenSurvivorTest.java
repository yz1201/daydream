package cn.dbdj1201.jvm.cap9;

import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-07-16 17:48
 **/
public class EdenSurvivorTest {
    public static void main(String[] args) {
        System.out.println("我只是来看看");
        try {
            TimeUnit.SECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
