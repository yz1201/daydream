package cn.dbdj1201.jvm.cap9;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;

import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-07-17 10:11
 **/
public class ScalarReplace {
    public static void main(String[] args) {
        TimeInterval timer = DateUtil.timer();
        int size = 1000000;
        for (int i = 0; i < size; i++) {
            alloc();
        }
        System.out.println(timer.interval());


        try {
            TimeUnit.SECONDS.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void alloc() {
        Point point = new Point();
        point.x = 1;
        point.y = 2;
//        System.out.println(point);
    }

    static class Point {
        public int x;
        public int y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
