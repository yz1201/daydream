package cn.dbdj1201.exam;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Properties;

/**
 * @author yz1201
 * @date 2020-06-19 13:28
 **/
@Slf4j(topic = "c.Problem4")
public class Problem4 {
    public static void main(String[] args) throws IOException {
        /*
            在项目根目录下创建Student.txt文件,文件内容如下,学生姓名和年龄是以”键值对”形式存在的.
            2.利用所学的Properties类的相关知识,将文件内容读取到项目中,判断”键值对”中是否有刘方的数据,如果有,将其对应的年龄改为18.
            3.利用Properties类的相关知识把修改后的最新数据写入到newstu.txt文件中.
            Student.txt文件内容:
            刘伊=18
            王含=20
            李风风=17
            刘方=16
            马红红=20
            丁磊=18
            方影=21
            姚华华=20
        */

        Properties prop = new Properties();
        prop.load(new FileReader("Student.txt"));
        prop.stringPropertyNames().forEach(key -> {
            //打印，找到指定数据并处理。
            log.debug("key {}", key);
            log.debug("value {}", prop.getProperty(key));
            if (key.equals("刘方")){
                prop.setProperty(key,"18");
            }

        });
        prop.store(new FileWriter("newstu.txt"),"for exam");
    }
}
