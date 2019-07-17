package chapter13_1;

import java.util.Scanner;

public class Quiz1301_04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[10];
		int i = 0, j = 9, num = 0;
		for (int e = 0; e < ary.length; e++) {
			System.out.println("정수를 입력하세요. ");
			num = sc.nextInt();
			if (num % 2 != 0) {
				ary[i] = num;
				i++;
			} else {
				ary[j] = num;
				j--;
			}
		}
		for (int e = 0; e < ary.length; e++) {
			System.out.print(ary[e] + " ");
		}
		sc.close();
	}
}
