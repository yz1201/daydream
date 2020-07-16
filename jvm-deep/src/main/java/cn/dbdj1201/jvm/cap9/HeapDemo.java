package cn.dbdj1201.jvm.cap9;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-07-16 15:13
 **/
@Slf4j(topic = "c.HeapDemo")
public class HeapDemo {

    public static void main(String[] args) {
        log.debug("start...");
//        try {
//            TimeUnit.SECONDS.sleep(15000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int num = 5 + 1;
        System.out.println(num);

        log.debug("end ... ");
    }
}
