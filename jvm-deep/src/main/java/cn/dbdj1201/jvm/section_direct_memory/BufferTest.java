package cn.dbdj1201.jvm.section_direct_memory;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-07-31 14:57
 **/
@Slf4j(topic = "c.BufferTest")
public class BufferTest {

    private static final int BUFFER = 1024*1024*50;
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);

        log.debug("直接内存分配完毕，请求指示");
//        Scanner scanner = new Scanner(System.in);
//        scanner.next();
//
//        log.info("开始释放");
//
//        byteBuffer = null;
//        System.gc();
//        scanner.next();
        try {
            TimeUnit.SECONDS.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
