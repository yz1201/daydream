package cn.dbdj1201.exam;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-06-19 13:28
 **/
@Slf4j(topic = "c.Problem3")
public class Problem3 {
    public static void main(String[] args) {
        Race race = new Race(); //创建一个比赛
        log.debug("Race Start!");
        //要求创建两个线程名字分别为：兔子、乌龟。并启动线程，请补全此处代码：

//        __________________③__________________
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}

@Slf4j(topic = "c.Race")
class Race implements Runnable {
    //winner:只有一个胜利者
    private static String winner;
    int lengthr = 200;//lengthr为兔子需要跑的长度
    int lengtht = 200;//lengtht为乌龟需要跑的长度

    // 重写run方法，编写奔跑操作
    public void run() {
        while (true) {
            //判断线程的名字是兔子
            if (Thread.currentThread().getName().equals("兔子")) {
                //兔子以5米的速度跑，
                //线程每运行一次，就打印"XXX距离终点还有YYY米(XXX为线程名字，YYY为剩余的距离)"，
                //每跑50米休息10毫秒（线程休眠10毫秒），当剩余距离为0米时不再休息，请补全此处代码：
                int disr = 5;

//                __________________①__________________
                lengthr -= disr;
                log.debug("距离终点还有 {} 米", lengthr);
//                System.out.println(Thread.currentThread().getName() + " 距离终点还有" + lengthr + "米");
                if (lengthr % 50 == 0 && lengthr != 0) {
                    try {
                        log.debug("我是兔子，我得休息10ms");
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //调用gameOver方法判断比赛是否结束
                boolean flag = gameOver(lengthr);
                if (flag) {
                    break;
                }
            }

            //判断线程的名字是乌龟
            if (Thread.currentThread().getName().equals("乌龟")) {
                //乌龟以1米的速度，会一直跑不休息，
                //线程每运行一次，就打印"XXX距离终点还有YYY米(XXX为线程名字，YYY为剩余的距离)"，请补全此处代码：
                int dist = 1;

//                __________________②__________________
                lengtht -= dist;
                log.debug("距离终点还有 {} 米", lengtht);
//                System.out.println(Thread.currentThread().getName() + " 距离终点还有" + lengtht + "米");

                //调用gameOver方法判断比赛是否结束
                boolean flag = gameOver(lengtht);
                if (flag) {
                    break;
                }
            }
        }
    }

    //判断比赛是否结束
    private boolean gameOver(int lenght) {
        if (winner != null) { //如果存在胜利者
            return true;
        }
        if (lenght == 0) {  //判断如果跑到了比赛结束
            winner = Thread.currentThread().getName();
            System.out.println("比赛结束");
            System.out.println("胜利者----->" + winner);
            return true;
        }
        return false;
    }
}
