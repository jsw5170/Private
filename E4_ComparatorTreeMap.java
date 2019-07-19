import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

class AgeComparator implements Comparator<Integer> {
	public int compare(Integer n1, Integer n2) {
		return n2.intValue() - n1.intValue();
	}
}

public class E4_ComparatorTreeMap {

	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap<>(new AgeComparator());

		// Key-Value 기반 데이터 저장
		map.put(45, "Brown");
		map.put(37, "James");
		map.put(23, "Martin");

		// Key만 담고 있는 컬렉션 인스턴스 생성
		Set<Integer> ks = map.keySet();

		// 전체 Key 출력 (for-each문 기반)
		for (Integer n : ks)
			System.out.print(n.toString() + '\t');
		System.out.println();

		// 전체 Value 출력 (for-each문 기반)
		for (Integer n : ks)
			System.out.print(map.get(n).toString() + '\t');
		System.out.println();

		// 전체 Value 출력 (반복자 기반)
		for (Iterator<Integer> itr = ks.iterator(); itr.hasNext();)
			System.out.print(map.get(itr.next()) + '\t');
		System.out.println();
	}

}
