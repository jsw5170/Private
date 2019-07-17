package chapter13_1;

import java.util.Scanner;

public class Quiz1301_01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[5];
		System.out.println("5개의 정수를 입력하세요. ");
		for(int i=0;i<5;i++) {
			System.out.println("정수를 입력하세요. ");
			ary[i] = sc.nextInt();
		}
		System.out.printf("최대 값 : %d \t 최소 값 : %d\t 합 계 : %d",max(ary),min(ary),sum(ary));
		sc.close();
	}
	public static int max(int[] ary)
	{
		int max = ary[0];
		for(int i=0;i<ary.length;i++) {
			if (max < ary[i]) {
				max =ary[i];
			}
		}
		return max;
	}
	public static int min(int[] ary)
	{
		int min = ary[0];
		for(int i=0;i<ary.length;i++) {
			if(min>ary[i]) {
				min =ary[i];
			}
		}
		return min;
	}
	public static int sum(int[] ary)
	{
		int sum = 0;
		for(int i=0;i<ary.length;i++) {
			sum +=ary[i];
		}
		return sum;
	}
}
