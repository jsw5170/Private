package chapter06;

import java.util.Scanner;

public class Quiz0605 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(" 여러 개의 정수를 입력하세요. (0을 입력하면 종료합니다.");
		int result = 0;
		int num =1;
		while(num != 0) {
		num = sc.nextInt();
		result = result + num;
		}
		System.out.println("결과 : " + result);
		sc.close();
	}
}
