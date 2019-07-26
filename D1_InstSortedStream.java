import java.util.stream.Stream;

public class D1_InstSortedStream {

	public static void main(String[] args) {
	// String 인스턴스는 Comparable<String> 인터페이스를 구현! 이를 기반으로 한 정렬
		Stream.of("Box", "Apple", "Robot")
		.sorted()
		.forEach(n -> System.out.print(n + "\t"));
		System.out.println();
		
		Stream.of("Box", "Apple", "Rabbit")
		.sorted((s1,s2) -> s1.length() - s2.length())	// compareTo 메소드에 대한 람다식 이를 기반으로 한 정렬
		.forEach(n -> System.out.print(n + "\t"));
		System.out.println();

	}

}
