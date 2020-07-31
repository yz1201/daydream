package cn.dbdj1201.jvm.sectionobject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-07-31 9:16
 **/
@Slf4j(topic = "c.CloneTest")
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        TestObject object = new TestObject();

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("abc");
        object.setName("test obj");
        object.setAge(12);
        object.setCustomer(customer);


       log.debug("clone - {}",object.clone());
       log.debug("clone - {}",object);
    }

}
