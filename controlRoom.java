import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class controlRoom extends Thread  {
	static Map<String, PrintWriter> clientMap;
	static List<Room> roomList = new ArrayList();
	static int pnRoom;
	static Scanner sc = new Scanner(System.in);
	static PrintWriter out = null;
	static BufferedReader in = null;
	private Socket socket;
	
	public void controlRoomT(Socket socket) {
		this.socket = socket;
		try {
			out = new PrintWriter(this.socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					this.socket.getInputStream(),"UTF-8"));
		} catch (Exception e) {
			System.out.println("예외 : " + e);
		}
	}
	public static Room createPRoom() {
		System.out.print("방 이름 : ");
		String roomName = sc.nextLine();
		int pnRoom = 0;
		Room pRoom = new Room(pnRoom);
		pRoom.roomName = roomName;
		roomList.add(pRoom);
		return pRoom;
	}

	public static Room createPRoom(Client owner) {
		System.out.print("방 이름 : ");
		String roomName = sc.nextLine();
		Room pRoom = new Room(pnRoom);
		pRoom.enterClient(owner);
		pRoom.setOwner(owner);
		pRoom.roomName = roomName;
		roomList.add(pRoom);
		return pRoom;
	}

	public static Room createPRoom(List clients) {
		System.out.print("방 이름 : ");
		String roomName = sc.nextLine();
		Room pRoom = new Room(pnRoom);
		pRoom.enterClient(clients);
		pRoom.roomName = roomName;
		roomList.add(pRoom);
		return pRoom;
	}

	public static Room getRoom(Room room) {
		int index = roomList.indexOf(room);
		if (index > 0) {
			return (Room) roomList.get(index);
		} else
			return null;
	}

	public static void removeRoom(Room room) {
		room.delete();
		roomList.remove(room);
	}

	public static void viewRoom() {
		System.out.println("전체 방 보기");
		for (Room r : roomList)
			System.out.println(r.roomName);
		if (roomList == null) {
			System.out.println("존재하는 방이 없습니다.");
		}
	}

	public static void showMenu(PrintWriter out) {
		try {
			out.println(URLEncoder.encode("[방방방]", "UTF-8"));
			out.println(URLEncoder.encode("1.공개방 생성", "UTF-8"));
			out.println(URLEncoder.encode("선택 : ", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void doControl(PrintWriter out) throws IOException {
		String s = "";
		int choice = 0;
		while (true) {
			showMenu(out);
			System.out.println("1");
			while (in != null) {
			s = in.readLine();
			s = URLDecoder.decode(s,"UTF-8");
			System.out.println("2");
			choice =  Integer.parseInt(s);
			}
			switch (choice) {
			case 1:
				createPRoom();
				break;
			case 2:

				break;
			case 3:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.");
				break;
			}
			
		}
	}
}
