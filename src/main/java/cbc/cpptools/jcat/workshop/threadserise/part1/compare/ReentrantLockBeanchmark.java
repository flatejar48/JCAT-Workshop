package cbc.cpptools.jcat.workshop.threadserise.part1.compare;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockBeanchmark implements Counter {

    private volatile long count = 0;

    private Lock lock;

    public ReentrantLockBeanchmark(boolean fair) {
        lock = new ReentrantLock(fair);
    }

    public long getValue() {
        return count;
    }

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

}
