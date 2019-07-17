package chapter13_1;

import java.util.Scanner;

public class Quiz1301_03 {

	public static void main(String[] args) {
		int[] ary = new int[10];
		Scanner sc = new Scanner(System.in);
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
		System.out.print("홀수 : ");
		for (int e = 0; e < ary.length; e++) {
			if (ary[e] % 2 == 0)
				break;
			if (ary[e] != 0)
				System.out.print(ary[e] + " ");
		}
		System.out.println();
		System.out.print("짝수 : ");
		for (int e = ary.length - 1; e > 0; e--) {
			if (ary[e] % 2 != 0)
				break;
			if (ary[e] != 0)
				System.out.print(ary[e] + " ");
		}
		sc.close();
	}

}
