package cn.dbdj1201.cos.cap8;

import cn.dbdj1201.cos.util.IExecutors;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author yz1201
 * @date 2020-06-29 13:39
 **/
@Slf4j(topic = "c.Test062901")
public class Test062901 {

    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

    private static final Object obj = new Object();

    public static void main(String[] args) {
//        fileGenerator();

        log.debug("task begin");

        Map<String, LongAdder> integerMap = test0(ConcurrentHashMap::new, (map, words) -> {
            for (String word : words) {
//                synchronized (map) {
//                    map.put(word, map.get(word) == null ? 1 : map.get(word) + 1);
//                }

                LongAdder adder = map.computeIfAbsent(word, key -> new LongAdder());
                adder.increment();
            }
        });
        log.debug("{}", integerMap);
    }

    private static <V> Map<String, V> test0(Supplier<Map<String, V>> supplier, BiConsumer<Map<String, V>, List<String>> consumer) {
        ExecutorService service = IExecutors.newFixedThreadPool(ALPHA.length(), 32);

        Map<String, V> map = supplier.get();
        for (int i = 1; i <= ALPHA.length(); i++) {
            int j = i;
            service.submit(() -> {
                List<String> words = readFromFile(j);
                consumer.accept(map, words);
            });
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        service.shutdown();
//        if (service.isShutdown())
//            map.forEach((key, value) -> log.debug("key {} - value {}", key, value));
        return map;
    }

    private static List<String> readFromFile(int i) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test\\tmp\\" + i + ".txt")))) {
            while (true) {
                String word = in.readLine();
                if (word == null) {
                    break;
                }
                words.add(word);
            }
            return words;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fileGenerator() {
        int length = ALPHA.length();
        int count = 200;
        List<String> list = new ArrayList<>(length * count);
        for (int i = 0; i < length; i++) {
            char ch = ALPHA.charAt(i);
            for (int j = 0; j < count; j++) {
                list.add(String.valueOf(ch));
            }
        }

        Collections.shuffle(list);

        for (int i = 0; i < length; i++) {
            try (PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("D:\\test\\tmp\\" + (i + 1) + ".txt")
                    )
            )) {
                pw.write(String.join("\n", list.subList(i * count, (i + 1) * count)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
