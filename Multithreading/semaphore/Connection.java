package multithreading.semaphore;

import java.util.concurrent.Semaphore;

public class Connection {
	// Singleton

	private static Connection instance = new Connection();

	private Semaphore sem = new Semaphore(10);

	private int connections = 0;

	private Connection() {

	}

	public static Connection getInstance() {
		return instance;
	}

	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Even if doConnect() throws an exception the finally block will
		// still release the lock.
		try {
			doConnect();
		} finally {
			sem.release();
		}
	}

	public void doConnect() {

		synchronized (this) {
			connections++;
			System.out.println("Current connections: " + connections);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synchronized (this) {
			connections--;
			// System.out.println("Connections after end of method: " +
			// connections);
		}

	}

}
