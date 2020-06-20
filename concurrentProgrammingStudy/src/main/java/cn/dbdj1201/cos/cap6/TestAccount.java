package cn.dbdj1201.cos.cap6;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yz1201
 * @date 2020-06-20 19:25
 **/
@Slf4j(topic = "c.TestAccount")
public class TestAccount {
    public static void main(String[] args) {
        Account accout = new AccountImpl(10000);
        Account.demo(accout);
    }

}

class AccountImpl implements Account {

    private Integer balance;

    public AccountImpl(Integer balance) {
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
        System.out.println(account.getBalance() + " cost: " + (end - start) / 1000_000 + "ms");
    }
}

