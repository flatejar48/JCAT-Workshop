package cbc.cpptools.jcat.workshop.threadserise.part1.sync;


public class SynchronizedUsageShow {
	
//	public synchronized int n1 = 0;  
//	public static synchronized int n2 = 0; 
	
    protected int c = 0;
    private static int d = 0;

    synchronized void increment() {
        c++;
    }
    
    void increment2() {
    	//dddd
    	synchronized(this){
    		c++;
    	}
    }
    
    synchronized int get() {
        return c;
    }
    
    //-------------------------------------------------------

    public static synchronized void decrement() {
        d--;
    }
    
    public static void decrement2() {
    	synchronized(SynchronizedUsageShow.class){
    		d--;
    	}
    }

}
