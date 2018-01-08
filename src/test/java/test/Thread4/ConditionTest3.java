package test.Thread4;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class MyService5{
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set()   {
        try{
            lock.lock();
            while (hasValue == true){
                condition.await();
            }
            System.out.println("我是一个生产者");
            hasValue = true;
            condition.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void get(){
        try {
            lock.lock();
            while (hasValue == false){
                condition.await();
            }
            System.out.println("我是一个消费者");
            hasValue = false;
            condition.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class MyThreadA extends Thread{
    private MyService5 service5;

    public MyThreadA(MyService5 service5) {
        this.service5 = service5;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            service5.set();
        }
    }
}



class MyThreadB extends Thread{
    private MyService5 service5;

    public MyThreadB(MyService5 service5) {
        this.service5 = service5;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            service5.get();
        }
    }
}


public class ConditionTest3 {
    public static void main(String[] args) {
        MyService5 myService5 = new MyService5();
        MyThreadA a = new MyThreadA(myService5);
        a.start();
        MyThreadB b = new MyThreadB(myService5);
        b.start();
    }
}
