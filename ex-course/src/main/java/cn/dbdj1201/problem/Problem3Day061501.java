package cn.dbdj1201.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yz1201
 * @date 2020-06-15 17:47
 **/
public class Problem3Day061501 {
    public static void main(String[] args) {
        /*
        无重复字符的最长子串
            形如：
            abcabcbb - abc - 3
            bbbbbbb - b - 1
            pwwkew - wke - 3
        */
    }

    private static void test0(String text) {
//        List<Character> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            map.put(text.charAt(i), i);
            for (int j = i + 1; j < text.length(); j++) {

            }
        }
    }
}
