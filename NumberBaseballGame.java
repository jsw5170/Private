package Game;

import java.util.Random;
import java.util.Scanner;

public class NumberBaseballGame {
	static int cnt=0;
	public static void main(String[] args) {
		
		int cNum,uNum,end=0;
		cNum = comNum();
		System.out.println("숫자로 하는 야구게임 시작");
		while(true) {
		uNum = userNum();
		end=compare(cNum,uNum);
		if(end ==1) break;
		}
	}
	public static int compare(int comNum,int userNum) {
		int strike=0,ball=0,compare=0;
		int ctNum = comNum/100,cdNum = comNum/10%10,coNum = comNum%10;
		int utNum = userNum/100,udNum = userNum/10%10,uoNum = userNum%10;
		System.out.printf("%d : %d : %d\n",utNum,udNum,uoNum);
		if(ctNum==utNum) strike++;
		if(cdNum==udNum) strike++;
		if(coNum==uoNum) strike++;
		
		System.out.printf("%d Strike ",strike);
		
		if(ctNum==udNum || ctNum==uoNum) ball++;
		if(cdNum==utNum || cdNum==uoNum) ball++;
		if(coNum==utNum || coNum==udNum) ball++;
		
		System.out.printf("%d Ball\n",ball);
		
		if(strike ==0 && ball ==0) System.out.println("Out!");
		cnt++;
		if(strike ==3) {
			System.out.printf("%d : %d : %d\n",ctNum,cdNum,coNum);
			System.out.println("YOU WIN!!");
			compare =1;
		}
		return compare;
	}
	public static int userNum() {
		Scanner sc = new Scanner(System.in);
		System.out.printf("세자리 숫자를 입력하세요. (%d회)",cnt);
		int userNum = sc.nextInt();
		return userNum;
	}
	public static int comNum() {
		Random randomV1 = new Random();
		while(true){
			int i = randomV1.nextInt(1000);
			if(i<=100) {
				continue;
			}
			if(i/100 != i/10%10 && i/100 != i%10 && i/10%10 != i%10){
				return i;			
			}
		}
	}
}
/*
 * 		Random randomV1 = new Random();
 * 		Scanner sc = new Scanner(System.in);
		
		while(true){
			int ctNUm = randomV1.nextInt(9)+1;
			int cdNum = randomV1.nextInt(10);
			int coNum = randomV1.nextInt(10);
			if(i1 != i2 && i1 != i3 && i2 != i3){
				break;
			}
		}
		System.out.printf("세자리 숫자를 입력하세요. (%d회)",cnt);
		int userNum = sc.nextInt();
		int utNum = userNum/100,udNum = userNum/10%10,uoNum = userNum%10;

 *		if(ctNum==utNum) strike++;cdNum = randomV1.nextInt(10); coNum = randomV1.nextInt(10);if(cdNum != ctNum && coNum != ctNum) break;
		if(cdNum==udNum) strike++;coNum = randomV1.nextInt(10); ctNUm = randomV1.nextInt(9)+1;if(ctNum != cdNum && coNum != cdNum) break;
		if(coNum==uoNum) strike++;cdNum = randomV1.nextInt(10); ctNUm = randomV1.nextInt(9)+1;if(cdNum != coNum && ctNum != coNum) break;
		
		if(ctNum==udNum || ctNum==uoNum) ball++; 
		if(st==0) cdnum=ctnum;coNum = randomV1.nextInt(10); ctNUm = randomV1.nextInt(9)+1; compare;
		if(st==1) if(cdnum=udnum) conum=ctnum; ctNUm = randomV1.nextInt(9)+1; compare; if()
	
		if(cdNum==utNum || cdNum==uoNum) ball++; 
		if(coNum==utNum || coNum==udNum) ball++;
 
 * */
 