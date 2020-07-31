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
import java.time.temporal.ChronoUnit;
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

    @Test
    public void testTime8() {
        LocalDate myDate = LocalDate.of(2018, 9, 5);
        LocalDate nowDate = LocalDate.now();
        System.out.println("今天是2018-09-06吗？ " + nowDate.equals(myDate));//今天是2018-09-06吗？ false
        System.out.println(myDate + "是否在" + nowDate + "之前？ " + myDate.isBefore(nowDate));//2018-09-05是否在2018-09-06之前？ true
        System.out.println(myDate + "是否在" + nowDate + "之后？ " + myDate.isAfter(nowDate));//2018-09-05是否在2018-09-06之后？ false
    }

    @Test
    public void testTime9() {
        LocalDate nowDate = LocalDate.now();
        System.out.println(nowDate);//当前日期
        System.out.println(nowDate.minusYears(1));//一年前
        System.out.println(nowDate.minusMonths(1));//一月前
        System.out.println(nowDate.minusWeeks(1));//一周前
        System.out.println(nowDate.minusDays(1));//一天前

        System.out.println(nowDate.plusYears(1));//一年后
        System.out.println(nowDate.plusMonths(1));//一月后
        System.out.println(nowDate.plusWeeks(1));//一周后
        System.out.println(nowDate.plusDays(1));//一天后
    }

    @Test
    public void testTime10() {
        LocalTime nowTime = LocalTime.now();
        System.out.println(nowTime);//当前时间
        System.out.println(nowTime.minusHours(1));//一小时前
        System.out.println(nowTime.minusMinutes(1));//一分钟前
        System.out.println(nowTime.minusSeconds(1));//一秒前
        System.out.println(nowTime.minusNanos(1));//一纳秒前

        System.out.println(nowTime.plusHours(1));//一小时后
        System.out.println(nowTime.plusMinutes(1));//一分钟后
        System.out.println(nowTime.plusSeconds(1));//一秒后
        System.out.println(nowTime.plusNanos(1));//一纳秒后
    }

    @Test
    public void testTime11() {
        Instant instant = Instant.now();
        System.out.println("当前时间戳是：" + instant);//当前时间戳是：2018-09-06T10:14:29.460Z
        Date date = Date.from(instant);
        System.out.println("当前时间戳是：" + date);//当前时间戳是：Thu Sep 06 18:14:29 CST 2018
        instant = date.toInstant();
        System.out.println(instant);
    }

    @Test
    public void testTime12() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);//2018-09-06T18:22:47.366
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ldtStr = ldt.format(dtf);

        System.out.println(ldtStr);//2018-09-06 18:22:47
        String ldtStr1 = dtf.format(ldt);
        System.out.println(ldtStr1);//2018-09-06 18:22:47
    }

    @Test
    public void testTime13() {
        LocalDate today = LocalDate.now();
        System.out.println(today);//2018-09-06
        LocalDate birthDate = LocalDate.of(1994, 10, 21);
        System.out.println(birthDate);//1990-10-01

        Period period = Period.between(birthDate, today);//第二个参数减第一个参数
        System.out.printf("年龄 : %d 年 %d 月 %d 日", period.getYears(), period.getMonths(), period.getDays());//年龄 : 27 年 11 月 5 日
    }

    @Test
    public void testTime14() {
        LocalDateTime today = LocalDateTime.now();
        System.out.println(today);
        LocalDateTime birthDate = LocalDateTime.of(1990,10,1,10,50,30);
        System.out.println(birthDate);

        Duration duration = Duration.between(birthDate, today);//第二个参数减第一个参数
        System.out.println(duration.toDays());//两个时间差的天数
        System.out.println(duration.toHours());//两个时间差的小时数
        System.out.println(duration.toMinutes());//两个时间差的分钟数
        System.out.println(duration.toMillis());//两个时间差的毫秒数
        System.out.println(duration.toNanos());//两个时间差的纳秒数
    }
    @Test
    public void testTime15(){
        LocalDateTime today = LocalDateTime.now();
        System.out.println(today);
        LocalDateTime birthDate = LocalDateTime.of(1990,10,1,10,50,30);
        System.out.println(birthDate);

        System.out.println("相差的年数：" + ChronoUnit.YEARS.between(birthDate, today));
        System.out.println("相差的月数：" + ChronoUnit.MONTHS.between(birthDate, today));
        System.out.println("相差的周数：" + ChronoUnit.WEEKS.between(birthDate, today));
        System.out.println("相差的天数：" + ChronoUnit.DAYS.between(birthDate, today));
        System.out.println("相差的时数：" + ChronoUnit.HOURS.between(birthDate, today));
        System.out.println("相差的分数：" + ChronoUnit.MINUTES.between(birthDate, today));
        System.out.println("相差的秒数：" + ChronoUnit.SECONDS.between(birthDate, today));
        System.out.println("相差的毫秒数：" + ChronoUnit.MILLIS.between(birthDate, today));
        System.out.println("相差的微秒数：" + ChronoUnit.MICROS.between(birthDate, today));
        System.out.println("相差的纳秒数：" + ChronoUnit.NANOS.between(birthDate, today));

        System.out.println("相差的半天数：" + ChronoUnit.HALF_DAYS.between(birthDate, today));
        System.out.println("相差的十年数：" + ChronoUnit.DECADES.between(birthDate, today));
        System.out.println("相差的世纪（百年）数：" + ChronoUnit.CENTURIES.between(birthDate, today));
        System.out.println("相差的千年数：" + ChronoUnit.MILLENNIA.between(birthDate, today));
        System.out.println("相差的纪元数：" + ChronoUnit.ERAS.between(birthDate, today));
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