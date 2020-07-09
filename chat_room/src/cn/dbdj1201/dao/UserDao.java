package cn.dbdj1201.dao;

import cn.dbdj1201.entity.User;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 * @author yz1201
 * @date 2020-07-09 8:41
 **/
public class UserDao {

    private static JdbcTemplate template;

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

    public User login(String username, String password) {
        User user;
        try {
            user = template.queryForObject("select * from tb_user where username = ? and password = ?",
                    new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public int register(String username, String password) {
        return template.update("insert into tb_user(username, password) values(?, ?)",
                username, password);
    }

    public List<User> findByUsername(String username) {
        List<User> users;
        try {
            users = template.query("select * from tb_user where username = ?",
                    new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }


//    public static void main(String[] args) {
//        System.out.println(new UserDao().login("root", "root"));
//    }
}
