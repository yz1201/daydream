package cn.dbdj1201.mbeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author yz1201
 * @date 2020-08-02 19:29
 **/
public class HelloBeans {

    private String text;

    private Map<Integer, String> users;

    public void setText(String text) {
        this.text = text;
    }

    public void setUsers(Map<Integer, String> users) {
        this.users = users;
    }

    private Set<String> names;

    private Properties prop;

    private List<Integer> list;

    public void setNames(Set<String> names) {
        this.names = names;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public void hello() {
        System.out.println("hello java");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "HelloBeans{" +
                "text='" + text + '\'' +
                ", users=" + users +
                ", names=" + names +
                ", prop=" + prop +
                ", list=" + list +
                '}';
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloBeans hello = (HelloBeans) ac.getBean("hello");
        hello.hello();
    }

}
