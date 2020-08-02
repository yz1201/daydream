package cn.dbdj1201.jvm.section_string_table;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yz1201
 * @date 2020-08-02 14:56
 **/
public class StringTest3 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++));
        }

    }
}
