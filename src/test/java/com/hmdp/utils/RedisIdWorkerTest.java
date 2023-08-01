package com.hmdp.utils;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.cache.CacheProperties;

import javax.annotation.Resource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WLH
 * @verstion 1.0
 */

class RedisIdWorkerTest {
    @Resource
    RedisIdWorker redisIdWorker;
    private ExecutorService es = Executors.newFixedThreadPool(300);
    @Test
    void nextId() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(300);

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.nextId("order");
                System.out.println("id = " + id);
            }
            latch.countDown();
        };
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            es.submit(task);
        }
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - begin));

    }
}