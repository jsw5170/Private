public class QuizAZ {

	public static void main(String[] args) {
		int n1,n2;
		int result;
		
		for(n1=0;n1<10;n1++) {
			for(n2=0;n2<10;n2++) {
				result=n1 + n2;
				if(n1==n2) {
					continue;
				}
				if(result == 9){
					System.out.println(n1 +" "+ n2 +" : "+ n2 +" "+ n1 );
				}
			}
		}
	}
}
