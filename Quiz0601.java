package chapter06;

import java.util.Scanner;

public class Quiz0601 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 개의 정수를 입력하세요.");
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int result = num1 - num2;
				
		if(result > 0) 
		{
			System.out.println("두 수의 차 : " + result);
		} else {
			System.out.println("두 수의 차 : " + -result);
		}
		sc.close();

	}

}
