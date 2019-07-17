import java.util.Random;

public class QuizRandom {

	public static void main(String[] args) {
		while(true){
			Random randomV1 = new Random();
			int i = randomV1.nextInt(1000);
			if(i<=100) {
				continue;
			}
			if(i/100 != i/10%10 && i/100 != i%10 && i/10%10 != i%10){
				System.out.println(i);
				break;			
			}
		}
	}
}
