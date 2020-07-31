package cn.dbdj1201.jvm.sectionobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yz1201
 * @date 2020-07-31 9:14
 **/
@AllArgsConstructor
@NoArgsConstructor
public class TestObject implements Cloneable {

    private Customer customer;

    private String name;

    private int age;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        TestObject clone = (TestObject) super.clone();
        clone.setCustomer((Customer) getCustomer().clone());
        return clone;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "customer=" + customer +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
