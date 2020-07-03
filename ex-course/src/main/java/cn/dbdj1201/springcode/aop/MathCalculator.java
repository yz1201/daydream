package cn.dbdj1201.springcode.aop;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-07-02 16:18
 **/
@Slf4j(topic = "c.MathCalculator")
public class MathCalculator {
    public int div(int i, int j) {
//        log.debug("cal - {}", this);
        return i / j;
    }
}
