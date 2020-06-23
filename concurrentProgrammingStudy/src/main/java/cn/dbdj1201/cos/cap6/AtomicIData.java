//package cn.dbdj1201.cos.cap6;
//
//import lombok.extern.slf4j.Slf4j;
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//import java.util.Objects;
//
///**
// * @author yz1201
// * @date 2020-06-23 11:43
// **/
//@Slf4j(topic = "c.AtomicIData")
//public class AtomicIData {
//    public static void main(String[] args) {
//
//    }
//}
//
//class MyAtomicInteger {
//    private static final Unsafe UNSAFE;
//    private static final long valueOffset;
//    private volatile int value;
//
//    static {
//        UNSAFE = UnsafeAccessor.getUnsafe();
//        try {
//            valueOffset = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    public int getValue() {
//        return value;
//    }
//
//    public void decrement(int amount) {
//        while (true) {
//            int prev = this.value;
//            int next = prev - amount;
//            if (UNSAFE.compareAndSwapInt(this, valueOffset, prev, next)) {
//                break;
//            }
//        }
//    }
//}
//
//class UnsafeAccessor {
//    private static final Unsafe unsafe;
//
//    static {
//        try {
//            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//            theUnsafe.setAccessible(true);
//            unsafe = (Unsafe) theUnsafe.get(null);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static Unsafe getUnsafe() {
//        return unsafe;
//    }
//
//}
