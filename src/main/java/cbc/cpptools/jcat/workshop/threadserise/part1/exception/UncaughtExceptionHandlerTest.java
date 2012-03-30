package cbc.cpptools.jcat.workshop.threadserise.part1.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class UncaughtExceptionHandlerTest {
	/*
	 * 1.uncaughtExceptionHandler of thread 2.uncaughtException of threadGroup
	 * 3.defaultUncaughtExceptionHandler
	 */
	public static void main(String[] args) throws InterruptedException {
		MyThreadGroup mtg = new MyThreadGroup("MyThreadGroup");
		NullPointerRunnable target = new NullPointerRunnable();
		Thread t1 = new Thread(mtg, target, "t1");
		Thread t2 = new Thread(mtg, target, "t2");
		Thread t3 = new Thread(target, "t3");
		t1.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler(
				"-UncaughtExceptionHandler"));
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(
				"-DefaultUncaughtExceptionHandler"));

		t1.start();
		Thread.sleep(1000);
		t2.start();
		Thread.sleep(1000);
		t3.start();
	}
}

class NullPointerRunnable implements Runnable {
	@Override
	public void run() {
		throw new NullPointerException("NullPointerRunnable");
	}
}

class MyThreadGroup extends ThreadGroup {

	public MyThreadGroup(String name) {
		super(name);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t.getName() + " -MyThreadGroup!!");
	}

}

class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {

	String msg;

	public MyUncaughtExceptionHandler(String msg) {
		this.msg = msg;
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t.getName() + " " + msg);

	}

}
