package singleton;

public class Singleton {
	/*volatile keyword�� Java ������ Main Memory�� �����ϰڴٶ�� ���� ����ϴ� ���Դϴ�.
	 * volatile ������ ����ϰ� ���� �ʴ� MultiThread ���ø����̼ǿ����� Task�� �����ϴ� ���� ���� ����� ���� Main Memory���� ���� ���� ���� CPU Cache�� �����ϰ� �˴ϴ�.
	 * ���࿡ Multi Threadȯ�濡�� Thread�� ���� ���� �о�� �� ������ CPU Cache�� ����� ���� �ٸ��� ������ ���� �� ����ġ ������ �߻��ϰ� �˴ϴ�.
	 * Multi Thread ȯ�濡�� �ϳ��� Thread�� read & write�ϰ� ������ Thread�� read�ϴ� ��Ȳ���� ���� �ֽ��� ���� �����մϴ�.    
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
