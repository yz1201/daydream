package cn.dbdj1201.cos.cap8;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yz1201
 * @date 2020-06-26 9:04
 **/
@Slf4j(topic = "c.Test062601")
public class Test062601 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
//        test0(pool);
//        test1(pool);
//        test2(pool);

        Future<String> submit1 = pool.submit(() -> {
            log.debug("task running");
            TimeUnit.MILLISECONDS.sleep(1000);
            log.debug("task1 finish");
            return "1";
        });


        Future<String> submit2 = pool.submit(() -> {
            log.debug("task2 running");
            TimeUnit.MILLISECONDS.sleep(1000);
            log.debug("task2 finish");
            return "2";
        });


        Future<String> submit3 = pool.submit(() -> {
            log.debug("task3 running");
            TimeUnit.MILLISECONDS.sleep(1000);
            log.debug("task3 finish");
            return "3";
        });

        log.debug("shutdown");
        pool.shutdown();

//        pool.awaitTermination(20000,TimeUnit.MILLISECONDS);
        List<Runnable> runnables = pool.shutdownNow();

        log.debug("???? {}",runnables);
        runnables.forEach(runnable -> new Thread(runnable).start());

    }

    private static void test2(ExecutorService pool) throws InterruptedException, ExecutionException, TimeoutException {
//        Object o = pool.invokeAny(Arrays.asList(() -> {
//            log.debug("begin1");
//            TimeUnit.MILLISECONDS.sleep(1500);
//            return "1";
//        }, () -> {
//            log.debug("begin2");
//            TimeUnit.MILLISECONDS.sleep(500);
//            return "2";
//        }, () -> {
//            log.debug("begin3");
//            TimeUnit.MILLISECONDS.sleep(2000);
//            return "3";
//        }), 3000L, TimeUnit.MILLISECONDS);
//
//        log.debug("{}", o);
    }

    private static void test1(ExecutorService pool) throws InterruptedException {
        List<Future<Object>> futures = pool.invokeAll(Arrays.asList(() -> {
            log.debug("begin1");
            TimeUnit.MILLISECONDS.sleep(1500);
            return "1";
        }, () -> {
            log.debug("begin2");
            TimeUnit.MILLISECONDS.sleep(500);
            return "2";
        }, () -> {
            log.debug("begin3");
            TimeUnit.MILLISECONDS.sleep(2000);
            return "3";
        }), 3000L, TimeUnit.MILLISECONDS);

        futures.forEach(f -> {
            try {
                log.debug("{}", f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void test0(ExecutorService pool) throws InterruptedException, ExecutionException {
//        Future<String> submit = pool.submit(() -> {
//            log.debug("running");
//            TimeUnit.SECONDS.sleep(5);
//            return "OK";
//        });
//
//        log.debug("submit {}", submit.get());
    }

}
