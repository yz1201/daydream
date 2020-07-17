package cn.dbdj1201.jvm.cap9;

import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-07-17 8:41
 **/
public class TLABArgsTest {
    public static void main(String[] args) {
        System.out.println("just come to see see");
        try {
            TimeUnit.SECONDS.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
