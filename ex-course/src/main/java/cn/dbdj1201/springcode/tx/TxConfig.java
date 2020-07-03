package cn.dbdj1201.springcode.tx;

import cn.dbdj1201.springcode.config.MainConfigOfAOP;
import cn.dbdj1201.util.DataSourceUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author yz1201
 * @date 2020-07-03 13:30
 **/
@Slf4j(topic = "c.TxConfig")
@Configuration
@EnableTransactionManagement
@ComponentScan("cn.dbdj1201.springcode")
public class TxConfig {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(DataSourceUtils.PropertiesBuilder.pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
