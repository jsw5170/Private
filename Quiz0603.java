package chapter06;

import java.util.Scanner;

public class Quiz0603 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("하나의 정수를 입력하세요.");
		
		int num = sc.nextInt();
		int i = num, result = 1;
		while (i>0) {
			System.out.print(i);
			if(i>1)
				System.out.print(" * ");
			
			result = result * i;
			i--;
		}
		System.out.println(" = " + result);
		sc.close();
	}

}
