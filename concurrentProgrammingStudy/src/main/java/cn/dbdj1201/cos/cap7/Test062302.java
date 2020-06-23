package cn.dbdj1201.cos.cap7;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * @author yz1201
 * @date 2020-06-23 14:05
 **/
@Slf4j(topic = "c.Test062302")
public class Test062302 {
    public static void main(String[] args) {
        test1();
    }

    private static void test0() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    log.debug("{}", sdf.parse("1951-04-21"));
                } catch (ParseException e) {
                    log.error("error: ", e);
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void test1(){
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> log.debug("date - {}", sdf.parse("1951-04-21"))).start();
        }
    }
}
