package cn.dbdj1201.dao;

import cn.dbdj1201.entity.User;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.awt.SunHints;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author yz1201
 * @date 2020-07-09 8:41
 **/
public class UserDao {

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

    public JdbcTemplate getTemplate() {
        return template;
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

    public List<User> findAll() {
        return template.query("select * from tb_user", new BeanPropertyRowMapper<>(User.class));
    }

    public boolean createUser(User newUser) {
        int affectedRows = template.update("insert into tb_user(name,gender,age,address,qq,email) values(?,?,?,?,?,?)",
                newUser.getName(), newUser.getGender(), newUser.getAge(), newUser.getAddress(),
                newUser.getQq(), newUser.getEmail());
        return affectedRows > 0;
    }

    public boolean deleteById(Integer userId) {
        int affectedRows = template.update("delete from tb_user where id = ?", userId);
        return affectedRows > 0;
    }

    public User findByUSerId(Integer id) {
        User user;
        try {
            user = template.queryForObject("select * from tb_user where id = ?", new BeanPropertyRowMapper<>(User.class), id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("error id " + id);
            return null;
        }
        return user;
    }

    public boolean updateUser(User updateUser) {
        int affectedRows = template.update("update tb_user set gender = ?, age=?, address = ?, qq = ?, email = ? where id = ?",
                updateUser.getGender(), updateUser.getAge(), updateUser.getAddress(),
                updateUser.getQq(), updateUser.getEmail(), updateUser.getId());
        return affectedRows > 0;
    }

    public void deleteUserByIds(List<Integer> ids) {
        ids.forEach(this::deleteById);
    }

    public List<User> findUserPages(Integer startIndex, Integer rows) {
        return template.query("select * from tb_user limit ?, ?",
                new BeanPropertyRowMapper<>(User.class), startIndex, rows);
    }

    public int findCounts() {
        return template.queryForObject("select count(1) from tb_user", int.class);
    }


    /**
     * @param condition
     * @return 根据查寻结果集记录数
     */
    public int findCountsConditionally(Map<String, String[]> condition) {
        StringBuilder sb = new StringBuilder("select count(1) from tb_user where 1 = 1 ");
        List<String> needValues = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : condition.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            String value = values[0];
            if (key.equals("currentPage") || "rows".equals(key)) {
                continue;
            }

            if (value != null && !"".equals(value)) {
                sb.append(" and ").append(key).append(" like ? ");
                needValues.add("%" + value + "%");
            }

        }

        System.out.println("sql: " + sb.toString());
        System.out.println(needValues);
        return template.queryForObject(sb.toString(), int.class, needValues.toArray());
    }


    public List<User> findUserPagesConditionally(int startIndex, int rows, Map<String, String[]> condition) {
        StringBuilder sb = new StringBuilder("select * from tb_user where 1 = 1 ");
        List<Object> params = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : condition.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            String value = values[0];
            if (key.equals("currentPage") || "rows".equals(key)) {
                continue;
            }

            if (value != null && !"".equals(value)) {
                sb.append(" and ").append(key).append(" like ? ");
                params.add("%" + value + "%");
            }
        }

        sb.append(" limit ?, ?");

        params.add(startIndex);
        params.add(rows);

        System.out.println("sql: " + sb.toString());
        System.out.println(params);

        return template.query(sb.toString(), new BeanPropertyRowMapper<>(User.class), params.toArray());
    }


//    public StringBuilder coditionsResolve(String sqlTemplate, Map<String, String[]> conditions) {
//        StringBuilder sb = new StringBuilder(sqlTemplate);
//        List<Object> params = new ArrayList<>();
//        for (Map.Entry<String, String[]> entry : conditions.entrySet()) {
//            String key = entry.getKey();
//            String[] values = entry.getValue();
//            String value = values[0];
//            if (key.equals("currentPage") || "rows".equals(key)) {
//                continue;
//            }
//
//            if (value != null && !"".equals(value)) {
//                sb.append(" and ").append(key).append(" like ? ");
//                params.add("%" + value + "%");
//            }
//        }
//
//        return sb;
//    }

}
