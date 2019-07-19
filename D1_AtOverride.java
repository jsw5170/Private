interface Viewable1 {
	public void showIt(String str);
}

class Viewer1 implements Viewable1 {
	@Override
	public void showIt(String str) {
		System.out.println(str);
	}
}

public class D1_AtOverride {

	public static void main(String[] args) {

		Viewable1 view = new Viewer1();
		view.showIt("Hello Annotations");

	}

}
// 오버라이딩을 올바르게 했는지 컴파일러가 체크하게 한다.
