package Game;

import java.util.Random;
import java.util.Scanner;

public class HighLow {

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner sc = new Scanner(System.in);
		Scanner s = new Scanner(System.in);
		while(true)
		{
			int i = ran.nextInt(101);
			playGame(i);
			System.out.print("게임을 더 하시겠습니까? <y/n>...");
			String str = s.next();
			if (!str.equalsIgnoreCase("y")) {
				System.out.println("Good bye~");
				break;
			}
		}
	}
	public static int compare(int n,int i,int j) {
		if (n > 100 || n < 0) {
			System.out.println("잘못된 값을 입력하셨습니다.");
			return j=j-1;
		}
		if (n == i) {
			System.out.println(n + "는 정답입니다. 축하합니다!");
			System.out.println("High Low 게임을 플레이해 주셔서 감사합니다.");
		} else if (n > i) {
			System.out.println(n + "는 제가 정한 숫자보다 큽니다.");
			System.out.println("[ " + (j-1) + "] 의 기회가 남았습니다.");
		} else {
			System.out.println(n + "는 제가 정한 숫자보다 작습니다.");
			System.out.println("[ " + (j-1) + "] 의 기회가 남았습니다.");
		}
		return j;
	}
	public static void playGame(int i) {
		Scanner sc = new Scanner(System.in);
		System.out.println("나는 지금 0 부터 100 사이의 값 중에 하나를 생각하겠습니다.");
		System.out.println("당신은 그 숫자를 6회 안에 맞추시면 됩니다.");
		for (int j = 6; j >= 0; j--) {
			if (j == 0) {
				System.out.println("기회를 모두 사용하셨습니다.\n 다음기회에...");
				break;
			}
			System.out.println("몇이라고 생각합니까? < 0 to 100 >");
			int n = sc.nextInt();
			compare(n,i,j);
		}
	}
}
/*		while (true) {
			System.out.println("나는 지금 0 부터 100 사이의 값 중에 하나를 생각하겠습니다.");
			System.out.println("당신은 그 숫자를 6회 안에 맞추시면 됩니다.");
			int i = ran.nextInt(101);
			for (int j = 6; j >= 0; j--) {
				if (j == 0) {
					System.out.println("기회를 모두 사용하셨습니다.\n 다음기회에...");
					break;
				}
				System.out.println("몇이라고 생각합니까? < 0 to 100 >");
				int n = sc.nextInt();
				if (n > 100 || n < 0) {
					System.out.println("잘못된 값을 입력하셨습니다.");
					j++;
					continue;
				}
				if (n == i) {
					System.out.println(n + "는 정답입니다. 축하합니다!");
					System.out.println("High Low 게임을 플레이해 주셔서 감사합니다.");
					break;
				} else if (n > i) {
					System.out.println(n + "는 제가 정한 숫자보다 큽니다.");
					System.out.println("[ " + (j-1) + "] 의 기회가 남았습니다.");
				} else {
					System.out.println(n + "는 제가 정한 숫자보다 작습니다.");
					System.out.println("[ " + (j-1) + "] 의 기회가 남았습니다.");
				}
			}
			System.out.print("게임을 더 하시겠습니까? <y/n>...");
			String str = s.next();
			if (!str.equalsIgnoreCase("y")) {
				System.out.println("Good bye~");
				break;
			}
		}
	}
*/