package cn.dbdj1201.demo2.day03;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author yz1201
 * @date 2020-06-17 10:33
 **/
public class Test061701 {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
//        ClassLoader cl = ClassLoader.getSystemClassLoader();
//        System.out.println(cl);
//        System.out.println(cl.getParent());
//        System.out.println(cl.getParent().getParent());
//        test0();
        test2();
    }

//    private static void test0() throws ClassNotFoundException {
//        Person0617 p = new Person0617();
//        System.out.println(Person0617.class);
//        System.out.println(Class.forName("cn.dbdj1201.demo.day03.Person0617"));
//        System.out.println(p.getClass());
//    }

    private static void test1() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("cn.dbdj1201.demo2.day03.Person0617");
        Constructor<?>[] constructors = aClass.getDeclaredConstructors();
//        for (Constructor<?> constructor : constructors) {
//            System.out.println(constructor);
//        } for (Constructor<?> constructor : constructors) {
////            System.out.println(constructor);
////        }
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
            System.out.println(method.getModifiers());
        }
    }

    private static void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> aClass = Class.forName("cn.dbdj1201.demo2.day03.Person0617");
        Constructor<?> con = aClass.getDeclaredConstructor(String.class);
        System.out.println(con.getModifiers());
        con.setAccessible(true);
        Object xixi = con.newInstance("xixi");
        System.out.println(xixi);
        Method method = aClass.getDeclaredMethod("show");
        method.setAccessible(true);
        method.invoke(xixi);
        Field name = aClass.getDeclaredField("name");
        Field age = aClass.getDeclaredField("age");
        name.setAccessible(true);
        age.setAccessible(true);
        name.set(xixi, "haha");
        age.set(xixi, 5);
        System.out.println(xixi);

        Collection<String> list = new ArrayList<>();
        Collection proxyInstance = (Collection) Proxy.newProxyInstance(list.getClass().getClassLoader(),
                list.getClass().getInterfaces(),
                (proxy, method1, args) -> {
//                    System.out.println("come in");
                    Object invoke = null;
                    if (method1.getName().equals("add")) {
//                        System.out.println("??");
                        invoke = method1.invoke(list, args);
                    }
                    return invoke;
                });

        proxyInstance.add(23.53);
        proxyInstance.add(true);
        proxyInstance.add("asdasd");
        proxyInstance.add(4234);
        System.out.println(list);
    }
}

class Person0617 {
    private String name;
    private int age;

    public Person0617() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person0617(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person0617(String name) {
        this.name = name;
    }

    private void show() {
        System.out.println("show show show");
    }

    void test() {
        System.out.println("default");
    }

    protected String test1() {
        System.out.println("protected");
        return "1";
    }

    @Override
    public String toString() {
        return "Person0617{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}

//class Man extends Person0617 {
//    private String story;
//
//    public Man(String story) {
//        this.story = story;
//    }
//}