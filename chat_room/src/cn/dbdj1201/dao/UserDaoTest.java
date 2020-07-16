package cn.dbdj1201.dao;


import cn.dbdj1201.entity.User;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yz1201
 * @date 2020-07-09 9:03
 **/
public class UserDaoTest {


    public void login() {
    }

    @Test
    public void register() {
//        UserDao userDao = new UserDao();
//        int len = 10;
//        for (int i = 0; i < len; i++) {
//            System.out.println(userDao.findByUSerId(i + 1));
//        }

        User user = new User();
        user.setId(101);
        user.setAge(2000);
        user.setEmail("2422dad@asdas.com");
        user.setQq("2340249234");
        user.setGender("男");
        user.setAddress("北京北");
        new UserDao().updateUser(user);
    }

    @Test
    public void createUser() {
        int len = 100;
        User user = new User();
        for (int i = 0; i < len; i++) {
            user.setName("adas" + i);
            user.setAge(24 + i);
            user.setAddress("weqweq" + i);
            user.setGender("女");
            user.setEmail("1231@124.com");
            user.setQq("12312" + i);
            new UserDao().createUser(user);
        }

    }

    @Test
    public void deleteUserByIds() {

        JdbcTemplate template = new UserDao().getTemplate();
//        List<Map<String, Object>> maps = template.queryForList("select * from tb_user where id = 1");

        Map<String, Object> objectMap = template.queryForMap("select email from tb_user where id =1");
        System.out.println(objectMap);
        System.out.println("====");
//        maps.forEach(map -> map.entrySet().forEach(System.out::println));

//        new UserDao().deleteUserByIds(Arrays.asList(99, 100));
    }

    @Test
    public void findUserPages() {
//        new UserDao().findUserPages(1,5).forEach(System.out::println);
        System.out.println(new UserDao().findCounts());

    }

    @Test
    public void findCountsConditionally() {
        HashMap<String, String[]> map = new HashMap<>();
        map.put("name", new String[]{"s"});
        map.put("address", new String[]{"w"});
        map.put("email", new String[]{"1"});
        List<User> users = new UserDao().findUserPagesConditionally(0, 50, map);
        System.out.println(users.size());
        users.forEach(System.out::println);
    }
}