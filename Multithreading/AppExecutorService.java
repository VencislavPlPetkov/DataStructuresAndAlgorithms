package multithreadingPart01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

	private int id;

	public Processor(int id) {
		this.id = id;
	}

	public void run() {

		System.out.println("Starting " + id);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}

		System.out.println("Completed" + id);

	}
}

public class AppExecutorService {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		// Make this two threads do 5 tasks

		for (int i = 0; i < 5; i++) {
			executor.submit(new Processor(i));
		}

		// Stop accepting new tasks

		executor.shutdown();

		System.out.println("All tasks submitted. ");

		// Give a count down for the tasks
		// Example: if it is 5 minutes. After 5 minutes it will move forward
		// without
		// waiting for them to finish.
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
		}

		System.out.println("All tasks completed.");

	}

}
