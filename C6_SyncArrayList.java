import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class C6_SyncArrayList {
	public static List<Integer> lst =
			Collections.synchronizedList(new ArrayList<Integer>());
//	public static List<Integer> lst = new ArrayList<Integer>();
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 16; i++)
			lst.add(i);
		System.out.println(lst);

		Runnable task = () -> {
// 컬렉션 인스턴스가 동기화되었다고 해도 이를 기반으로
// 생성된 반복자까지 동기화가 이뤄지는 것은 아니다.
// 반복자도 동기화를 해주어야 한다.
//			synchronized (lst) {
				ListIterator<Integer> itr = lst.listIterator();

				while (itr.hasNext())
					itr.set(itr.next() + 1);
//				 System.out.println(lst);
//			}
		};
		ExecutorService exr = Executors.newFixedThreadPool(3);
		exr.submit(task);
		exr.submit(task);
		exr.submit(task);

		exr.shutdown();
		exr.awaitTermination(100, TimeUnit.SECONDS);
		System.out.println(lst);
	}
}
