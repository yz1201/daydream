package cn.dbdj1201.springcode.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author yz1201
 * @date 2020-07-02 15:38
 **/
public class ColorFactoryBean implements FactoryBean<Color> {
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
