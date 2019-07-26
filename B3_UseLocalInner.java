interface Printable2{
	void print();
}
class Papers2 {
	private String con;
	public Papers2(String s) {	con = s;}
	public Printable2 getPrinter() {
		class Printer2 implements Printable2 {
			public void print() {
				System.out.println(con);
			}
		}
		return new Printer2();
	}
}
public class B3_UseLocalInner {

	public static void main(String[] args) {
		Papers2 p = new Papers2("서류 내용 : 행복합니다.");
		Printable2 prn = p.getPrinter();
		prn.print();
	}

}
// 로컬 클래스는 바로 위에서 소개한 멤버 클래스와
// 상당 부분 유사하다.
// 지역 내에 정의된다는 점에서만 차이를 보임