package cn.dbdj1201.demo2.day09;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author yz1201
 * @date 2020-06-26 15:50
 **/
@Slf4j(topic = "c.Test062603")
public class Test062603 {

    public static void main(String[] args) {
//        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        int sum = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).parallel().sum();
//        log.debug("sum {}", sum);

//        Thread t1 = new Thread(Test062603::streamTest);
//        Thread t2 = new Thread(Test062603::streamTest2);
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        test0();
    }

    public static void streamTest() {
        //第一个并行流
        try {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            numbers.parallelStream().forEach(num -> log.debug("first time to run"));
            TimeUnit.MILLISECONDS.sleep(5000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void streamTest2() {
        //第2个并行流
        try {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            numbers.parallelStream().forEach(num -> log.debug("second time to run"));
            TimeUnit.MILLISECONDS.sleep(5000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test0() {
        Instant start = Instant.now();
        double sum = LongStream.rangeClosed(0, 1000000000L).parallel().sum();
        log.debug("sum {}", sum);
        Instant end = Instant.now();
        log.debug("consume {}", Duration.between(start,end).toMillis());
    }

}
