package ch06;

public class C_Pratice {

	public static void main(String[] args) {
		int a,b,c;
		
		for(a=0;a<2;a++) {
			System.out.println("연습 : " + a);
			for(b=-2;b<0;b++) {
				System.out.println("연습2 : " + b);
				for(c=100;c>99;c--)
					System.out.println("연습3 : " +c);
				if(c<=100);break;
			}
		}
	}

}
