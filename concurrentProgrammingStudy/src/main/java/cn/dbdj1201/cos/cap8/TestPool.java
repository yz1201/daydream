package cn.dbdj1201.cos.cap8;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yz1201
 * @date 2020-06-24 13:31
 **/
@Slf4j(topic = "c.TestPool")
public class TestPool {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000,
                TimeUnit.MILLISECONDS, 1,
                (queue, task) -> {
                    // 1. 死等
//            queue.put(task);
                    // 2) 带超时等待
//            queue.offer(task, 1500, TimeUnit.MILLISECONDS);
                    // 3) 让调用者放弃任务执行
//            log.debug("放弃{}", task);
                    // 4) 让调用者抛出异常
//            throw new RuntimeException("任务执行失败 " + task);
                    // 5) 让调用者自己执行任务
                    task.run();
                });
        for (int i = 0; i < 3; i++) {
            final int j = i;
            threadPool.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}", j);
            });
        }
    }
}

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    //任务队列
    private final BlockingQueue<Runnable> taskQueue;
    //线程集合
    private final HashSet<Worker> workers = new HashSet<>();
    //核心线程数
    private final int coreSize;
    //获取任务的超时时间
    private final long timeout;
    private final TimeUnit timeUnit;
    private final RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;

        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        //当任务数没有超过核心线程数时，直接交给worker对象执行
        //如果任务数超过了，加入任务队列暂存
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("add worker  {} {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
//                taskQueue.put(task);
                //队列已满，死等
                //带超时等待
                //放弃任务执行
                //抛出异常，停止程序
                //调用者自己去执行任务
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //当task不为空，执行任务
            //当task执行完毕，再接着从任务队列获取任务执行
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    log.debug("{} task is running", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.debug("worker {} is removed", this);
                workers.remove(this);
            }
        }
    }
}

@Slf4j(topic = "c.BlockingQueue")
class BlockingQueue<T> {
    //双向链表
    private final Deque<T> deque = new ArrayDeque<>();
    //锁
    private final ReentrantLock lock = new ReentrantLock();
    //消费者条件变量
    private final Condition fullWaitSet = lock.newCondition();
    //生产者条件变量
    private final Condition emptyWaitSet = lock.newCondition();
    //容量
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    //阻塞获取
    public T take() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (deque.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T t) {
        lock.lock();
        try {
            while (deque.size() == capacity) {
                try {
                    log.debug("wait for the task queue {}", t);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.addLast(t);
            log.debug("add to queue {}", t);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }


    //带超时时间的阻塞添加
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            log.debug("timeout -> {}", timeout);
            long nanos = timeUnit.toNanos(timeout);
            while (deque.size() == capacity) {
                try {
                    if (nanos <= 0) {
                        return false;
                    }
                    log.debug("wait for task queue {}", task);
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("add to task queue {}", task);
            deque.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return deque.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if (deque.size() == capacity) {
                rejectPolicy.reject(this, task);
            } else {
                //有空闲
                log.debug("try put add to task queue {}", task);
                deque.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}