package cn.dbdj1201.jvm.cap9;

/**
 * @author yz1201
 * @date 2020-07-18 11:04
 **/
public class MethodAreaTest {
    public static void main(String[] args) {
        Order order = null;
        order.hello();
        System.out.println(order.count);
    }
}

class Order {
    public static int count = 1;
    public static final int NUMBER = 2;
    private String str = "A";
    private byte num = 2;
    private char c = 'A';

    public static void hello() {
        System.out.println("hello");
    }
}
