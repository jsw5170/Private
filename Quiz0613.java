package chapter06;

public class Quiz0613 {

	public static void main(String[] args) {
		System.out.println("가로");
		for(int j = 1; j < 10; j++) {
			for(int i = 2; i < 10; i++) {
				System.out.printf("%d*%d=%d\t",i,j,i*j);
			}
			System.out.println("");
		}
		System.out.println("세로");
		for(int i = 2; i < 10; i++){
			for(int j = 1; j < 10; j++) {
				System.out.printf("%d*%d=%d\t",i,j,i*j);
			}
			System.out.println("\n");
		}
	}

}
