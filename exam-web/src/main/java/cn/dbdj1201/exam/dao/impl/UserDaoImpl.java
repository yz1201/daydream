package cn.dbdj1201.exam.dao.impl;

import cn.dbdj1201.exam.dao.IUserDao;
import cn.dbdj1201.exam.entity.User;
import cn.dbdj1201.exam.utils.DruidUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author yz1201
 * @date 2020-07-31 18:20
 **/
public class UserDaoImpl implements IUserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

    @Override
    public User login(String username, String password) {
        String sql = "select * from t_user where username = ? and password = ?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }
}
