package cn.dbdj1201.springcode.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;

/**
 * @author yz1201
 * @date 2020-07-02 16:20
 **/
@Aspect
@Slf4j(topic = "c.LogAspect")
public class LogAspect {

    @Pointcut("execution(public * cn.dbdj1201.springcode.aop.MathCalculator.*(..))")
    public void test() {

    }

    @Before("test()")
    public void logStart() {
        log.info("log: div running");
    }

    @After("test()")
    public void logEnd() {
        log.info("div end");
    }

    @AfterReturning("test()")
    public void logReturn() {
        log.info("div return");
    }

    @AfterThrowing("test()")
    public void logException() {
        log.info("div error");
    }
}
