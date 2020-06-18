package cn.dbdj1201.demo2.day02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author yz1201
 * @date 2020-06-15 10:25
 **/
public class Test061502 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        test2("2020-06-15", s -> {
            Date date = null;
            try {
                date = sdf.parse(s);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }, sdf::format);

        test3(new Random().nextInt(101), num -> num > 30, num -> num < 88, num -> num == 66);
    }

//    private static void consume(String text, Consumer<String>... consumers) {
//
//    }

    private static void test2(String date, Function<String, Date> function, Function<Date, String> fun2) {
        System.out.println(function.andThen(fun2).apply(date));
    }

    private static void test3(int num, Predicate<Integer> pre1, Predicate<Integer> pre2, Predicate<Integer> pre3) {
        System.out.println("your number -> "+num);
        System.out.println(pre1.and(pre2).or(pre3).negate().test(num));
    }
}
