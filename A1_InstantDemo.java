import java.time.Duration;
import java.time.Instant;

public class A1_InstantDemo {

	public static void main(String[] args) {
		Instant start = Instant.now();	//현재 시작 정보를 담음
		System.out.println("시작 : " + start.getEpochSecond());
		int sum = 0;
		for (int i = 0; i < 1000000; i++) {
			sum = sum + i;
		}
		System.out.println("Time flies like an arrow." + sum);

		Instant end = Instant.now();
		System.out.println("끝 : " + end.getEpochSecond());

		Duration between = Duration.between(start, end);
		System.out.println("밀리 초 단위 차 : " + between.toMillis());

	}

}
