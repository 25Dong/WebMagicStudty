package test.ThreadPoolTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * i++,++i:线程不安全
 * 使用AtomicInteger线程安全，适合用于高并发下的访问
 */
public class AtomicIntegerTest {
    private Map<String, AtomicInteger> counter = new HashMap<String, AtomicInteger>();

    public static void main(String[] args) {

    }

    private void initCounter() {
        counter.put("cvm", new AtomicInteger(0));
    }
}


