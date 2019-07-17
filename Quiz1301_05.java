package chapter13_1;

import java.util.Scanner;

public class Quiz1301_05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("단어를 입력하시오 : ");
		String str = sc.next();
		char[] ary = str.toCharArray();
		boolean pa = false;
		for (int e = 0; e < ary.length / 2; e++) {
			if (ary[e] != ary[ary.length - 1 - e]) {
				break;
			}
			pa = true;
		}
		if (pa == true)
			System.out.println("회문입니다.");
		else
			System.out.println("회문이 아닙니다.");

		sc.close();
	}

}
