package cn.dbdj1201.cos.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author yz1201
 * @date 2020-06-29 9:48
 **/
@Slf4j(topic = "c.IExecutors")
public class IExecutors {

    public static ExecutorService newFixedThreadPool(int nThreads, int capacity) {
        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("dbdj1201-first-pool-%d").build();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                nThreads,
                nThreads,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(capacity),
                factory,
                new ThreadPoolExecutor.AbortPolicy());
        log.debug("wuhu airlines");
        return poolExecutor;
    }

}
