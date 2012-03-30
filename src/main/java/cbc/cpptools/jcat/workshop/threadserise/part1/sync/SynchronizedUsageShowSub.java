package cbc.cpptools.jcat.workshop.threadserise.part1.sync;


public class SynchronizedUsageShowSub extends SynchronizedUsageShow {
	
	@Override
    int get() {
        return c;
    }
	
	
	@Override
    synchronized void increment() {
        c+=2;
    }
	
}
