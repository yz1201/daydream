package cn.dbdj1201.cos.cap8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author yz1201
 * @date 2020-06-27 18:36
 **/
@Slf4j(topic = "c.TestStampedLock")
public class TestStampedLock {

    public static void main(String[] args) {
        DataContainerStamped dcs = new DataContainerStamped(1);
        new Thread(() -> dcs.read(1), "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> dcs.write(257), "t2").start();
    }
}

@Slf4j(topic = "c.DataContainerStamped")
class DataContainerStamped {
    private int data;
    private final StampedLock lock = new StampedLock();

    public DataContainerStamped(int data) {
        this.data = data;
    }

    public int read(int readTime) {
        long stamp = lock.tryOptimisticRead();
        log.debug("optimistic read locking ...{}", stamp);
        try {
            TimeUnit.SECONDS.sleep(readTime);

            if (lock.validate(stamp)) {
                log.debug("read finish ... {}", stamp);
                return data;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("updating to read lock {}", stamp);

        try {
            stamp = lock.readLock();
            log.debug("read lock {}", stamp);
            TimeUnit.SECONDS.sleep(readTime);
            log.debug("read finish {}", stamp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("read unlock {}", stamp);
            lock.unlockRead(stamp);
        }
        return data;
    }

    public void write(int newData) {
        long stamp = lock.writeLock();
        log.debug("write lock {}", stamp);
        try {
            TimeUnit.SECONDS.sleep(2);
            this.data = newData;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("write unlock {}", stamp);
            lock.unlockWrite(stamp);
        }

    }
}
