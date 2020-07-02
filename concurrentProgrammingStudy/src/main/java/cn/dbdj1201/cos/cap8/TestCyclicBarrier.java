package cn.dbdj1201.cos.cap8;

import cn.dbdj1201.cos.util.IExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author yz1201
 * @date 2020-06-29 10:56
 **/
@Slf4j(topic = "c.TestCyclicBarrier")
public class TestCyclicBarrier {
    public static void main(String[] args) {

        ExecutorService service = IExecutors.newFixedThreadPool(5, 64);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> log.debug("mission accomplished"));

        int circleTimes = 3;
        for (int i = 0; i < circleTimes; i++) {
            int j = i;
            service.submit(() -> {
                try {
                    log.debug(j + " task1 begin ...");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        cyclicBarrier.await();
                        log.debug(j + " task1 end ");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });

            service.submit(() -> {
                try {
                    log.debug(j + " task2 begin ... ");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        cyclicBarrier.await();
                        log.debug(j + " task2 end ");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


//        try {
//            log.debug("go go 1-{}", cyclicBarrier.getNumberWaiting());
//            cyclicBarrier.await();
//            log.debug("go go 2-{}", cyclicBarrier.getNumberWaiting());
//        } catch (InterruptedException | BrokenBarrierException e) {
//            e.printStackTrace();
//        }
    }
}
