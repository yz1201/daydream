package cn.dbdj1201.jvm.section_direct_memory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author yz1201
 * @date 2020-07-31 15:33
 **/
public class MaxDirectMemorySizeTest {
    private static final long _1MB = 1024 * 1024;

    public static void main(String[] args) {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = null;
        try {
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
