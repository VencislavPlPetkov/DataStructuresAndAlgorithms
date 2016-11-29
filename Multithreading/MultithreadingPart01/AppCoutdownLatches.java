package multithreadingPart01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor1 implements Runnable {

	private CountDownLatch latch;

	public Processor1(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {

		System.out.println("Started...");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		latch.countDown();

	}

}

public class AppCoutdownLatches {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(3);

		ExecutorService executor = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 3; i++) {

			executor.submit(new Processor1(latch));

		}

		// waits until the CountDownLatch has counted down to 0.
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Completed. ");

	}

}
