package Game;

import java.util.Scanner;

public class NumberFind {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int sum = 0;
		while (true) {
			System.out.print("데이터를 아무거나 무작위로 입력하세요 : ");
			String str = s.next();
			for (int i = 0; i < str.length(); i++) {
				if (48 <= str.charAt(i) && str.charAt(i) <= 57) {
					sum += str.charAt(i)-'0';
				}
			}
			System.out.println("숫자의 합 : " + sum);
			sum = 0;
			System.out.println("계속하려면 아무 키나 누르십시오 .");
			String str1 = s.next();
		}
	}
}
