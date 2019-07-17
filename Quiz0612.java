package chapter06;

import java.util.Scanner;

public class Quiz0612 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 개의 수를 입력하세요. ");
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int result = 0;
		if(num1 < num2){
			for(int i = num1;i <= num2; i++) {
				if(i != num2) {
					System.out.print(i + " + ");
				}else {
					System.out.print(i);
				}
				result = result + i;
			}
		}else {
			for(int i = num1;i >= num2; i--) {
				if(i != num2) {
					System.out.print(i + " + ");
				}else {
					System.out.print(i);
				}
				result = result + i;
			}
		}
		System.out.println(" = " + result);
		sc.close();
	}

}
