package chapter13_1;

public class Quiz1302_07 {

	public static void main(String[] args) {
		int[][] arr1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
		int[][] arr2 = new int[4][2];

		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[i].length; j++) {
				arr2[j][i] = arr1[i][j];
			}
		}
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length; j++) {
				System.out.print(arr2[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
