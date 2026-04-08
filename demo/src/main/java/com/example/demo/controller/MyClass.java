package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyClass {

    static Date addSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date)date.clone());
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }
    static Date dt = Date.from(Instant.now());
    static List<Date> dates = IntStream.rangeClosed(1, 100).mapToObj(i -> addSeconds(dt,i)).collect(Collectors.toCollection(ArrayList::new));


    static void thread1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dates.stream().parallel().forEach(d-> {
            System.out.println(" --- " + sdf.format(d) + " at: " + Thread.currentThread().threadId());
        });
    }

    static Runnable runnableSleepy(int id, CountDownLatch countDownLatch) {
        return () -> {
            try {
                long start = System.currentTimeMillis();
                System.out.println("--:"+id+" START:"+(System.currentTimeMillis()-start)+" @" + Thread.currentThread().threadId());
                Thread.sleep(1000);
                System.out.println("--:"+id+" COUNT:"+(System.currentTimeMillis()-start)+" @" + Thread.currentThread().threadId());
                countDownLatch.countDown();
                System.out.println("--:"+id+"  EXIT:"+(System.currentTimeMillis()-start)+" @" + Thread.currentThread().threadId());
            } catch (InterruptedException e) {}
        };
    }

    static void thread_latch() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        List<Thread> threads = IntStream.rangeClosed(0, 9).parallel().mapToObj(i -> runnableSleepy(i,countDownLatch)).map(Thread::new).collect(Collectors.toCollection(ArrayList::new));
        threads.stream().forEach(Thread::start);
        try {
            countDownLatch.await();
            System.out.println("==OK============================"+countDownLatch.getCount());
        } catch (InterruptedException e) {
            System.out.println("==ERR==========================="+countDownLatch.getCount());
        }
    }


    static void thread_latch_expires() {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        List<Thread> threads = IntStream.rangeClosed(0, 9).parallel().mapToObj(i -> runnableSleepy(i,countDownLatch)).map(Thread::new).collect(Collectors.toCollection(ArrayList::new));
        threads.stream().forEach(Thread::start);
        try {
            boolean hasFinished = countDownLatch.await(10000, TimeUnit.MILLISECONDS);
            System.out.println("==OK============================:"+countDownLatch.getCount() + " finished = " + hasFinished);
        } catch (InterruptedException e) {
            System.out.println("==ERR===========================:"+countDownLatch.getCount());
        }
    }


    static void main() {
        //thread1();
        //thread_latch();
        thread_latch_expires();
    }

}
