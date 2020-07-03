package cn.dbdj1201.util;

import cn.dbdj1201.springcode.config.MainConfig2;

import java.io.IOException;
import java.util.Properties;

/**
 * @author yz1201
 * @date 2020-07-03 13:36
 **/
public class DataSourceUtils {

    public static class PropertiesBuilder {

        public static Properties pro;

        private PropertiesBuilder() {
        }

        static {
            pro = new Properties();
            try {
                pro.load(MainConfig2.class.getClassLoader()
                        .getResourceAsStream("mysql.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        public static Properties build() {
//            Properties pro = new Properties();
//            try {
//                pro.load(MainConfig2.class.getClassLoader()
//                        .getResourceAsStream("mysql.txt"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return pro;
//        }
    }

}
