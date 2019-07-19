interface Scale {
	int DO = 0, RE = 1, MI = 2, FA = 3, SO = 4, RA = 5, TI = 6;
}

public class A1_InterfaceBaseConst {
	public static void main(String[] args) {
		int sc = Scale.DO;
		
		switch(sc) {
		case Scale.DO:
			System.out.println("도~ ");
			break;
		case Scale.RE:
			System.out.println("레~ ");
			break;
		case Scale.MI:
			System.out.println("미~ ");
			break;
		case Scale.FA:
			System.out.println("파~ ");
			break;
		default:
			System.out.println("솔~ 라~ 시~ ");
		}
	}
}
