package cn.dbdj1201.jvm.cap9;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yz1201
 * @date 2020-07-16 19:39
 **/
public class JVMTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "weasdasda";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("遍历次数为 " + i);
        }
    }
}
