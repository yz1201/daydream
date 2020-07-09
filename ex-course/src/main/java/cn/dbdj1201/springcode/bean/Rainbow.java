package cn.dbdj1201.springcode.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author yz1201
 * @date 2020-07-02 15:23
 **/
@Lazy
@Component
public class Rainbow {

    public Rainbow() {
        System.out.println("rainbow constructor...");
    }
}
