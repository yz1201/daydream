package cn.dbdj1201.jvm.cap9;

import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-07-17 9:37
 **/
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.nanoTime();
        int len = 10000000;
        for (int i = 0; i < len; i++) {
            alloc();
        }

        long end = System.nanoTime();
        System.out.println("assume: " + (end - start)+" ns");
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void alloc(){
        User user = new User();
    }

    static class User{

    }
}
