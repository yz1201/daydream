//package cn.dbdj1201.cos.cap6;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
///**
// * @author yz1201
// * @date 2020-06-23 11:18
// **/
//
//@Slf4j(topic = "c.TestUnsafe")
//public class TestUnsafe {
//
//    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//        theUnsafe.setAccessible(true);
//        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
////        log.debug("{}", unsafe);
////        System.out.println(unsafe);
//        long id = unsafe.objectFieldOffset(StudentUnsafe.class.getDeclaredField("id"));
//        long name = unsafe.objectFieldOffset(StudentUnsafe.class.getDeclaredField("name"));
//        StudentUnsafe studentUnsafe = new StudentUnsafe();
//
//        unsafe.compareAndSwapInt(studentUnsafe,id,0,1);
//        unsafe.compareAndSwapObject(studentUnsafe,name,null,"test");
//        log.debug("{}",studentUnsafe);
//    }
//}
//
//@Data
//class StudentUnsafe{
//    volatile  int id;
//    volatile String name;
//}
