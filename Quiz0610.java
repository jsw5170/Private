package chapter06;

import java.util.Scanner;

public class Quiz0610 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(" 5개의 정수를 입력하세요.(1미만의 숫자 제외)");
		
		int result = 0;
		for(int i = 0; i < 5; i++) {
			int num1 = sc.nextInt();
			if(num1<1) {
				System.out.println("1 미만의 수를 입력했습니다. 재입력하세요.");
				i--;
			}
			else{
				result = result + num1;
			}
		}
		System.out.println("결과 : " + result );
		sc.close();
	}

}
