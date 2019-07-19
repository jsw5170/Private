enum Person4{
	 MAN, WOMAN ;
	
	@Override
	public String toString() {
		return "I am a cat person";
	}
}
public class B2_EnumConst {

	public static void main(String[] args) {
		System.out.println(Person4.MAN);
		// toString 메서드의 반환 값 출력 
		System.out.println(Person4.WOMAN);

	}

}
