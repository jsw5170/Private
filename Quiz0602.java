package chapter06;

import java.util.Scanner;

public class Quiz0602 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("국어, 영어, 수학의 점수를 입력하세요.");
		
		System.out.println("국어 : ");
		int num1 = sc.nextInt();
		
		System.out.println("영어 : ");
		int num2 = sc.nextInt();
		
		System.out.println("수학 : ");
		int num3 = sc.nextInt();
		int result = (num1 + num2 + num3) / 3;
		
		if(result >= 90) {
			System.out.println("학점은 A입니다.");
		}else if(result >= 80) {
			System.out.println("학점은 B입니다.");
		}else if(result >= 70) {
			System.out.println("학점은 C입니다.");
		}else if(result >= 50) {
			System.out.println("학점은 D입니다.");
		}else {
			System.out.println("학점은 F입니다.");
		}	
		sc.close();
	}

}
