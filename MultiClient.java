import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/*콘솔 멀티채팅 클라이언트 프로그램 */
public class MultiClient {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		String id = null;
		try {
			String ServerIP = "localhost";
			if (args.length > 0)
				ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999); 
			System.out.println("서버와 연결이 되었습니다.....");
			id = doRun(socket);
			Thread receiver = new Receiver6(socket);
			receiver.start();
			new ChatWin(socket, id);
		} catch (Exception e) {
			System.out.println("예외[MultiClient class] : " + e);
			e.printStackTrace();
		}
	}
	
	public static String doRun(Socket socket) throws UnsupportedEncodingException {
		PrintWriter out = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			System.out.println("예외 : " + e);
		}
		String choice;
		String id = null;
		while (true) {
			showMenu();
			choice = sc.nextLine();
			out.println(URLEncoder.encode(choice, "UTF-8"));
			switch (choice) {
			case "1":
				signup(out);
				break;
			case "2":
				id = signin(out);
				return id;
			case "3":
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("잘 못 입력하셨습니다.");
				break;
			}
		}
	}

	public static void showMenu() {
		System.out.println("┌─────────────┐");
		System.out.println("│ [메뉴 선택] │");
		System.out.println("│ 1.회원가입  │");
		System.out.println("│ 2.로그인    │");
		System.out.println("│ 3.종료      │");
		System.out.println("└─────────────┘");
		System.out.print(" 선택 : ");
	}

	public static void signup(PrintWriter out) {
		try {
			System.out.print("ID : ");
			String id = sc.nextLine();
			out.println(URLEncoder.encode(id, "UTF-8"));
			System.out.print("Password : ");
			String pw = sc.nextLine();
			out.println(URLEncoder.encode(pw, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
	}

	public static String signin(PrintWriter out) {
		try {
			System.out.print("ID : ");
			String id = sc.nextLine();
			out.println(URLEncoder.encode(id, "UTF-8"));
			System.out.print("Password : ");
			String pw = sc.nextLine();
			out.println(URLEncoder.encode(pw, "UTF-8"));
			return id;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;	
	}
}
