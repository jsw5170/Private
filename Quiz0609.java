package chapter06;

import java.util.Scanner;

public class Quiz0609 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("(2~9)사이의 수를 입력하세요. ");
		int num1 = sc.nextInt();
		
		for(int i = 9; i > 0;i--) {
			System.out.printf("%d * %d = %d\n",num1,i,num1*i);
		}
		
		sc.close();
	}

}
