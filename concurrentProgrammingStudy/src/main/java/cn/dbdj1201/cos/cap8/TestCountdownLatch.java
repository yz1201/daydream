package cn.dbdj1201.cos.cap8;

import cn.dbdj1201.cos.util.IExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-06-29 9:16
 **/
@Slf4j(topic = "c.TestCountdownLatch")
public class TestCountdownLatch {

    public static void main(String[] args) {
//        test0();
//        test1();

        ExecutorService service = IExecutors.newFixedThreadPool(10, 64);
        String[] all = new String[10];
        Random random = new Random();
        CountDownLatch latch = new CountDownLatch(10);

        for (int j = 0; j < 10; j++) {
            int index = j;
            service.submit(() -> {
                try {
                    for (int i = 0; i <= 100; i++) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        all[index] = i + "%";
                        System.out.print("\r" + Arrays.toString(all));
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            log.debug("waiting ... ");
            latch.await();
            System.out.println();
            log.debug("game start!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void test1() {
        CountDownLatch cdl = new CountDownLatch(3);
        ExecutorService pools = IExecutors.newFixedThreadPool(4, 1024);
        pools.submit(() -> {
            try {
                log.debug("t1 ready {}", cdl.getCount());
                TimeUnit.SECONDS.sleep(1);
                cdl.countDown();
                log.debug("t1 end {}", cdl.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pools.submit(() -> {
            try {
                log.debug("t2 ready {}", cdl.getCount());
                TimeUnit.SECONDS.sleep(2);
                cdl.countDown();
                log.debug("t2 end {}", cdl.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pools.submit(() -> {
            try {
                log.debug("t3 ready {}", cdl.getCount());
                TimeUnit.SECONDS.sleep(3);
                cdl.countDown();
                log.debug("t3 end {}", cdl.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pools.submit(() -> {
            try {
                log.debug("awaiting...");
                cdl.await();
                log.debug("let's go {}", cdl.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void test0() {
        CountDownLatch cdl = new CountDownLatch(3);
        new Thread(() -> {
            try {
                log.debug("t1 ready {}", cdl.getCount());
                TimeUnit.SECONDS.sleep(1);
                cdl.countDown();
                log.debug("t1 end {}", cdl.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                log.debug("t2 ready {}", cdl.getCount());
                TimeUnit.SECONDS.sleep(2);
                cdl.countDown();
                log.debug("t2 end {}", cdl.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                log.debug("t3 ready {}", cdl.getCount());
                TimeUnit.SECONDS.sleep(3);
                cdl.countDown();
                log.debug("t3 end {}", cdl.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            log.debug("waiting ... ");
            cdl.await();
            log.debug("let's go.. {}", cdl.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
