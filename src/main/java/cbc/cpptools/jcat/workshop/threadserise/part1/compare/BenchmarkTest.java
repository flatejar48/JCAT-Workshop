package cbc.cpptools.jcat.workshop.threadserise.part1.compare;

import java.util.concurrent.CyclicBarrier;


public class BenchmarkTest {
    private Counter counter;

    private CyclicBarrier barrier;

    private int threadNum;
    
    private String des;

    public BenchmarkTest(Counter counter, int threadNum, String des) {
        this.counter = counter;
        barrier = new CyclicBarrier(threadNum + 1); 
        this.threadNum = threadNum;
        this.des = des;
    }

    public static void main(String args[]) {
    	int threadNum = 500;
        new BenchmarkTest(new SynchronizeBenchmark(), threadNum, "SynchronizeBenchmark").test();
        new BenchmarkTest(new ReentrantLockBeanchmark(false), threadNum, "ReentrantLockBeanchmark").test();
        new BenchmarkTest(new ReentrantLockBeanchmark(true), threadNum, "ReentrantLockBeanchmarkFair").test();   
    }

    public void test() {
        try {
            for (int i = 0; i < threadNum; i++) {
                new TestThread(counter).start();
            }
            long start = System.currentTimeMillis();
            barrier.await(); // wait for all thread create
            barrier.await(); // wait for all count finish
            long end = System.currentTimeMillis();
            System.out.println("count value:" + counter.getValue());
            System.out.println(des + " cost Time:" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class TestThread extends Thread {
        private Counter counter;

        public TestThread(final Counter counter) {
            this.counter = counter;
        }

        public void run() {
            try {
                barrier.await();
                for (int i = 0; i < 100; i++)
                    counter.increment();
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
