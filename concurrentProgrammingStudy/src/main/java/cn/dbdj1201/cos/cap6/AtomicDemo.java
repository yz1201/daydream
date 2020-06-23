package cn.dbdj1201.cos.cap6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yz1201
 * @date 2020-06-21 19:03
 **/
@Slf4j(topic = "c.AtomicDemo")
public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        log.debug("i: {}", integer.incrementAndGet());
        log.debug("i: {}", integer.decrementAndGet());
        log.debug("i: {}", integer.getAndIncrement());
        log.debug("i: {}", integer.getAndDecrement());
        log.debug("i: {}",integer.get());
    }
}
