interface Printable3 {
	void print();
}

class Papers3 {
	private String con;
	public Papers3(String s) { con = s; }

	public Printable3 getPrinter() {
		return new Printable3() {
			public void print() {System.out.println(con);}
		};
	}
}

public class B4_UseAnonymousInner {

	public static void main(String[] args) {
		Papers3 p = new Papers3("서류 내용 : 행복합니다.");
		Printable3 prn = p.getPrinter();
		prn.print();
	}

}
// 익명 내부 클래스는 예전에 자바 UI 에서 이벤트를
// 처리하는 데 많이 사용했다.
// 현재는 안드로이드 프로그래밍에서 위젯의 이벤트
// 처리하는 핸들러를 구현할 때 사용한다.