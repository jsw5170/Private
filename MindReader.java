package Game;

import java.util.Scanner;

public class MindReader {

	public static void main(String[] args) {
		playGame();
	}

	public static void playGame() {
		Scanner sc = new Scanner(System.in);
		int i = 50, j = 1, h = 101, l = 0;
		System.out.println("0부터 100 사이의 값 중에 하나를 생각하세요.");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 크면 h를 입력하세요.");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 작으면 l를 입력하세요.");
		System.out.println("제(컴)가 숫자를 맞췄다면 y를 입력해 주세요.");
		while (true) {
			System.out.println("당신이 선택한 숫자는 " + i + " 입니까?");
			String str = sc.next();
			if (str.equalsIgnoreCase("h")) {
				h = i;
				i = (l + h) / 2;
				j++;
			} else if (str.equalsIgnoreCase("l")) {
				l = i;
				i = (l + h) / 2;
				j++;
			} else if (str.equalsIgnoreCase("y")) {
				System.out.println("정답입니다. [" + j + "회차]");
				System.out.println("Good bye~");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}
