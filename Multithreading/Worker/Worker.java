package multithreadingPart01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	// to prevent intertwining of threads here instead of the Reentrant class
	// we use objects to lock on
	private Random random = new Random();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	public void stageOne() {
		// if we use synchronised on the method calling instead of just the
		// code inside it, it will work but it will be slower
		
		// This way two threads can work simultaneously on the same method
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list1.add(random.nextInt(100));
		}

	}

	public void stageTwo() {

		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list2.add(random.nextInt(100));
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void main() {
		System.out.println("Starting...");

		long start = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				process();
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				process();
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		long end = System.currentTimeMillis();

		System.out.println("Time taken: " + (end - start));
		System.out.println("List1: " + list1.size());
		System.out.println("List2: " + list2.size());

	}

}
