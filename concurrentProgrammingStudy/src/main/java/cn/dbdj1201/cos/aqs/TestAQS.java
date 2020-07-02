package cn.dbdj1201.cos.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author yz1201
 * @date 2020-06-26 19:21
 **/
@Slf4j(topic = "c.TestAQS")
public class TestAQS {

    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            log.debug("lock 1");
            lock.lock();
            log.debug("lock 2");
            try {
                log.debug("t1 locking");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                log.debug("t1 unlocking");
                lock.unlock();
            }
        }, "t1").start();

//        new Thread(() -> {
//            lock.lock();
//            try {
//                log.debug("t2 locking");
//            } finally {
//                log.debug("t2 unlocking");
//                lock.unlock();
//            }
//        },"t2").start();
    }
}

/**
 * 自定义锁(不可重入锁)
 */
class MyLock implements Lock {

    /**
     * 独占锁
     * 同步器类
     */
    private static class MySync extends AbstractQueuedLongSynchronizer {

        @Override
        protected boolean tryAcquire(long arg) {
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                //加锁，并且设置owner为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(long arg) {
            assert arg == 1;
            if (!isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0L);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final MySync sync = new MySync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
