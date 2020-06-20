package cn.dbdj1201.demo2.day05;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yz1201
 * @date 2020-06-19 8:35
 **/
@Slf4j(topic = "c.Test061901")
public class Test061901 {
    private static final String URL = "D:\\test";
    private static final String FROM = "D:\\test\\myData.txt";
    private static final String TO = "D:\\test\\myDataCopy.txt";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void main(String[] args) {
        test1();
    }

    /*
    复制一下
    */
    private static void test0() {
//        BufferedInputStream bis = null;
//        BufferedOutputStream bos = null;
//        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FROM));
//             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(TO)))
        try (FileInputStream bis = new FileInputStream(FROM);
             FileOutputStream bos = new FileOutputStream(TO)) {
            byte[] bs = new byte[16];
            int len;
            while ((len = bis.read(bs)) != -1) {
//                log.debug("len- {}" ,len);
                log.debug("data {} len-》 {} bs - {}", new String(bs, 0, len), len, bs);
                bos.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    private static void test1() {
        /*
        private String name;
        private int age;
        private String gender;
        private String username;
        private String password;
        */
        Person0619 person06191 = new Person0619("ysj1", 12, "male", "dbdj1201", "dbdj1201");
        Person0619 person06192 = new Person0619("ysj2", 12, "male", "dbdj1201", "dbdj1201");
        Person0619 person06193 = new Person0619("ysj3", 12, "male", "dbdj1201", "dbdj1201");
        Person0619 person06194 = new Person0619("ysj4", 12, "male", "dbdj1201", "dbdj1201");
        Person0619 person06195 = new Person0619("ysj5", 12, "male", "dbdj1201", "dbdj1201");
        Person0619 person06196 = new Person0619("ysj6", 12, "male", "dbdj1201", "dbdj1201");
        Person0619 person06197 = new Person0619("ysj7", 12, "male", "dbdj1201", "dbdj1201");
        List<Person0619> personList = new ArrayList<>();
        personList.add(person06191);
        personList.add(person06192);
        personList.add(person06193);
        personList.add(person06194);
        personList.add(person06195);
        personList.add(person06196);
        personList.add(person06197);
//        ObjectMapper om = new ObjectMapper();
        String personVal = null;
        try {
            personVal = OBJECT_MAPPER.writeValueAsString(personList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.debug("person {}", personVal);
        System.out.println(personVal);

//        try {
//            List list = OBJECT_MAPPER.readValue(personVal, List.class);
//            list.forEach(System.out::println);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        Map<String, Person0619> collect = personList.stream().collect(Collectors.toMap(p -> p.getName().substring(p.getName().length() - 1),
                p -> p));

        try {
            String s = OBJECT_MAPPER.writeValueAsString(collect);
            log.debug(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}


