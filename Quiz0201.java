package chapter02;

import java.util.Scanner;

public class Quiz0201 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 개의 정수를 입력하세요.");
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int result;
		
		result = num1 + num2;
		System.out.printf("%d + %d = %d \n", num1, num2, result);
		result = num1 - num2;
		System.out.printf("%d - %d = %d \n", num1, num2, result);
		result = num1 * num2;
		System.out.printf("%d * %d = %d \n", num1, num2, result);
		result = num1 / num2;
		System.out.printf("%d / %d = %d \n", num1, num2, result);
		sc.close();
	}

}
