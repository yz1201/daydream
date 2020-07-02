package cn.dbdj1201.springcode.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yz1201
 * @date 2020-07-02 13:38
 **/
@Configuration
public class MainConfig2 {
//


    private static class PropertiesBuilder {
        private static Properties build() {
            Properties pro = new Properties();
            try {
                pro.load(MainConfig2.class.getClassLoader()
                        .getResourceAsStream("mysql.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return pro;
        }
    }

    @Bean
    public DataSource dataSource() {
        Properties pro = PropertiesBuilder.build();
        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

}
