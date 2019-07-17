package chapter07;

import java.util.Scanner;

public class Quiz0701 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("세 개의 수를 입력하세요. ");
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		int result;
		result = avg(num1,num2,num3);
		System.out.println("평균 값 : " + result);
		sc.close();
	}
	public static int avg(int num1,int num2,int num3)
	{
		int avg = (num1 + num2 + num3) / 3;
		return avg;
	}
}
