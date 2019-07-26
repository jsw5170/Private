package PhoneBook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Data {
	String name;
	String phoneNumber;
	String eMail;
	static HashMap<String, Data> map = new HashMap<>();
	public Data(String name, String phoneNumber, String eMail) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
	}

	public Data(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	static void showInfo(Data d) {
		System.out.println("이름 : " + d.name);
		System.out.println("전화번호 : " + d.phoneNumber);
		System.out.println("이메일 : " + d.eMail);
	}
}
class Menu{
	static int menu(List<Data> db) {
		Scanner sc = new Scanner(System.in);
		int n = 0;
		System.out.println("원하는 메뉴를 선택하세요.");
		System.out.println("1. 전화번호 입력");
		System.out.println("2. 전화번호 조회");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 종료");
		n=sc.nextInt();
		switch(n)
		{
		case 1:
			DataInput.input(db);
			break;
		case 2:
			DataSearch.search(db);
			break;
		case 3:
			DataRemove.remove(db);
			break;
		case 4:
			System.out.println("종료합니다.");
			break;
		default :
			System.out.println("잘 못 된 입력입니다.");
			break;
		}
		return n;
	}
}
class DataInput extends Data{
	public DataInput(String name, String phoneNumber, String eMail) {
		super(name, phoneNumber, eMail);
		// TODO Auto-generated constructor stub
	}

	static List<Data> input(List<Data> db) {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.print("전화번호를 입력하세요 : ");
		String pNumber = sc.nextLine();
		System.out.print("이메일을 입력하세요 : ");
		String eMail = sc.nextLine();
		Data d = new Data(name,pNumber,eMail);
		db.add(d);
		showInfo(d);
		map.put(name,d);
		return db;
	}
}
class DataSearch extends Data{
	public DataSearch(String name, String phoneNumber, String eMail) {
		super(name, phoneNumber, eMail);
		// TODO Auto-generated constructor stub
	}

	static List<Data> search(List<Data> db) {
		Scanner sc = new Scanner(System.in);
		System.out.print("찾기를 원하는 이름을 입력하세요 : ");
		String searchName = sc.nextLine();
		for (Iterator<Data> d = db.iterator(); d.hasNext();) {
			Data dbs = d.next();
			if (dbs.name.equals(searchName))  {
				showInfo(dbs);
				return db;
			}
		}
		System.out.println("검색 결과가 없습니다.");
		return db;
	}
}
class DataRemove extends Data{
	public DataRemove(String name, String phoneNumber, String eMail) {
		super(name, phoneNumber, eMail);
		// TODO Auto-generated constructor stub
	}

	static void remove(List<Data> db) {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제를 원하는 이름을 입력하세요 : ");
		String searchName = sc.nextLine();
		for (Iterator<Data> d = db.iterator(); d.hasNext();) {
			Data dbs = d.next();
			if (dbs.name.equals(searchName))  {
				showInfo(dbs);
				d.remove();
				map.remove(d);
			}
		}
	}
}