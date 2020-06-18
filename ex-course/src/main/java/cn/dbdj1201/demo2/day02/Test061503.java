package cn.dbdj1201.demo2.day02;

import java.util.stream.Stream;

/**
 * @author yz1201
 * @date 2020-06-15 14:33
 **/
public class Test061503 {
    public static void main(String[] args) {
        test0();
    }

    private static void test0() {
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("12");
//        list.add("123");
//        list.add("1");
//        Stream<String> stream1 = list.stream();
//        long count = stream1.skip(1).limit(5).count();
//        System.out.println(count);

//        int[] arr = {1, 2, 3, 45, 6, 234, 23425};

//        OptionalDouble average = Arrays.stream(arr).average();
//        if (average.isPresent())
//            System.out.println(average.getAsDouble());

//        Integer[] arr123 = {1,2,3,4};
//        Stream<Integer> arr1231 = Stream.of(arr123);
//
//        Stream<int[]> arr1 = Stream.of(arr);
//        arr1.forEach(System.out::println);
//        IntStream stream = Arrays.stream(arr);
//        stream.sorted().forEach(System.out::println);
//        Stream.concat(stream.boxed(),stream1).skip(3).limit(8).distinct().forEach(System.out::println);

//        stream1.sorted((s1,s2)->s2.length()-s1.length()).forEach(System.out::println);

//        double asDouble = stream1.mapToDouble(Double::parseDouble).average().getAsDouble();
//        System.out.println(asDouble);
//        System.out.println( stream.filter(i->i>1000000).count());
        Stream<String> stream2 = Stream.of("12", "14", "23", "35", "15");
        stream2.skip(2).filter(s->s.startsWith("1"));
        System.out.println(stream2.count());
    }
}
