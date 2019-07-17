package chapter07;

import java.util.Scanner;

public class Quiz0702 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("세 개의 수를 입력하세요. ");
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		int result;
		result = max(num1,num2,num3);
		System.out.println("최대 값 : " + result);
		sc.close();
	}
	public static int max(int num1,int num2,int num3)
	{
		int max = 0;
		if(num1>num2) {
			if(num1>num3)
				max=num1;
			else
				max=num3;
		}else {
			if(num2>num3)
				max=num2;
			else
				max=num3;
		}
		return max;
	}
}
