package Game;

import java.util.Random;
import java.util.Scanner;

public class reverse {

	static int strike = 0, ball = 0, compare = 0 , cnt=0;
	static int s1=0,s2=0,s3=0,b1=0,b2=0,b3=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random randomV1 = new Random();
		int cNum;
		while(true)
		{
			int ctNum = randomV1.nextInt(9)+1;
			int cdNum = randomV1.nextInt(10);
			int coNum = randomV1.nextInt(10);
			if(ctNum != cdNum && ctNum != coNum && cdNum != coNum) {
				cNum= ctNum*100+cdNum*10+coNum;
				compare(cNum);
			}
			if(strike ==3) break;
		}
		System.out.println(s1+s2+s3);
	}
	public static void compare(int comNum) {
		Random randomV1 = new Random();
		Scanner sc = new Scanner(System.in);
		int ctNum = comNum/100,cdNum = comNum/10%10,coNum = comNum%10;
		int temp=0;
		System.out.printf("%d : %d : %d\n",ctNum,cdNum,coNum);
		System.out.println("strike 는 몇개입니까?");
		int str1 = sc.nextInt();
		strike = str1;
		System.out.println("ball 는 몇개입니까?");
		int str2 = sc.nextInt();
		ball = str2;
		
		if(strike == 1) {
			temp = ctNum;
			ctNum=cdNum;
			cdNum=temp;
			comNum=ctNum*100+cdNum*10+coNum;
			compare(comNum);
			if(strike ==1) {
				s3 = coNum;
				ctNum = randomV1.nextInt(9)+1;
				cdNum = randomV1.nextInt(10);
				comNum=ctNum*100+cdNum*10+coNum;
				compare(comNum);
			}else {
				temp = ctNum;
				ctNum=coNum;
				coNum=temp;
				comNum=ctNum*100+cdNum*10+coNum;
				compare(comNum);
				if(strike==1) {
					s2=cdNum;
					ctNum = randomV1.nextInt(9)+1;
					coNum = randomV1.nextInt(10);
					comNum=ctNum*100+cdNum*10+coNum;
					compare(comNum);
				}
				else { 
					s1=ctNum;
					coNum = randomV1.nextInt(10);
					cdNum = randomV1.nextInt(10);
					comNum=ctNum*100+cdNum*10+coNum;
					compare(comNum);
				}
			}
		}else if(strike ==2) {
			temp = ctNum;
			ctNum=cdNum;
			cdNum=temp;
			comNum=ctNum*100+cdNum*10+coNum;
			compare(comNum);
			if(strike==1) {
				s3=coNum;
				ctNum = randomV1.nextInt(9)+1;
				cdNum = randomV1.nextInt(10);
				comNum=ctNum*100+cdNum*10+coNum;
				compare(comNum);
			}else {
				s1=ctNum;
				s2=cdNum;
				coNum = randomV1.nextInt(10);
				comNum=ctNum*100+cdNum*10+coNum;
				compare(comNum);
			}
		}
//		ctNum = randomV1.nextInt(9)+1;
//		cdNum = randomV1.nextInt(10);
//		coNum = randomV1.nextInt(10);
//		if(ball == 1) {
//			temp = ctNum;
//			ctNum=cdNum;
//			cdNum=temp;
//			coNum = randomV1.nextInt(10);
//			comNum=ctNum*100+cdNum*10+coNum;
//			compare(comNum);
//			if(strike ==1) {
//				temp = coNum;
//				coNum=cdNum;
//				cdNum=temp;
//				cdNum = randomV1.nextInt(10);
//				coNum = randomV1.nextInt(10);
//				comNum=ctNum*100+cdNum*10+coNum;
//				compare(comNum);
//				if(strike ==1) {
//					s1=ctNum;
//					cdNum = randomV1.nextInt(10);
//					coNum = randomV1.nextInt(10);
//					comNum=ctNum*100+cdNum*10+coNum;
//					compare(comNum);
//				}else {
//					s2=cdNum;
//					ctNum = randomV1.nextInt(9)+1;
//					coNum = randomV1.nextInt(10);
//					comNum=ctNum*100+cdNum*10+coNum;
//					compare(comNum);
//				}
//			}
//			if(ball==1)	{
//				
//			}
//		}else if (ball ==2) {
//			
//		}else if(ball ==3) {
//			
//		}
	}
}
