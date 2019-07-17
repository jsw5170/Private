package chapter13_1;

public class Quiz1302_06 {

	public static void main(String[] args) {
		int[][] arr = new int[3][9];
		
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j]=(i+2)*(j+1);
				System.out.printf("%d * %d = %d\t",(i+2),(j+1),arr[i][j]);
			}
			System.out.println();
		}
	}

}
