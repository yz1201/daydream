package cn.dbdj1201.demo2.day03;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-06-17 19:04
 **/
@Slf4j(topic = "c.Test061702")
public class Test061702 {
    public static void main(String[] args) {
//        String s = String.class.newInstance();
//        System.out.println(s);
//        TestMan testMan = TestMan.class.newInstance();
//        System.out.println(testMan);
        PersonDay0617 p = new PersonDay0617("asd", "21");
        log.debug("???? {}", p);
    }
}

class TestMan {
    private String name;
    private int age;

//    public TestMan() {
//    }

    public TestMan(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestMan{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}