package chapter13_1;

import java.util.Scanner;

public class Quiz1301_02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] ary = { 'G', 'o', 'o', 'd', ' ', 't', 'i', 'm', 'e' };
		for (int i = 0; i < ary.length; i++) {
			System.out.print(ary[i]);
		}
		sc.close();
	}
}
