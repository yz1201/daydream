package cn.dbdj1201.cos.cap8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yz1201
 * @date 2020-06-27 14:04
 **/
@Slf4j(topic = "c.TestReadWriteLock")
public class TestReadWriteLock {

    public static void main(String[] args) {
//        ExecutorService pool = Executors.newFixedThreadPool(2);
        DataContainer dc = new DataContainer();
        new Thread(dc::write, "t1").start();
        new Thread(dc::write, "t2").start();
    }

}

@Slf4j(topic = "c.DataContainer")
class DataContainer {
    private Object data;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read() {
        log.debug("retrieve read lock");
        r.lock();
        try {
            log.debug("read sth");
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("free read lock");
            r.unlock();
        }
        return data;
    }

    public void write() {
        log.debug("retrieve write lock");
        w.lock();
        try {
            log.debug("write");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("free write lock");
            w.unlock();
        }
    }

}
