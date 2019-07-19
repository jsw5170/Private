package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Puzzle_3By3 {

	private static int ArrayList;

	public static void main(String[] args) {
		Random ran = new Random();
		String[][] arr = new String[3][3];
		int rand = ran.nextInt(9);
		arr = setPuzzle();
		while (true) {
			rand = game(arr, rand);
			if (rand < 0 || rand > 9)
				break;
		}
	}

	public static int game(String[][] arr, int rand) {
		Scanner s = new Scanner(System.in);
		String[][] num = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "x" } };
		int x = 0;
		arr = screen(arr);

		System.out.println("[ Move ] a:Left s:Right w:Up z:Down");
		System.out.println("[ Exit ] k:Exit ");
		System.out.print("이동키를 입력하세요 : ");
		String str = s.next();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j].equals(num[i][j])) {
					x++;
				}
			}
		}
		if (x == 8) {
			System.out.println("정답입니다. 게임을 종료합니다.");
			System.out.println("Good bye~");
			rand = 10;
			return rand;
		}
		arr = control(arr, str);
		if (str.equals("k")) {
			System.out.println("Good bye~");
			rand = 10;
		}
		return rand;
	}

	public static String[][] setPuzzle() {
// x 포함 배열 
		String[][] num = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "X" } };
		String[][] arr = new String[3][3];
		Random ran = new Random();
		String temp;
		int i=2,j=2;
		for (int n = 0; n < 3; n++) {
			int nr = ran.nextInt(4);
			if (nr == 0) {
				if (j > 0) {
					temp = num[i][j];
					num[i][j] = num[i][j - 1];
					num[i][j - 1] = temp;
				}
			} else if (nr == 1) {
				if (j < 2) {
					temp = num[i][j];
					num[i][j] = num[i][j + 1];
					num[i][j + 1] = temp;
				}
			} else if (nr == 2) {
				if (i > 0) {
					temp = num[i][j];
					num[i][j] = num[i - 1][j];
					num[i - 1][j] = temp;
				}
			} else if (nr == 3) {
				if (i < 2) {
					temp = num[i][j];
					num[i][j] = num[i + 1][j];
					num[i + 1][j] = temp;
				}
			}
			for (int a = 0; a < num.length; a++) {
				for (int b = 0; b < num[a].length; b++) {
					if (num[a][b].equals("X") ) {
						i = a;
						j = b;
					}
				}
			}
		}
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[x].length; y++) {
				arr[x][y] = num[x][y];
			}
		}
		return arr;
	}

	public static String[][] screen(String[][] arr) {
		// 화면에 출력
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		return arr;
	}

	public static String[][] control(String[][] arr, String str) {
		String temp;
		int i = 0, j = 0;
		for (int a = 0; a < arr.length; a++) {
			for (int b = 0; b < arr[a].length; b++) {
				if (arr[a][b] == "X") {
					i = a;
					j = b;
				}
			}
		}

		switch (str) {
		case "a": {
			if (j > 0) {
				temp = arr[i][j];
				arr[i][j] = arr[i][j - 1];
				arr[i][j - 1] = temp;
				return arr;
			}
			if (j == 0) {
				System.out.println("불가");
				return arr;
			}
		}
		case "s": {
			if (j < 2) {
				temp = arr[i][j];
				arr[i][j] = arr[i][j + 1];
				arr[i][j + 1] = temp;
				return arr;
			}
			if (j == 2) {
				System.out.println("불가");
				return arr;
			}

		}
		case "w": {
			if (i > 0) {
				temp = arr[i][j];
				arr[i][j] = arr[i - 1][j];
				arr[i - 1][j] = temp;
				return arr;
			}
			if (i == 0) {
				System.out.println("불가");
				return arr;
			}
		}
		case "z": {
			if (i < 2) {
				temp = arr[i][j];
				arr[i][j] = arr[i + 1][j];
				arr[i + 1][j] = temp;
				return arr;
			}
			if (i == 2) {
				System.out.println("불가");
				return arr;
			}

		}
		}
		return arr;
	}
}
