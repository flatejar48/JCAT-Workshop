package cbc.cpptools.jcat.workshop.threadserise.part1.vola;

public class VolatileTest1 {

	public static volatile int race = 0;
	
	public static void increase(){
		race++;
		int vvv = 0;
		vvv++;
	}
	
	private static final int THREAD_COUNTS = 20;
	private static final int RUN_TIMES = 10000;
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[THREAD_COUNTS];
		for(int i = 0; i < THREAD_COUNTS; i++){
			threads[i] = new Thread(new VolatileTest1().new MyRunnable(i + 1));
			threads[i].start();
			//threads[i].join();
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
