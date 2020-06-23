package cn.dbdj1201.cos.cap7;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-06-23 16:03
 **/
@Slf4j(topic = "c.TestIPoolDemo")
public class TestIPoolDemo {
    public static void main(String[] args) {
        IDataPool pool = new IDataPool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Connection conn = pool.borrow();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pool.free(conn);
            }).start();
        }
    }
}
