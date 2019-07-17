package chapter07;

import java.util.Scanner;

public class Quiz0703 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("세 개의 수를 입력하세요. ");
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		int result;
		result = min(num1,num2,num3);
		System.out.println("최소 값 : " + result);
		sc.close();
	}
	public static int min(int num1,int num2,int num3)
	{
		int min = 0;
		if(num1<num2) {
			if(num1<num3)
				min=num1;
			else
				min=num3;
		}else {
			if(num2<num3)
				min=num2;
			else
				min=num3;
		}
		return min;
	}
}
