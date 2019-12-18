package singleton;

public class Singleton {
	/*volatile keyword는 Java 변수를 Main Memory에 저장하겠다라는 것을 명시하는 것입니다.
	 * volatile 변수를 사용하고 있지 않는 MultiThread 어플리케이션에서는 Task를 수행하는 동안 성능 향상을 위해 Main Memory에서 읽은 변수 값을 CPU Cache에 저장하게 됩니다.
	 * 만약에 Multi Thread환경에서 Thread가 변수 값을 읽어올 때 각각의 CPU Cache에 저장된 값이 다르기 때문에 변수 값 불일치 문제가 발생하게 됩니다.
	 * Multi Thread 환경에서 하나의 Thread만 read & write하고 나머지 Thread가 read하는 상황에서 가장 최신의 값을 보장합니다.    
	 */
	private volatile static Singleton uniqueInstance;
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}
