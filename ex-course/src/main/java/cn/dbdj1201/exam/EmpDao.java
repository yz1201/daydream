package cn.dbdj1201.exam;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.Properties;

/**
 * @author yz1201
 * @date 2020-07-10 15:47
 **/
public class EmpDao {


    public void addSal() {
        Properties pro = new Properties();
        JdbcTemplate template = null;
        try {
            pro.load(EmpDao.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            template = new JdbcTemplate(
                    DruidDataSourceFactory.createDataSource(pro)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert template != null;
        int affectedRows = template.update("update emp set sal = sal * 1.35 where YEAR(NOW()) - YEAR(hiredate) >= 35");
        if (affectedRows>0){
            System.out.println("加薪成功");
        }else {
            System.out.println("出问题了");
        }

    }
}
