package cbc.cpptools.jcat.workshop.threadserise.part1.sync;

public class SyncOneVariable implements Runnable {
	private Test test;
	private String label;

	public void run() {
		test.method(label + " is running...");
	}

	public SyncOneVariable(Test test, String label) {
		this.test = test;
		this.label = label;
	}

	public static void main(String[] args) throws Exception {
		Test test1 = new Test();
		Test test2 = new Test();
		SyncOneVariable sync1 = new SyncOneVariable(test1, "test1");
		SyncOneVariable sync2 = new SyncOneVariable(test2, "test2");

		new Thread(sync1).start();
		new Thread(sync2).start();
		new Thread(sync1).start();//will be blocked!!
	}
}

class Test {
	public synchronized void method(String l) {
		System.out.println(l);
		while (true)
			;
	}
}