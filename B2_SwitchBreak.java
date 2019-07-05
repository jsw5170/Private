import java.util.Scanner;

public class B2_SwitchBreak {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("(1~3)의 정수를 입력하세요.");
		
		int n = sc.nextInt();
		
		switch(n) {
		case 1:
			System.out.println("Simple Java");
			break;
		case 2:
			System.out.println("Funny Java");
			break;
		case 3:
			System.out.println("Fantastic Java");
			break;
		default:
			System.out.println("The best programing language");
		}
		
		System.out.println("Do you like Java?");
	}

}
