import java.util.Scanner;

public class B1_SwitchBasic {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("(1~3)의 정수를 입력하세요.");
		
		int n = sc.nextInt();
		
		switch(n) {
		case 1:
			System.out.println("Simple Java");
		case 2:
			System.out.println("Funny Java");
		case 3:
			System.out.println("Fantastic Java");
		default:
			System.out.println("The best programing language");
		}
		
		System.out.println("Do you like Java?");
	}

}
