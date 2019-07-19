class Customer {
	enum Gender {
		MALE, FEMALE
	}

	private String name;
	private Gender gen;

	Customer(String n, String g) {
		name = n;

		if (g.equals("man"))
			gen = Gender.MALE;
		else
			gen = Gender.FEMALE;
	}

	@Override
	public String toString() {
		if (gen == Gender.MALE)
			return "Thank you, Mr " + name;
		else
			return "Thank you, Mrs " + name;
	}
}

public class A5_InnerEnum {

	public static void main(String[] args) {
		Customer cus1 = new Customer("Brown", "man");
		Customer cus2 = new Customer("Susan Hill", "woman");

		System.out.println(cus1);
		System.out.println(cus2);

	}

}
// 클래스 내에 열거형이 정의되면 해당 클래스 내에서만
// 사용 가능한 열거형이 된다.