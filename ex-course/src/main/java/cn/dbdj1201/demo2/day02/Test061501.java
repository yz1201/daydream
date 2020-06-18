package cn.dbdj1201.demo2.day02;

import java.util.*;

/**
 * @author yz1201
 * @date 2020-06-15 8:52
 **/
public class Test061501 {
    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();
        people.add(new Person("test1", 12));
        people.add(new Person("test2", 14));
        people.add(new Person("test3", 16));
        people.add(new Person("test4", 1));
        people.add(new Person("test5", 2));
        people.add(new Person("test6", 23));
        people.add(new Person("test7", 7));

//        Collections.sort(people);
        people.sort(Comparator.comparingInt(Person::getAge));
        people.forEach(System.out::println);

    }

    private static void test() {
    }
}

class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    public int compareTo(Person o) {
        return -this.age + o.age;
    }
}
