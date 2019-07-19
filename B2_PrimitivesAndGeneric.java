class Box8<T> {
	private T ob;

	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}
public class B2_PrimitivesAndGeneric {
	public static void main(String[] args) {
		Box8<Integer> iBox = new Box8<Integer>();
// <> 안에 타입인자로 기본자료형(int 등) 이  
// 올 수 없음으로 래퍼 클래스를 사용해야 한다.
		
		iBox.set(125);			 // 오토 박싱 진행
		int num = iBox.get();	 // 오토 언박싱 진행

		System.out.println(num);
	}
}