package chapter06;

public class Quiz0611 {

	public static void main(String[] args) {
		int i = 0;
		int result = 0;
		do {
			i++;
			if(i % 2 == 0) 
				result = result + i;
		}while(i<101);
		System.out.println("결과 : " + result);
	}

}
