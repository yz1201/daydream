package cn.dbdj1201.cos.cap6;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yz1201
 * @date 2020-06-20 19:25
 **/
@Slf4j(topic = "c.TestAccount")
public class TestAccount {
    public static void main(String[] args) throws InterruptedException {
        Account account = new AccountImpl(10000);
        //Account account2 = new AccountImpl2(10000);
        Account.demo(account);
        log.debug("sleep 1 s");
        TimeUnit.SECONDS.sleep(1);
        //Account.demo(account2);
    }

}

@Slf4j(topic = "c.AccountImpl")
class AccountImpl implements Account {

    private AtomicInteger balanceBake;

    public AccountImpl(int balanceBake) {
        this.balanceBake = new AtomicInteger(balanceBake);
    }

    @Override
    public Integer getBalance() {
        return this.balanceBake.get();
    }

    @Override
    public void withdraw(Integer amount) {
//        this.balanceBake.addAndGet(-amount);
//        this.balanceBake.compareAndSet(this.balanceBake.get(), this.balanceBake.get() - amount);

        while (true) {
            int expect = this.balanceBake.get();
            int val = expect - amount;
//            log.debug("expect:{}", expect);
            if (this.balanceBake.compareAndSet(expect, val)) {
                break;
            }
        }
    }

}

class AccountImpl2 implements Account {
    private int balance;

    public AccountImpl2(Integer balance) {
        this.balance = balance;
    }

    @Override
    public synchronized Integer getBalance() {
        return this.balance;
    }

    @Override
    public void withdraw(Integer account) {
        synchronized (this) {
            this.balance -= account;
        }
    }
}

interface Account {
    Integer getBalance();

    void withdraw(Integer amount);

    static void demo(Account account) {
        List<Thread> ls = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            ls.add(new Thread(() -> account.withdraw(10)));
        }
        long start = System.nanoTime();
        ls.forEach(Thread::start);
        ls.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();
        System.out.println("who: " + account.getBalance() + " cost: " + (end - start) / 1000_000 + "ms");
    }
}

