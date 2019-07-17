class Box {
	private String contents;
	private int boxNum;

	Box(int num, String cont) {
		boxNum = num;
		contents = cont;
	}

	public int getBoxNum() {
		return boxNum;
	}

	public String toString() {
		return contents;
	}
}

public class D2_EnhancedForInst {
	public static void main(String[] args) {
		Box[] ar = new Box[5];

		ar[0] = new Box(101, "Coffee");
		ar[1] = new Box(202, "Computer");
		ar[2] = new Box(303, "Apple");
		ar[3] = new Box(404, "Dress");
		ar[4] = new Box(505, "Fairy-tale book");

		for (Box e : ar) {
			if (e.getBoxNum() == 505)
				System.out.println(e);
//println은 클래스 변수를 호출시 toString를 출력.
//toString가 없을 시 클래스 변수의 해시값을 출력.
		}
	}
}
