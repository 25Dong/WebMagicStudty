package test.Thread4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService3{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void await(){
        try{
            lock.lock();//加上锁
            //await():使当前的执行线程进入了等待的状态
            System.out.println("线程开始加入等待队列，当期时间是"+System.currentTimeMillis());
            condition.await();//await()方法之前必定要先加锁
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("线程释放锁，时间为"+System.currentTimeMillis());
            lock.unlock();//释放锁
        }
    }


    public void signal(){
        try{
            lock.lock();
            System.out.println("Signal时间为：" + System.currentTimeMillis());
            condition.signal();//唤醒在等待的线程
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class ThreadA3 extends Thread{
    private MyService3 service3;
    public ThreadA3(MyService3 service3){
        this.service3 = service3;
    }

    public void run(){
        service3.await();
    }
}
public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        MyService3 service3 = new MyService3();
        ThreadA3 threadA3 = new ThreadA3(service3);
        threadA3.start();
        Thread.sleep(3000);
        service3.signal();//唤醒正在等待的线程
    }
}
