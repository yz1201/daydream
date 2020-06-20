package cn.dbdj1201.demo2.day05;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yz1201
 * @date 2020-06-19 11:26
 **/
public class Test061902 {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
//        prop.setProperty("username", "dbdj1201");
//        prop.setProperty("password", "dbdj1201");
        prop.setProperty("用户名", "着火了");
        prop.setProperty("密码", "忘了");
//        prop.stringPropertyNames().forEach(System.out::println);
        for (String name : prop.stringPropertyNames()) {
            System.out.println(name + "->" + prop.getProperty(name));
        }

        prop.store(new FileWriter("D:\\test\\info.txt"),"just for test");

    }
}
