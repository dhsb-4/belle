package com.tsp.belle;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author likeWind
 * @date created in 12:25 2020/4/16
 * @description
 */
public class LockTest {
    private Lock lock = new ReentrantLock();



    private int number = 10;

    public void test1(){
        System.out.println("争取lock" + Thread.currentThread().getName());
        lock.lock();
        System.out.println("获得lock" + Thread.currentThread().getName());

        number--;
        lock.unlock();

    }

    public void test2(){
        System.out.println("2  争取lock" + Thread.currentThread().getName());
        lock.lock();
        number++;
        System.out.println("2  获得lock" + Thread.currentThread().getName());

        lock.unlock();

    }


    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        for (int i=0;i<5;i++){
            new Thread(lockTest::test1).start();
            new Thread(lockTest::test2).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lockTest.number);
    }

}
