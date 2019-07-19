import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class B1_SetCollectionFeature {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		set.add("Toy");
		set.add("Box");
		set.add("Robot");
		set.add("Box");
		
		System.out.println("인스턴스 수 : " + set.size());
		
		// 반복자를 이용한 전체 출력
		for (Iterator<String> itr = set.iterator(); itr.hasNext();)
			System.out.print(itr.next() + '\t');
		System.out.println();
		
		// for-each를 이용한 전체 출력
		for(String s: set)
			System.out.print(s + '\t');
		System.out.println();
	}
}
// Set<E> 인터페이스를 구현하는 제네릭 클래스들은 다음 두가지 특성을 갖는다.
// : 저장 순서가 유지되지 않는다.
// : 데이터의 중복 저장을 허용하지 않는다.