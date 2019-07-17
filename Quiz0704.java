package chapter07;

import java.util.Scanner;

public class Quiz0704 {

	public static void main(String[] args) {
		double result;
		Scanner sc = new Scanner(System.in);
		System.out.println("섭 씨를 입력하세요. ");
		int cel = sc.nextInt();
		result = fahrenheit(cel);
		System.out.println("화 씨 "+result);
		
		System.out.println("화 씨를 입력하세요. ");
		int fah = sc.nextInt();
		result = celsius(fah);
		System.out.println("섭 씨 "+result);
		sc.close();

	}
	public static double celsius(int num) {
		double celsius = 1.8 * num + 32;
		return celsius;
	}
	public static double fahrenheit(int num) {
		double fahrenheit = (num-32)/1.8;
		return fahrenheit;
	}
}
