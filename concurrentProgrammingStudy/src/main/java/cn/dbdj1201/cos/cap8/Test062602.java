package cn.dbdj1201.cos.cap8;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-06-26 13:24
 **/
@Slf4j(topic = "c.Test062602")
public class Test062602 {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = now.withHour(13).withMinute(35).withNano(0).with(DayOfWeek.FRIDAY);
        if (time.isBefore(now)) {
            time = time.plusWeeks(1L);
        }

        long delay = Duration.between(now, time).toMillis();
//        int period = 7 * 24 * 3600 * 1000;
        int period = 2000;
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.scheduleWithFixedDelay(() -> log.debug("right time"), delay, period, TimeUnit.MILLISECONDS);
    }
}
