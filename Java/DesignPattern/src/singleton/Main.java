package singleton;

public class Main {

	public static void main(String[] args) {
		SingletonThread thread1 = new SingletonThread(500);
		SingletonThread thread2 = new SingletonThread(300);
		
		thread1.start();
		thread2.start();
		//thread1.run();
		//thread2.run();
	}

}
