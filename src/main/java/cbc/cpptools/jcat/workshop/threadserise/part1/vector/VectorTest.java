package cbc.cpptools.jcat.workshop.threadserise.part1.vector;

import java.util.Vector;


public class VectorTest {
	
	private static Vector<Integer> vector = new Vector<Integer>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while(true){
			for(int i = 0; i < 10; i++){
				vector.add(i);
			}
			
			Thread removeThread = new Thread(new Runnable(){

				@Override
				public void run() {
					for(int i = 0; i < vector.size(); i++){
						vector.remove(i);
					}
					
				}
				
			});
			
			Thread printThread = new Thread(new Runnable(){

				@Override
				public void run() {
					for(int i = 0; i < vector.size(); i++){
						//vector.get(i);
						System.out.print(vector.get(i));
					}
					
				}
				
			});
			
			removeThread.start();
			printThread.start();
			
			while(Thread.activeCount() > 20){
				;
			}
		}

	}

}
