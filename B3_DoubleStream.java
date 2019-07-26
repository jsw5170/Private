import java.util.Arrays;

public class B3_DoubleStream {

	public static void main(String[] args) {
		double[] ds = { 1.1, 2.2, 3.3, 4.4, 5.5 };

		Arrays.stream(ds)
		.forEach(d -> System.out.print(d + "\t"));
		System.out.println();

		Arrays.stream(ds, 1, 4)
		.forEach(d -> System.out.print(d + "\t"));
		System.out.println();
	}

}
// 일부분을 이용해 스트림을 생성할 수 있다.