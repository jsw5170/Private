package chapter06;

public class Quiz0604 {

	public static void main(String[] args) {
		int i = 0 ;
		int result = 0;
		do {
			i++;
			result = result + i;
			if( i!= 1000) {
			System.out.print(i+" + ");
			} else if(i ==1000) {
				System.out.print(i);
			}
		}while(i != 1000);
		System.out.println(" = " + result);
		
	}

}
