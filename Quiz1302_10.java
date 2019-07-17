package chapter13_1;

public class Quiz1302_10 {

	public static void main(String[] args) {
		int[][] arr = new int[4][4];
		int num = 0;
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				num++;
				arr[i][j] = num;
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		arr=spin(arr);
		arr=spin(arr);
		arr=spin(arr);
	}
	public static int[][] spin(int[][] arr) {
		int[][] arr1 = new int[4][4];
		for(int i=0;i<arr1.length;i++) {
			for(int j=0;j<arr1[i].length;j++) {
				arr1[j][i] = arr[i][arr1.length-1-j];
				System.out.print(arr1[j][i]+" ");
			}
			System.out.println();
		}
		return arr1;
	}

}
