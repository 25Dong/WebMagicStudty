package test.Thread4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//使用多个Condition实现通知部分线程
class MyService4{
   private Lock lock = new ReentrantLock();
   private Condition conditionA = lock.newCondition();
   private Condition conditionB = lock.newCondition();

   public void awaitA(){
       try {
           lock.lock();//加锁A
           System.out.println(Thread.currentThread().getName()+" Begin awaitA time is "+System.currentTimeMillis());
           conditionA.await();//加入等待状态
           System.out.println(Thread.currentThread().getName()+" end awaitA time is "+System.currentTimeMillis());
       } catch (InterruptedException e) {
           e.printStackTrace();
       }finally {
           lock.unlock();//释放锁
       }
   }

    public void awaitB(){
        try {
            lock.lock();//加锁
            System.out.println(Thread.currentThread().getName()+" Begin awaitB time is "+System.currentTimeMillis());
            conditionB.await();//加入等待状态
            System.out.println(Thread.currentThread().getName()+" end awaitB time is "+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();//释放锁
        }
    }

    public void signalAll_A(){
       try{
           lock.lock();//加锁
           System.out.println(Thread.currentThread().getName()+" signalAll_A time is "+System.currentTimeMillis());
           conditionA.signalAll();//唤醒conditionA对象中所有的等待线程
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           lock.unlock();//释放锁
       }
    }

    public void signalAll_B(){
        try{
            lock.lock();//加锁
            System.out.println(Thread.currentThread().getName()+" signalAll_B time is "+System.currentTimeMillis());
            conditionB.signalAll();//唤醒conditionB对象中所有的等待线程
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();//释放锁
        }
    }
}

class ThreadA4 extends Thread{
    private  MyService4 service4;

    public ThreadA4(MyService4 service4){
        this.service4 = service4;
    }

    @Override
    public void run() {
        service4.awaitA();//调用awaitA（）方法，使当前线程进入等待状态
    }
}

class ThreadB4 extends Thread{
    private MyService4 service4;

    public ThreadB4(MyService4 service4){
        this.service4 = service4;//调用awaitB（）方法，使当前线程进入等待状态
    }

    @Override
    public void run() {
        service4.awaitB();////调用awaitB（）方法，使当前线程进入等待状态
    }
}
public class ConditionTest2 {
    public static void main(String[] args) throws InterruptedException {
        MyService4 service4 = new MyService4();
        ThreadA4 a4 = new ThreadA4(service4);
        a4.setName("AA");
        a4.start();//启动AA线程

        ThreadB4 b4 =  new ThreadB4(service4);
        b4.setName("BB");
        b4.start();//启动BB线程

        Thread.sleep(5000);
        service4.signalAll_A();//唤醒conditionA对象中所有等待的线程
    }

}
