class Apple4 {
	public String toString() {
		return "I am an apple";
	}
}
class Orange4 {
	public String toString() {
		return "I am an Orange";
	}
}
class Box4 {
	private Object ob;

	public void set(Object o) {
		ob = o;
	}

	public Object get() {
		return ob;
	}
}
public class A4_FruitAndBoxFault2 {
	public static void main(String[] args) {
		Box4 aBox = new Box4();
		Box4 oBox = new Box4();

		// 과일을 박스에 담은 것일까?
		aBox.set("Apple");
		oBox.set("Orange");

		System.out.println(aBox.get());
		System.out.println(oBox.get());
	}
}

// 프로그래머의 실수가 실행 과정에서 
// 조차 발견되지 않을 수 있다.