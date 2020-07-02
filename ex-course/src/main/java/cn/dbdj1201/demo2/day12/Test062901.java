package cn.dbdj1201.demo2.day12;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author yz1201
 * @date 2020-06-29 17:39
 **/
@Slf4j(topic = "c.Test062901")
public class Test062901 {
    public static void main(String[] args) {
//        test0();

        StringJoiner sj = new StringJoiner(":", "[", "]");
        sj.add("George").add("Sally").add("Fred");
        String desiredString = sj.toString();
        log.debug("{}",desiredString);

    }

    private static void test0() {
        HashMap<Student, String> map = new HashMap<>();
        // 这里key为new一个对象
        map.put(new Student(31, "张三"), "1");
        String s = map.get(new Student(31, "张三"));
        System.out.println(s);
    }
}

class Student {
    /**
     * 年龄
     */
    int age;
    /**
     * 名字
     */
    String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}