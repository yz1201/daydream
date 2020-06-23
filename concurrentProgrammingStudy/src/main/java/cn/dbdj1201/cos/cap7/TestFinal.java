package cn.dbdj1201.cos.cap7;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-06-23 16:26
 **/
@Slf4j(topic = "c.TestFinal")
public class TestFinal {

//    final static int A = 20;
//    final static int B = Short.MAX_VALUE + 1;

   final   int a = 20;
   final   int b = Integer.MAX_VALUE;

    final void test1() {

    }

    public static void main(String[] args) {
        new UserFinal1().test();
//        new UserFinal2().test();
    }
}

class UserFinal1 {
    public void test() {
//        System.out.println(TestFinal.A);
//        System.out.println(TestFinal.B);
        System.out.println(new TestFinal().a);
        System.out.println(new TestFinal().b);
        new TestFinal().test1();
    }
}

//class UserFinal2 {
//    public void test() {
//        System.out.println(TestFinal.A);
//    }
//}
