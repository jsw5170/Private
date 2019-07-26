package step10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class MyPhoneBook {

	Scanner sc = new Scanner(System.in);
	Map<String, PhoneInfo> map = new HashMap<>();

	public static void main(String[] args) {
		MyPhoneBook mpb = new MyPhoneBook();
		mpb.doRun();
	}

	public void doRun() {
		// readInfo();
		readCSV();
		int choice;
		while (true) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addNumber();
				break;
			case 2:
				selNumber();
				break;
			case 3:
				delNumber();
				break;
			case 4:
				// saveInfo();
				createCSV();
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.");
				break;
			}
		}
	}

	public void showMenu() {
		System.out.println("[메뉴 선택]");
		System.out.println("1.전화번호 입력");
		System.out.println("2.전화번호 조회");
		System.out.println("3.전화번호 삭제");
		System.out.println("4.종료");
		System.out.print("선택 : ");
	}

	public void addNumber() {
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String phoneNumber = sc.nextLine();
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		PhoneInfo pInfo;
		if (email != null) {
			pInfo = new PhoneInfo(name, phoneNumber, email);
		} else {
			pInfo = new PhoneInfo(name, phoneNumber);
		}
		pInfo.showPhoneInfo();
		map.put(name, pInfo);
		System.out.println("맵의 크기 : " + map.size());
	}

	public void selNumber() {
		System.out.print("조회할 이름 : ");
		String name = sc.nextLine();

//		Set<String> ks = map.keySet();
//		for(String s : ks)
//			System.out.println(map.get(s).toString());
//		System.out.println("===========================");

		PhoneInfo pInfo = map.get(name);
		if (pInfo != null) {
			pInfo.showPhoneInfo();
		} else {
			System.out.println("해당 값이 없습니다.");
		}
	}

	public void delNumber() {
		System.out.print("삭제할 이름 : ");
		String name = sc.nextLine();

		PhoneInfo pInfo = map.remove(name);
		if (pInfo != null) {
			System.out.println("삭제 되었습니다.");
//			pInfo.showPhoneInfo();	// 삭제한 내용 출력
		} else {
			System.out.println("해당 값이 없습니다.");
		}
//		Set<String> ks = map.keySet();
//		for(String s : ks)
//			System.out.println(map.get(s).toString());
//		System.out.println("===========================");
	}

	public void saveInfo() {
		try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("Object.bin"))) {
			Set<String> ks = map.keySet();
			for (String s : ks) {
				PhoneInfo pInfo = map.get(s);
				oo.writeObject(pInfo);
				// oo.writeObject(map.get(s)); 같은 결과
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readInfo() {
		try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("Object.bin"))) {
			while (true) {
				PhoneInfo pInfo = (PhoneInfo) oi.readObject();
				if (pInfo == null)
					break;
				map.put(pInfo.name, pInfo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	public void createCSV() {
		PhoneInfo pInfo;

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("phoneNumber.csv"))) {
			Set<String> ks = map.keySet();
			for (String d : ks) {
				pInfo = map.get(d);
				if (pInfo.email != null) {
					bw.write(pInfo.name + "," + pInfo.phoneNumber + "," + pInfo.email);
					bw.newLine();
				} else {
					bw.write(pInfo.name + "," + pInfo.phoneNumber);
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readCSV() {
		try (BufferedReader br = new BufferedReader(new FileReader("phoneNumber.csv"))) {
			String str;
			String name;
			String phoneNumber;
			String email;
			while (true) {
				str = br.readLine();
				name = null;
				phoneNumber = null;
				email = null;
				if (str != null) {
					StringTokenizer v = new StringTokenizer(str, ",");
					name = v.nextToken();
					phoneNumber = v.nextToken();
					if (v.hasMoreTokens())
						email = v.nextToken();

					PhoneInfo pInfo;
					if (email != null) {
						pInfo = new PhoneInfo(name, phoneNumber, email);
					} else {
						pInfo = new PhoneInfo(name, phoneNumber);
					}
					map.put(name, pInfo);
				} else if (str == null)
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
