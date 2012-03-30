package cbc.cpptools.jcat.workshop.threadserise.part1.vola;

import cbc.cpptools.jcat.workshop.threadserise.part1.NotThreadSafe;
import cbc.cpptools.jcat.workshop.threadserise.part1.ThreadSafe;


public class VolatileUsageShow {
	
	//Listing 1. Non-thread-safe number range class
	@NotThreadSafe 
	class NumberRange {
	    private volatile int lower; 
	    private volatile int upper;

	    public int getLower() { return lower; }
	    public int getUpper() { return upper; }

	    public void setLower(int value) { 
	        if (value > upper) 
	            throw new IllegalArgumentException("...");
	        lower = value;
	    }

	    public void setUpper(int value) { 
	        if (value < lower) 
	        	 throw new IllegalArgumentException("...");
	        upper = value;
	    }
	}
	
	//Pattern #1: status flags
	class Pattern1{
		volatile boolean shutdownRequested;

		public void shutdown() { shutdownRequested = true; }

		public void doWork() { 
		    while (!shutdownRequested) { 
		        // do stuff
		    }
		}

	}
	
	//Pattern #2: the "volatile bean" pattern
	@ThreadSafe
	public class Person {
	    private volatile String firstName;
	    private volatile String lastName;
	    private volatile int age;

	    public String getFirstName() { return firstName; }
	    public String getLastName() { return lastName; }
	    public int getAge() { return age; }

	    public void setFirstName(String firstName) { 
	        this.firstName = firstName;
	    }

	    public void setLastName(String lastName) { 
	        this.lastName = lastName;
	    }

	    public void setAge(int age) { 
	        this.age = age;
	    }
	}

	
	//Pattern #3: The cheap read-write lock trick
	@ThreadSafe
	public class CheesyCounter {
	    // Employs the cheap read-write lock trick
	    // All mutative operations MUST be done with the 'this' lock held
	    private volatile int value;

	    public int getValue() { return value; }

	    public synchronized int increment() {
	        return value++;
	    }
	}


}
