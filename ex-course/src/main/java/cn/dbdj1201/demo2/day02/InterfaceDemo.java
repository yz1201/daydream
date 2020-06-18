package cn.dbdj1201.demo2.day02;

/**
 * @author yz1201
 * @date 2020-06-14 14:37
 **/
public class InterfaceDemo {

    public static void main(String[] args) {
        Inter061401.show();
        new Klass1().method2();
        new Klass1().method3();
    }
}

class Klass1 implements Inter061401 {

}

interface Inter061401 {

    default void method2(){
        System.out.println("m2");
        show();
        method1();
    }

    default void method3(){
        System.out.println("m3");
        show();
        method1();
    }

    static void show(){
        System.out.println("show something");
    }
    static void method1() {
        System.out.println("m1 ???");
    }
}