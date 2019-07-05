package chapter02;

import java.util.Scanner;

public class Quiz0203 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 개의 정수를 입력하세요.");
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int result;
		
		result = num1 / num2;
		System.out.printf("%d 나누기 %d 의 몫은 %d입니다. \n", num1, num2, result);
		result = num1 % num2;
		System.out.printf("%d 나누기 %d 의 나머지는 %d입니다. \n", num1, num2, result);
		sc.close();
	}

}
