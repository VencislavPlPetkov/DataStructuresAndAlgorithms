package multithreadingPart02;

import java.util.Scanner;

public class Processor {
	
	public void produce() throws InterruptedException {
		
		// Synchronising on the class itself
		synchronized (this) {
			
			System.out.println("Producer thread running ....");
			
			// When the program reaches the wait(), it releases the lock
			// from the synchronisation, and allows the other threads to
			// run before the code beneath the wait() can be executed.
			wait();
			System.out.println("Resumed.");
		}
	}
	
	public void consume() throws InterruptedException {
		
		Scanner scn = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized (this) {
			System.out.println("Waiting for return key.");
			scn.nextLine();
			System.out.println("Return key pressed.");
			notify();
			
			
		}
		
		
	}
	
}































