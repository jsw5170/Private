package chapter02;

import java.util.Scanner;

public class Quiz0204 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 개의 정수를 입력하세요.");
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int result = num1-num2;
		int a;
		a = (result >0) ? num1 : num2;
		System.out.println("두 개의 정수중 큰 값은 : " + a);
		sc.close();
	}

}
