package cn.dbdj1201.jvm.cap9;

import java.io.Serializable;

/**
 * @author yz1201
 * @date 2020-07-18 10:03
 **/
public class MethodInnerStrucTest implements Comparable<String>, Serializable {
    private int num = 10;
    private static String words = "测试方法的内部结构";


    public void test1() {
        int count = 20;
        System.out.println("count = " + count);
    }

    public static int test2(int cal) {
        int result = 0;
        try {
            int value = 30;
            result = value / cal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("");
    }
}
