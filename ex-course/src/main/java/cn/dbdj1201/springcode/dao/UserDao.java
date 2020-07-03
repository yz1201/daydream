package cn.dbdj1201.springcode.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author yz1201
 * @date 2020-07-03 13:46
 **/
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "insert into tb_user (username, password) values(?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID().toString().substring(0, 5), "root");
    }

}
