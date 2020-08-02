package cn.dbdj1201.jvm.section_compiler;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-08-02 10:15
 **/
@Slf4j(topic = "c.JITTest")
public class JITTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        int length = 1000;
        for (int i = 0; i < length; i++) {
            list.add("asdjlasjdalkjl jasdlas");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
