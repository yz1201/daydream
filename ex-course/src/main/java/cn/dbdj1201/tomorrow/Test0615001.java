package cn.dbdj1201.tomorrow;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author yz1201
 * @date 2020-06-15 19:09
 **/
public class Test0615001 {
    private static String className;
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties prop = new Properties();
        try {
            prop.load(Objects.requireNonNull(Test0615001.class
                    .getClassLoader().getResourceAsStream("mysql.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        className = prop.getProperty("className");
        url = prop.getProperty("url");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

    }

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Connection conn = DriverManager.getConnection(url,
                username, password);
        PreparedStatement psmt = conn.prepareStatement("select * from tb_t2");
        ResultSet resultSet = psmt.executeQuery();
        List<Person001> person001s = new ArrayList<>();
        while (resultSet.next()) {
            Person001 person001 = new Person001();
            person001.setId(resultSet.getLong(1));
            person001.setName(resultSet.getString(2));
            person001.setAge(resultSet.getInt(3));
            person001.setStory(resultSet.getString(4));
            person001s.add(person001);
        }

        person001s.forEach(System.out::println);

    }
}

class Person001 {
    private Long id;
    private String name;
    private Integer age;
    private String story;

    public Person001() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return "Person001{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", story='" + story + '\'' +
                '}';
    }
}