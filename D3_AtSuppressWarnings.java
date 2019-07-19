interface Viewable3 {
	@Deprecated
	public void showIt(String str);

	public void brshowIt(String str);
}

class Viewer3 implements Viewable3 {
	@Override
	@SuppressWarnings("deprecation")
	public void showIt(String str) {
		System.out.println(str);
	}

	@Override
	public void brshowIt(String str) {
		System.out.println('[' + str + ']');
	}
}

public class D3_AtSuppressWarnings {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Viewable3 view = new Viewer3();
		view.showIt("Hello Annotations");
		view.brshowIt("Hello Annotations");

	}
}
//deprecation 관련 경고 메시지를 생략하라는 의미 