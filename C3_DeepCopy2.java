class MyText2 implements Cloneable {
	private String name;

	public MyText2(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class MyBook2 implements Cloneable {
	public MyText2 mt;

	public MyBook2(String str) {
		this.mt = new MyText2(str);
	}

	// 정보를 수정함
	public void changeText(String str) {
		mt.setName(str);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// Object 클래스의 clone 메소드 호출 결과를 얻음
		MyBook2 copy = (MyBook2)super.clone();
		
		// 깊은 복사의 형태로 복사본을 수정
		copy.mt = (MyText2)mt.clone();
		
		// 완성된 복사본의 참조를 반환
		return copy;
	}

	// 정보 출력
	public void showText() {
		System.out.println(mt.getName());
	}
}
public class C3_DeepCopy2 {

	public static void main(String[] args) {
		MyBook2 org = new MyBook2("홍길동");
		MyBook2 cpy;

		try {
			// 인스턴스 복사
			cpy = (MyBook2) org.clone();
			
			// 한 인스턴스의 좌표 정보를 수정
			org.changeText("전우치");
			
			// 두 객체의 값이 같이 변경되었음을 확인
			org.showText();
			cpy.showText();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}