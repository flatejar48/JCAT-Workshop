package cbc.cpptools.jcat.workshop.threadserise.part1.sync;

public class StaticAndUnstaticSyncThread  implements Runnable {
	public String methodName;

	public static void method(String s) {
		System.out.println(s);
		while (true)
			;
	}

	public synchronized void method1() {
		method("method1 is running...");
	}

	public void method2() {
		System.out.println("before synchronized block in method2");
		synchronized(this){
			method("method2 is running...");
		}
		System.out.println("after synchronized block in method2");
	}

	public static synchronized void method3() {
		method("static method3 is running...");
	}

	public static synchronized void method4() {
		method("static method4 is running...");
	}

	public void run() {
		try {
			getClass().getMethod(methodName).invoke(this);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) throws Exception {
		StaticAndUnstaticSyncThread target = new StaticAndUnstaticSyncThread();
		
		for (int i = 1; i <= 4; i++) {
			target.methodName = "method" + String.valueOf(i);
			new Thread(target).start();
			Thread.sleep(5);
		}
	}
}
