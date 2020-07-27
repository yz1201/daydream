package cn.dbdj1201.dao;


import cn.dbdj1201.entity.User;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

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


    private static final JdbcTemplate template;

    static {
        Properties pro = new Properties();
        DataSource dataSource = null;
        try {
            pro.load(UserDao.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert dataSource != null;
        template = new JdbcTemplate(dataSource);
    }


    @Test
    public void test245() {
        String sql = "INSERT INTO article(`author_id`,`category_id`,`views`,`comments`, `title`,`content`) VALUES(?,?,?,?,?,?)";
        int len = 200;
        for (int i = 0; i < len; i++) {
            template.update(sql, i + 40000, 1, i, i, "" + i, "" + i);
        }
    }

    @Test
    public void test246() {
        String sql = "INSERT INTO phone(card) VALUES(?)";
        int len = 20000;
        for (int i = 1; i <= len; i++) {
            template.update(sql, new Random().nextInt(len + 20000) + 20000);
        }
    }

    @Test
    public void test247() {
        String[] poses = {"manager", "dev", "test", "HR"};
        String sql = "INSERT INTO staffs(name, age, pos, add_time) VALUES(?,?,?,?)";
        int len = 20000;
        for (int i = 1; i <= len; i++) {
            template.update(sql, "dbdj1201-" + new Random().nextInt(len * 16), new Random().nextInt(100),
                    poses[new Random().nextInt(poses.length)], new Date());
        }
    }

    @Test
    public void test250() {
//        String[] poses = {"manager", "dev", "test", "HR"};
//        String sql = "select count(*) from phone ";
//        System.out.println(template.queryForObject(sql, int.class));

        template.queryForMap("select * from phone where phoneid = ?", 876)
                .forEach((key, value) -> System.out.println(key + ":" + value));

        System.out.println("************************************************");
        List<Map<String, Object>> maps = template.queryForList("select * from phone where  phoneid < 100");
        maps.forEach(map -> map.forEach((key, value) -> System.out.println(key + ":" + value)));
    }


    @Test
    public void testTime() {
        LocalDate nowDate = LocalDate.now();
        System.out.println("今天的日期：" + nowDate);//今天的日期：2018-09-06
        int year = nowDate.getYear();//年：一般用这个方法获取年
        System.out.println("year：" + year);//year：2018
        int month = nowDate.getMonthValue();//月：一般用这个方法获取月
        System.out.println("month：" + month);//month：9
        int day = nowDate.getDayOfMonth();//日：当月的第几天，一般用这个方法获取日
        System.out.println("day：" + day);//day：6

        int dayOfYear = nowDate.getDayOfYear();//日：当年的第几天
        System.out.println("dayOfYear：" + dayOfYear);//dayOfYear：249

        //星期
        System.out.println(nowDate.getDayOfWeek());//THURSDAY
        System.out.println(nowDate.getDayOfWeek().getValue());//4
        //月份
        System.out.println(nowDate.getMonth());//SEPTEMBER
        System.out.println(nowDate.getMonth().getValue());//9

    }

    @Test
    public void testTime2() {
        LocalTime nowTime = LocalTime.now();
        System.out.println("今天的时间：" + nowTime);//今天的时间：15:33:56.749
        int hour = nowTime.getHour();//时
        System.out.println("hour：" + hour);//hour：15
        int minute = nowTime.getMinute();//分
        System.out.println("minute：" + minute);//minute：33
        int second = nowTime.getSecond();//秒
        System.out.println("second：" + second);//second：56
        int nano = nowTime.getNano();//纳秒
        System.out.println("nano：" + nano);//nano：749000000
    }

    @Test
    public void testTime3() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("今天是：" + nowDateTime);//今天是：2018-09-06T15:33:56.750
        System.out.println(nowDateTime.getYear());//年
        System.out.println(nowDateTime.getMonthValue());//月
        System.out.println(nowDateTime.getDayOfMonth());//日
        System.out.println(nowDateTime.getHour());//时
        System.out.println(nowDateTime.getMinute());//分
        System.out.println(nowDateTime.getSecond());//秒
        System.out.println(nowDateTime.getNano());//纳秒
        //日：当年的第几天
        System.out.println("dayOfYear：" + nowDateTime.getDayOfYear());//dayOfYear：249
        //星期
        System.out.println(nowDateTime.getDayOfWeek());//THURSDAY
        System.out.println(nowDateTime.getDayOfWeek().getValue());//4
        //月份
        System.out.println(nowDateTime.getMonth());//SEPTEMBER
        System.out.println(nowDateTime.getMonth().getValue());//9
        System.out.println(nowDateTime.getMonthValue());
    }

    @Test
    public void testTime4() {
        System.out.println(LocalDate.of(1994, 10, 21));//直接传入对应的年月日
        System.out.println(LocalDate.of(1994, Month.OCTOBER, 21));//相对上面只是把月换成了枚举
        LocalDate birDay = LocalDate.of(1994, 10, 21);
        System.out.println(LocalDate.ofYearDay(1994, birDay.getDayOfYear()));//第一个参数为年，第二个参数为当年的第多少天
        long epochDay = birDay.toEpochDay();
        System.out.println(epochDay);
        System.out.println(LocalDate.ofEpochDay(epochDay));//参数为距离1970-01-01的天数

        System.out.println(LocalDate.parse("1994-09-23"));
        System.out.println(LocalDate.parse("19940923", DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    @Test
    public void testTime5() {
        System.out.println(LocalTime.of(8, 20));//时分
        System.out.println(LocalTime.of(8, 20, 30));//时分秒
        System.out.println(LocalTime.of(8, 20, 30, 150));//时分秒纳秒
        LocalTime mTime = LocalTime.of(8, 20, 30, 150);
        System.out.println("=======================================");
        System.out.println(LocalTime.ofSecondOfDay(mTime.toSecondOfDay()));//参数为距离当天零时的秒数
        System.out.println(LocalTime.ofNanoOfDay(mTime.toNanoOfDay()));//参数为距离当天零时的纳秒数

        System.out.println(LocalTime.parse("08:20:30"));
        System.out.println(LocalTime.parse("082030", DateTimeFormatter.ofPattern("HHmmss")));
    }

    @Test
    public void testTime7() {
        LocalDate birDay = LocalDate.of(2020, 07, 26);
        LocalTime mTime = LocalTime.of(17, 42, 33);
        System.out.println(LocalDateTime.of(birDay, mTime));//参数为LocalDate和LocalTime
        System.out.println(LocalDateTime.of(1991, 11, 11, 8, 20));
        System.out.println(LocalDateTime.of(1991, Month.NOVEMBER, 11, 8, 20));
        System.out.println(LocalDateTime.of(1991, 11, 11, 8, 20, 30));
        System.out.println(LocalDateTime.of(1991, Month.NOVEMBER, 11, 8, 20, 30));
        System.out.println(LocalDateTime.of(1991, 11, 11, 8, 20, 30, 150));
        System.out.println(LocalDateTime.of(1991, Month.NOVEMBER, 11, 8, 20, 30, 150));

        System.out.println(LocalDateTime.parse("1991-11-11T08:20:30"));
        System.out.println(LocalDateTime.parse("1991-11-11 08:20:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Tester {
    private Long id;
    private LocalDateTime birthday;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class PhoneModel {
    private Integer phoneId;
    private Integer card;
}