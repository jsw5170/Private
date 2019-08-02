import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyPhoneBook2 {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	Scanner sc = new Scanner(System.in);
	Connection con;
	public static void main(String[] args) {
		MyPhoneBook2 mpb = new MyPhoneBook2();
		mpb.doRun();
	}

	public void doRun() {
		connectDatabase();
		int choice;
		while (true) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 0:
				allView();
				break;
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
				favoriteAdd();
				break;
			case 5:
				favoriteView();
				break;
			case 6:
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
		System.out.println("0.전체 조회");
		System.out.println("1.전화번호 입력");
		System.out.println("2.전화번호 조회");
		System.out.println("3.전화번호 삭제");
		System.out.println("4.즐겨찾기 등록");
		System.out.println("5.즐겨찾기 조회");
		System.out.println("6.종료");
		System.out.print("선택 : ");
	}
	public void allView() {
		try {
			String sql = "select * from phonebook ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("name : " + rs.getString(1) + ", " +
									"phonebook : " + rs.getString(2) + ", " +
									"email : " + rs.getString(3));
			}
		} catch (SQLException sqle) {
			System.out.println("Connection Error");
			sqle.printStackTrace();
		}
	}
	public void addNumber() {
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String phoneNumber = sc.nextLine();
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		String sql = "insert into phonebook values(?, ?, ?, 0)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, name);		// 오라클은 1부터 시작 
			pstmt.setString(2, phoneNumber);
			pstmt.setString(3, email);
			int updateCount = pstmt.executeUpdate();
			System.out.println("데이버 베이스에 추가 되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("데이버 베이스 입력 에러입니다.");
		}
	}

	public void selNumber() {
		System.out.print("조회할 이름 : ");
		String name = sc.nextLine();
		String sql = "select * from phonebook where name = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("name : " + rs.getString(1));
				System.out.println("phonebook : " + rs.getString(2));
				if(rs.getString(3) != null) {
					System.out.println("email : " + rs.getString(3));
				}else {
					System.out.println("해당 값이 없습니다.");
				}
			}
		}catch(Exception e) {
			System.out.println("알 수 없는 에러가 발생했습니다..");
		}
	}

	public void delNumber() {
		System.out.print("삭제할 이름 : ");
		String name = sc.nextLine();
		String sql = "delete from phonebook where name = ?";
		try {
			
			PreparedStatement pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, name);
			int updateCount = pstmt.executeUpdate();
			System.out.println("데이버 베이스에서 삭제 되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("데이터베이스 삭제 에러입니다.");
		}
	}
	public void favoriteAdd() {
		System.out.print("조회할 이름 : ");
		String name = sc.nextLine();
		String sql = "update phonebook set favorites = 1 where name = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			int updateCount = pstmt.executeUpdate();
			System.out.println("즐겨찾기에 추가 되었습니다.");		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("알 수 없는 에러가 발생했습니다..");
		}
	}
	public void favoriteView() {
		String sql = "select * from phonebook where favorites = 1";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.print("name : " + rs.getString(1));
				System.out.print(" phonebook : " + rs.getString(2));
				if(rs.getString(3) != null) {
					System.out.print(" email : " + rs.getString(3));
				}else {
					System.out.print("해당 값이 없습니다.");
				}
				if(rs.getInt(4) != 0)
					System.out.println(" 즐겨찾기 O " );
				else {
					System.out.println(" 즐겨찾기 X " );
				}
			}
		}catch(Exception e) {
			System.out.println("알 수 없는 에러가 발생했습니다..");
		}
	}
	
	public void connectDatabase() {
		try  {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"scott",
					"tiger");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
