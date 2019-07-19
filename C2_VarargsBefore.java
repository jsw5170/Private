
public class C2_VarargsBefore {

	public static void showAll(String...vargs) {
		System.out.println("LEN : "+ vargs.length);
		for(String s : vargs)
			System.out.print(s + '\t');
		System.out.println();
	}
	public static void main(String[] args) {
		showAll(new String[] {"Box"});
		showAll(new String[] {"Box","Toy"});
		showAll(new String[] {"Box","Toy","Apple"});
	}

}
//매개변수의 가변 인자 선언은 자바 5에서 추가된 문법이다.
//따라서 그 이전의 코드는 위와 같이 작업해야 한다.