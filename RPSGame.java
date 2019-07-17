package Game;

import java.util.Random;
import java.util.Scanner;

public class RPSGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random randomV1 = new Random();
		while (true) {
			int i = randomV1.nextInt(3) + 1;
			int n;
			n = inPut(sc);
			versus(n, i);
			if (n == 0)
				break;
		}
		sc.close();
	}

	public static int inPut(Scanner sc) {
		System.out.print("무엇을 내겠습니까?<1: 가위, 2:바위, 3:보> : ");
		int num1 = sc.nextInt();
		if (num1 < 0 || num1 > 3) {
			System.out.println("잘못된 입력입니다.");
		}
		if (num1 == 0) {
			System.out.println("게임을 종료합니다.");
		}
		return num1;
	}

	public static void versus(int n1, int n2) {
		if (n1 == n2) {
			System.out.println("비겼습니다.");
			return;
		} else if (n1 == 1) {
			System.out.print("사용자 : 가위 ");
			if (n2 == 3) {
				System.out.println("컴퓨터 : 보");
				System.out.println("이겼습니다.");
			} else if (n2 == 2) {
				System.out.println("컴퓨터 : 바위");
				System.out.println("졌습니다.");
			}
		} else if (n1 == 2) {
			System.out.print("사용자 : 바위 ");
			if (n2 == 1) {
				System.out.println("컴퓨터 : 가위");
				System.out.println("이겼습니다.");
			} else if (n2 == 3) {
				System.out.println("컴퓨터 : 보");
				System.out.println("졌습니다.");
			}
		} else if (n1 == 3) {
			System.out.print("사용자 : 보 ");
			if (n2 == 2) {
				System.out.println("컴퓨터 : 바위");
				System.out.println("이겼습니다.");
			} else if (n2 == 1) {
				System.out.println("컴퓨터 : 가위");
				System.out.println("졌습니다.");
			}
		}
	}
}
/*
 * System.out.print("무엇을 내겠습니까?<1: 가위, 2:바위, 3:보> : "); int num1 = sc.nextInt();
 * if(num1 != 1 && num1 != 2 && num1 != 3 && num1 != 0) {
 * System.out.println("잘못된 입력입니다."); continue; }else if(num1 == i) {
 * System.out.println("비겼습니다."); continue; }else if(num1 ==1) {
 * System.out.print("사용자 : 가위 "); if(i==2) { System.out.println("컴퓨터 : 바위");
 * System.out.println("졌습니다."); }else if(i==3) { System.out.println("컴퓨터 : 보");
 * System.out.println("이겼습니다."); } continue; }else if(num1 ==2) {
 * System.out.print("사용자 : 바위 "); if(i==3) { System.out.println("컴퓨터 : 보");
 * System.out.println("졌습니다."); }else if(i==1) { System.out.println("컴퓨터 : 가위");
 * System.out.println("이겼습니다."); } continue; }else if(num1 ==3) {
 * System.out.print("사용자 : 보 "); if(i==1) { System.out.println("컴퓨터 : 가위");
 * System.out.println("졌습니다."); }else if(i==2) { System.out.println("컴퓨터 : 바위");
 * System.out.println("이겼습니다."); } continue; }else if(num1==0) {
 * System.out.println("게임을 종료합니다."); break; } sc.close();
 */
