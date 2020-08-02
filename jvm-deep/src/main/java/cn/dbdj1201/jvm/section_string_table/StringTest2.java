package cn.dbdj1201.jvm.section_string_table;

import java.util.Arrays;

/**
 * @author yz1201
 * @date 2020-08-02 14:19
 **/
public class StringTest2 {

    String text = "hello";
    char[] ch = {'a', 'b', 'c', 'd'};

    public void change(String text, char[] cs) {
        this.text = "hi";
        cs[0] = 'z';
    }

    @Override
    public String toString() {
        return "StringTest2{" +
                "text='" + text + '\'' +
                ", ch=" + Arrays.toString(ch) +
                '}';
    }

    public static void main(String[] args) {
        StringTest2 stringTest2 = new StringTest2();
        stringTest2.change(stringTest2.text,stringTest2.ch);
        System.out.println(stringTest2);
    }
}
