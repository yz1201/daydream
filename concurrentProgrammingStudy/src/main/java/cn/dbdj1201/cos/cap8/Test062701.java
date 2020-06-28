package cn.dbdj1201.cos.cap8;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author yz1201
 * @date 2020-06-27 16:46
 **/
@Slf4j(topic = "c.Test062701")
public class Test062701 {

    public static final int THREAD_POOL_SIZE = 16;

    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("dbdj1201-test-first-pool-%d").build();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                THREAD_POOL_SIZE,
                THREAD_POOL_SIZE,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                factory,
                new ThreadPoolExecutor.AbortPolicy());


        int circleNum = 1000;
        for (int i = 0; i < circleNum; i++) {
            int j = i;
            poolExecutor.execute(() -> log.debug("num - {}", j));
        }

        poolExecutor.shutdown();
        try {
            poolExecutor.awaitTermination(1000,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("i am done, how about you? ");
    }
}
