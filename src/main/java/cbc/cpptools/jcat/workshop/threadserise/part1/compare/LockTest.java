package cbc.cpptools.jcat.workshop.threadserise.part1.compare;
import java.math.BigDecimal;

public class LockTest {

	public static void main(String[] args) throws Exception {
		final int max = Runtime.getRuntime().availableProcessors() * 2;
		final int loopCount = 100000;
		long costTime = 0;
		long costTime2 = 0;
		for (int m = 0; m < max; m++) {
			long start1 = System.currentTimeMillis();
			final AtomicIntegerWithLock value1 = new AtomicIntegerWithLock(0);
			Thread[] ts = new Thread[max];
			for (int i = 0; i < max; i++) {
				ts[i] = new Thread() {
					public void run() {
						for (int i = 0; i < loopCount; i++) {
							value1.incrementAndGet();
						}
					}
				};
			}
			for (Thread t : ts) {
				t.start();
			}
			for (Thread t : ts) {
				t.join();
			}
			long end1 = System.currentTimeMillis();
			costTime += (end1 - start1);
		}
		System.out.println("ReentrantLock cost: " + (costTime));
		//
		System.out.println();
		//
		final Object lock = new Object();
		for (int m = 0; m < max; m++) {
			staticValue = 0;
			long start1 = System.currentTimeMillis();
			Thread[] ts = new Thread[max];
			for (int i = 0; i < max; i++) {
				ts[i] = new Thread() {
					public void run() {
						for (int i = 0; i < loopCount; i++) {
							synchronized (lock) {
								++staticValue;
							}
						}
					}
				};
			}
			for (Thread t : ts) {
				t.start();
			}
			for (Thread t : ts) {
				t.join();
			}
			long end1 = System.currentTimeMillis();
			costTime2 += (end1 - start1);
		}
		//
		System.out.println("synchronized  cost: " + (costTime2));
		System.out.println();
		System.out.println("synchronized/ReentrantLock: "
				+ BigDecimal.valueOf(costTime2).divide(
						BigDecimal.valueOf(costTime), 5,
						BigDecimal.ROUND_HALF_UP));
	}

	static int staticValue = 0;

}
