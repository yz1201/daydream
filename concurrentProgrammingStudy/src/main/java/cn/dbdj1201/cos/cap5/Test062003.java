package cn.dbdj1201.cos.cap5;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author yz1201
 * @date 2020-06-20 18:41
 **/
@Slf4j(topic = "c.Test062003")
public class Test062003 {
    public static void main(String[] args) {

    }


}

final class SingletonTest implements Serializable {
    private static final SingletonTest SINGLETON_TEST = new SingletonTest();

    private SingletonTest() {
    }

    public static SingletonTest getInstance() {
        return SINGLETON_TEST;
    }

    public Object readResolve() {
        return SINGLETON_TEST;
    }
}

enum SingletonTest2 {
    INSTANCE;
}

//静态内部类实现懒汉式单例，且无并发问题。
final class SingletonTest3 {

    private SingletonTest3() {

    }

    private static class LazyHolder {
        static final SingletonTest3 TEST_3 = new SingletonTest3();
    }

    public static SingletonTest3 getInstance() {
        return LazyHolder.TEST_3;
    }
}