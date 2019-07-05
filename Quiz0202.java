package chapter02;

import java.util.Scanner;

public class Quiz0202 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("한 개의 정수를 입력하세요.");
		
		int num = sc.nextInt();
		int result = num*num;
		
		System.out.println("제곱 : " + result);
		sc.close();

	}

}
