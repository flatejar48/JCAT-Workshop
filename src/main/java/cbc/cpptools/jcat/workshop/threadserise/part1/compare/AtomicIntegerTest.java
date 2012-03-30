package cbc.cpptools.jcat.workshop.threadserise.part1.compare;

import java.util.concurrent.atomic.AtomicInteger;


public class AtomicIntegerTest {
	
public static AtomicInteger race = new AtomicInteger(0);
	
	public static void increase(){
		race.incrementAndGet();
	}
	
	private static final int THREAD_COUNTS = 20;
	private static final int RUN_TIMES = 10000;
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[THREAD_COUNTS];
		for(int i = 0; i < THREAD_COUNTS; i++){
			threads[i] = new Thread(new AtomicIntegerTest().new MyRunnable(i + 1));
			threads[i].start();
		}
		
		Thread.sleep(2000);
		
		System.out.println(race);//assert race == THREAD_COUNTS * RUN_TIMES;

	}
	
	class MyRunnable implements Runnable{
		
		int index;
		public MyRunnable(int index){
			this.index = index;
		}

		@Override
		public void run() {
			for(int i = 0; i < RUN_TIMES; i++){
				increase();
			}
			System.out.println(index + " finish!");
		}
		
	}
}
