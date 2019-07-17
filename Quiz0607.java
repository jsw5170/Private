package chapter06;

public class Quiz0607 {

	public static void main(String[] args) {
		
		for(int i = 1;i<100;i++) {
			if (i % 7 == 0 && i % 9 == 0) {
				System.out.println("7과 9의 배수입니다. " + i);
			}else if(i % 9 == 0) {
				if(i % 7 != 0)
				System.out.println("9의 배수입니다. " + i);
			}else if(i % 7 == 0){
				if(i % 9 != 0)
				System.out.println("7의 배수입니다. " + i);
			}
			
		}
		
	}

}
