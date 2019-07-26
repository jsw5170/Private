import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C1_ExecutorsDemo {

	public static void main(String[] args) {
		Runnable task = () -> { // 쓰레드에게 시킬 작업
			int n1 = 10;
			int n2 = 20;
			String name = Thread.currentThread().getName();
			System.out.println(name + " : " + (n1 + n2));
		};

		ExecutorService exr = Executors.newSingleThreadExecutor();
		exr.submit(task); // 쓰레드 풀에 작업을 전달

		System.out.println("End " + Thread.currentThread().getName());

		exr.shutdown(); // 쓰레드 풀과 그 안에 있는 쓰레드의 소멸
// shutdown() 메서드가 호출되어도 이미 전달된 작업은 진행이 된다.
		
	}
}
