package cbc.cpptools.jcat.workshop.threadserise.part1.sync;

import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockShow {
	private int c = 0;
	private ReentrantLock lock = new ReentrantLock();
	
	void increment() {
		lock.lock();
		try{
			c++;
		}finally{
			lock.unlock();
		}
    }
	
}
