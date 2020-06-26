package cn.dbdj1201.cos.cap8;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yz1201
 * @date 2020-06-26 10:23
 **/
@Slf4j(topic = "c.TestDeadLock")
public class TestDeadLock {
    static final List<String> MENU = Arrays.asList("地三鲜", "宫保鸡丁", "辣子鸡丁", "烤鸡翅");
    static Random random = new Random();

    static String cooking() {
        return MENU.get(random.nextInt(MENU.size()));
    }

    public static void main(String[] args) {
        ExecutorService waiterPool = Executors.newFixedThreadPool(1);
        ExecutorService cookPool = Executors.newFixedThreadPool(1);
        waiterPool.execute(()->{
            log.debug("处理点菜");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Future<String> f = cookPool.submit(()->{
                log.debug("cooking ..");
                TimeUnit.SECONDS.sleep(1);
                return cooking();
            });

            try {
                log.debug("上菜：{}",f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        waiterPool.execute(()->{
            log.debug("处理点菜");
            Future<String> f = cookPool.submit(()->{
                log.debug("cooking ..");
                return cooking();
            });

            try {
                log.debug("上菜：{}",f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
