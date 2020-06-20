package cn.dbdj1201.cos.cap5;

/**
 * @author yz1201
 * @date 2020-06-20 13:42
 **/
public class SingletonDemo {

    private static volatile SingletonDemo INSTANCE;

    private SingletonDemo() {
    }

    public static SingletonDemo getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonDemo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonDemo();
                }
            }
        }
        return INSTANCE;
    }
}
