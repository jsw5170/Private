package chapter07;

import java.util.Scanner;

public class Quiz0705 {

	public static void main(String[] args) {
		int result=0;
		Scanner sc = new Scanner(System.in);
		System.out.println("세 개의 수를 입력하세요. ");
		System.out.println("첫번째의 수를 입력하세요. ");
		int num1 = sc.nextInt();
		System.out.println("두번째의 수를 입력하세요. ");
		int num2 = sc.nextInt();
		do {
			System.out.println("세번째의 수를 입력하세요. ");
			int num3 = sc.nextInt();
			if(num3 ==1) {
				result = hap(num1,num2);
				break;
			}else if(num3 ==2) {
				result = cha(num1,num2);
				break;
			}else if(num3 ==3) {
				result = gop(num1,num2);
				break;
			}else if(num3 ==4) {
				result = div(num1,num2);
				break;
			}else {
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
		}while(true);
		
		
		
		System.out.println("결과 값 : " + result);		
		sc.close();
	}
	public static int hap(int num1,int num2) {
		int result= num1 + num2;
		return result;
	}
	public static int cha(int num1,int num2) {
		int result= num1 - num2;
		return result;
	}
	public static int gop(int num1,int num2) {
		int result= num1 * num2;
		return result;
	}
	public static int div(int num1,int num2) {
		if(num2==0) {
			System.out.println("0으로는 나눌 수 없습니다.");
			return 0;
		}
		int result= num1 / num2;
		return result;
	}
}
