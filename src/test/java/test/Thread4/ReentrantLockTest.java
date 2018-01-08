package test.Thread4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 关键字可以实现线程之间的同步互斥
 * 但是在JDK 1.5中增加了ReentrantLock类也能达到同样的效果。并且扩展了
 * 其他功能"嗅探锁定"，"多路分支通知"
 */

class MyService{
    private Lock lock = new ReentrantLock();
    public void testMethod() {
        lock.lock();
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName()+": "+i);
        }
        lock.unlock();
    }
}


class MyThread extends  Thread{
    private  MyService service;

    public MyThread(MyService service){
        this.service = service;
    }

    public void  run(){
        service.testMethod();
    }
}


//测试
public class ReentrantLockTest {
    public static void main(String[] args) {
        //开启六个线程运行MyService类
        MyService service = new MyService();
        MyThread thread1 = new MyThread(service);
        MyThread thread2 = new MyThread(service);
        MyThread thread3 = new MyThread(service);
        MyThread thread4 = new MyThread(service);
        MyThread thread5 = new MyThread(service);
        MyThread thread6 = new MyThread(service);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}
