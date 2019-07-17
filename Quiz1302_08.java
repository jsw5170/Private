package chapter13_1;

import java.util.Scanner;

public class Quiz1302_08 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[4][4];
		String[] str = { "이순신", "강감찬", "을지문덕", "권율" };
		String[] str1 = { "국어", "영어", "수학", "국사" };
		int[] avg = new int[4], sum = new int[4];
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i] + "의 점수를 입력하세요. ");
			for (int j = 0; j < arr.length; j++) {
				System.out.print(str1[j] + " : ");
				arr[i][j] = sc.nextInt();
			}
		}
		System.out.print("구분\t");
		for (int i = 0; i < str.length; i++) {
			System.out.print(str[i] + "\t");
		}
		System.out.println("총점");
		for (int j = 0; j < arr.length; j++) {
			System.out.print(str1[j] + "\t");
			for (int i = 0; i < str.length; i++) {
				System.out.printf("%d\t", arr[i][j]);
				avg[j] += arr[i][j];
			}
			System.out.print(avg[j]);
			System.out.println();
		}
		System.out.print("총점\t");
		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				sum[i] += arr[i][j];
			}
			System.out.print(sum[i] + "\t");
		}
	}
}
