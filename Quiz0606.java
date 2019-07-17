package chapter06;

import java.util.Scanner;

public class Quiz0606 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(" 몇개의 정수를 입력할지 입력하세요.");
		int num1 = sc.nextInt();
		double result = 0;
		System.out.println(num1 + "개의 정수를 입력하세요.");
		for(int i = 0; i < num1 ; i++) {
			int num2 = sc.nextInt();	
			result = result + num2;
		}
		result = result / num1 ;
		System.out.println("평균 값 :" + result);
		sc.close();
	}

}
