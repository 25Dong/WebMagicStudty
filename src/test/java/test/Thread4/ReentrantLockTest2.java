package test.Thread4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService2{
    private Lock lock = new ReentrantLock();

    public void MethodA(){
        try {
            lock.lock();
            System.out.println("MethodA begin"+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("MethodA end"+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void MethodB(){
        try {
            lock.lock();
            System.out.println("MethodB begin"+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("MethodB end"+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class ThreadA extends Thread{
    private MyService2 service2;
    public  ThreadA(MyService2 service2){
        this.service2 = service2;
    }

    public void run(){
        service2.MethodA();
    }
}

class ThreadB extends Thread{
    private MyService2 service2;
    public ThreadB(MyService2 service2){
        this.service2 = service2;
    }

    public void run(){
        service2.MethodB();
    }
}
public class ReentrantLockTest2 {
    public static void main(String[] args) throws InterruptedException {
        MyService2 service2 = new MyService2();
        ThreadA threada1 = new ThreadA(service2);
        threada1.setName("A1");
        threada1.start();
        ThreadA threada2 = new ThreadA(service2);
        threada2.setName("A2");
        threada2.start();
        Thread.sleep(100);
        ThreadB threadb1 = new ThreadB(service2);
        threadb1.setName("B1");
        threadb1.start();
    }
}
