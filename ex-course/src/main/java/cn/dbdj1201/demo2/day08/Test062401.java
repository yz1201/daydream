package cn.dbdj1201.demo2.day08;

import cn.dbdj1201.domain.UserModel;
import cn.dbdj1201.util.JDBCUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yz1201
 * @date 2020-06-24 10:59
 **/
@Slf4j(topic = "c.Test062401")
public class Test062401 {
    public static void main(String[] args) {

        /*
            1. 修改1号数据的 salary 为 10000
			2. 添加一条记录
			3. 删除刚才添加的记录
			4. 查询id为1的记录，将其封装为Map集合
			5. 查询所有记录，将其封装为List
			6. 查询所有记录，将其封装为Emp对象的List集合
			7. 查询总记录数
		*/
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        List<UserModel> userModels = template.query("select * from tb_user",
                new BeanPropertyRowMapper<>(UserModel.class));

//        template.query("select * from tb_user", (RowMapper<UserModel>) (rs, rowNum) -> null);
        userModels.forEach(System.out::println);

        int count = template.update("update tb_user set password = 'test1' where user_id = ?",2);
        log.debug("affect rows {}", count);

    }
}
