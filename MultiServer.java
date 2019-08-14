import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MultiServer {
	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	Scanner sc = new Scanner(System.in);
	Connection con;
	List<User> userList = new ArrayList<User>();
	List<Room> roomList = new ArrayList<Room>();
	PreparedStatement pstmt;
	String nickName;
	int roomNumber;
	Room room;
	Room waitRoom = new Room();

	// 생성자
	public MultiServer() {
		// 클라이언트의 출력스트림을 저장할 해쉬맵 생성
		clientMap = new HashMap<String, PrintWriter>();
		// 해쉬맵 동기화 설정
		Collections.synchronizedMap(clientMap);
	}

	public void init() {
		try {
			serverSocket = new ServerSocket(9999); // 9999포트로 서버소켓 객채생성.
			System.out.println("서버가 시작되었습니다.");

			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + " : " + socket.getPort());

				Thread msr = new MultiServerT(socket); // 쓰레드 생성.
				msr.start();// 쓰레드 시동.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 접속된 모든 클라이언트들에게 메시지를 전달.
	public void sendAllMsg(String user, String msg) {
		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator it = clientMap.keySet().iterator();
		while (it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				if (user.equals(""))
					it_out.println(URLEncoder.encode(msg, "UTF-8"));
				// it_out.println(msg);
				else
					it_out.println("[" + URLEncoder.encode(user, "UTF-8") + "]" + URLEncoder.encode(msg, "UTF-8"));
				// it_out.println("[" + user + "]" + msg);
			} catch (Exception e) {
				System.out.println("server예외allmsg : " + e);
			}
		}
	}

	public void sendMsg(User user, String msg, PrintWriter out) throws UnsupportedEncodingException {
		Iterator<Room> it = roomList.iterator();
		Room r = new Room();
		while (it.hasNext()) {
			r = it.next();
			if (r.roomNumber == user.rNum)
				break;
		}
		if (user.rNum == 0) {
			r = waitRoom;
		}
		String[] ban = msg.split(" ");
		for (int i = 0; i < ban.length; i++) {
			if (user.banWord.contains(ban[i])) {
				char[] ch = null;
				ch = ban[i].toCharArray();
				for (int j = 0; j < ban[i].length(); j++) {
					ch[j] = '*';
				}
				String str = new String(ch);
				ban[i] = str;
			}
		}
		String msg1 = "";
		for (int i = 0; i < ban.length; i++) {
			msg1 += ban[i] + " ";
		}
		Iterator<User> ru = r.roomUser.iterator();
		while (ru.hasNext()) {
			try {
				String id = ru.next().id;
				PrintWriter it_out = (PrintWriter) clientMap.get(id);
				it_out.println(
						"[" + URLEncoder.encode(user.nickName, "UTF-8") + "]" + URLEncoder.encode(msg1, "UTF-8"));
//				if (ru.next().banUser.isEmpty()) {
//					Iterator<User> bu = ru.next().banUser.iterator();
//					if (!bu.next().banUser.contains(user.nickName)) {
//						it_out.println("[" + URLEncoder.encode(user.nickName, "UTF-8") + "]"
//								+ URLEncoder.encode(msg1, "UTF-8"));
//					} else {
//						it_out.println("[" + URLEncoder.encode(user.nickName, "UTF-8") + "]"
//								+ URLEncoder.encode(msg1, "UTF-8"));
//					}
//				} else {
//					it_out.println(
//							"[" + URLEncoder.encode(user.nickName, "UTF-8") + "]" + URLEncoder.encode(msg1, "UTF-8"));
//				}
			} catch (Exception e) {
				System.out.println("server예외msg : " + e);
				e.printStackTrace();
			}
		}
	}

	public void commandRun(String msg, PrintWriter out, User user, BufferedReader in) {
		try {
			while (true) {
//				String msg = in.readLine();
				User u = new User("", "", 0);
				for (int i = 0; i < userList.size(); i++) {
					u = userList.get(i);
					if (u.id == user.id) {
						u = user;
						break;
					}
				}
				u.userprint();
				String[] msgs = msg.split(" ");
				String command = msgs[0];
				String send = "";
				for (int i = 2; i < msgs.length; i++)
					send += msgs[i];
				String info = "";
				String pw = "";
				System.out.println(command);
				switch (command) {
				case "/exit":
					out.println(URLEncoder.encode("접속을 종료합니다.", "UTF-8"));
					exit(u);
					break;
				case "/to":
					sendOne(msgs[1], send);
					break;
				case "/create":
					out.println(URLEncoder.encode("비밀번호 설정?(Y/N)", "UTF-8"));
					pw = in.readLine();
					createRoom(u, msgs[1], pw, out, in);
					sendMsg(user, "┌─────────────────────────┐", out);
					sendMsg(user, "├   " + msgs[1] + "방에 입장하셨습니다. ┤", out);
					sendMsg(user, "└─────────────────────────┘", out);
					break;
				case "/enter":
					enterRoom(u, msgs[1], out, in);
					sendMsg(user, "┌─────────────────────────────────┐", out);
					sendMsg(user, "├ " + user.nickName + "님이 " + msgs[1] + "방에 입장하셨습니다. ┤", out);
					sendMsg(user, "└─────────────────────────────────┘", out);
					break;
				case "/monitoring":
					monitoring(u, msgs[1]);
					break;
				case "/managerDelete":
					managerDelete(u, msgs[1]);
					break;
				case "/blacklist":
					blackList(u, msgs[1]);
					break;
				case "/banuser":
					banUser(u, msgs[1]);
					break;
				case "/out":
					sendMsg(user, "┌─────────────────────────┐", out);
					sendMsg(user, "│   방에서 나가셨습니다.  │ ", out);
					sendMsg(user, "└─────────────────────────┘", out);
					outRoom(u);
					break;
				case "/invite":
					sendMsg(user, "┌─────────────────────────┐", out);
					sendMsg(user, "├ " + msgs[1] + "님을 초대하셨습니다. ┤", out);
					sendMsg(user, "└─────────────────────────┘", out);
					invite(u, msgs[1], out, in);
					sendMsg(user, "┌─────────────────────────┐", out);
					sendMsg(user, "├ " + msgs[1] + "님이 입장하셨습니다. ┤", out);
					sendMsg(user, "└─────────────────────────┘", out);
					break;
				case "/kickout":
					sendMsg(user, "┌─────────────────────────┐", out);
					sendMsg(user, "├ " + msgs[1] + "님을 강퇴합니다. ┤", out);
					sendMsg(user, "└─────────────────────────┘", out);
					kickOut(u, msgs[1]);
					break;
				case "/changeowner":
					sendMsg(user, "┌─────────────────────────┐", out);
					sendMsg(user, "├ " + msgs[1] + "님으로 방장을 변경합니다. ┤", out);
					sendMsg(user, "└─────────────────────────┘", out);
					changeOwner(u, msgs[1]);
					break;
				case "/roomdelete":
					roomDelete(u);
					sendMsg(user, "┌─────────────────────────┐", out);
					sendMsg(user, "│    방을 폭파했습니다.   │", out);
					sendMsg(user, "└─────────────────────────┘", out);
					break;
				case "/banword":
					sendMsg(user, "┌─────────────────────────┐", out);
					sendMsg(user, "├ " + msgs[1] + "을 금칙어에 추가합니다. ┤", out);
					sendMsg(user, "└─────────────────────────┘", out);
					banWord(u, msgs[1]);
					break;
				case "/list":
					out.println(URLEncoder.encode("┌─────────────────────────────┐", "UTF-8"));
					out.println(URLEncoder.encode("│ 사용자 리스트를 출력합니다. │", "UTF-8"));
					out.println(URLEncoder.encode("└─────────────────────────────┘", "UTF-8"));
					list(out);
					break;
				case "/getRoomInfo":
					out.println(URLEncoder.encode("┌───────────────────────────┐", "UTF-8"));
					out.println(URLEncoder.encode("│ 현재 방정보를 출력합니다. │", "UTF-8"));
					out.println(URLEncoder.encode("└───────────────────────────┘", "UTF-8"));
					info = getRoomInfo();
					out.println(URLEncoder.encode(info, "UTF-8"));
					break;
				case "/orinfo":
					out.println(URLEncoder.encode("┌───────────────────────────────┐", "UTF-8"));
					out.println(URLEncoder.encode("│ 현재 공개방정보를 출력합니다. │", "UTF-8"));
					out.println(URLEncoder.encode("└───────────────────────────────┘", "UTF-8"));
					info = getORoomInfo();
					out.println(URLEncoder.encode(info, "UTF-8"));
					break;
				case "/crinfo":
					out.println(URLEncoder.encode("┌───────────────────────────────┐", "UTF-8"));
					out.println(URLEncoder.encode("│ 현재 비밀방정보를 출력합니다. │", "UTF-8"));
					out.println(URLEncoder.encode("└───────────────────────────────┘", "UTF-8"));
					info = getCRoomInfo();
					out.println(URLEncoder.encode(info, "UTF-8"));
					break;
				case "/getRoomMember":
					out.println(URLEncoder.encode("┌───────────────────────────┐", "UTF-8"));
					out.println(URLEncoder.encode("│ 현재 방인원을 출력합니다. │", "UTF-8"));
					out.println(URLEncoder.encode("└───────────────────────────┘", "UTF-8"));
					info = getRoomMember(u);
					out.println(URLEncoder.encode(info, "UTF-8"));
					break;
				case "/getWaitMember":
					out.println(URLEncoder.encode("┌────────────────────────────────┐", "UTF-8"));
					out.println(URLEncoder.encode("│ 현재 대기방 인원을 출력합니다. │", "UTF-8"));
					out.println(URLEncoder.encode("└────────────────────────────────┘", "UTF-8"));
					info = getWaitMember(u);
					out.println(URLEncoder.encode(info, "UTF-8"));
					break;
				}
				break;
			}
		} catch (Exception e) {
			System.out.println("command예외2 : " + e);
			e.printStackTrace();
		}
	}

	public void exit(User user) {
		userList.remove(user);
		clientMap.remove(user.id);
	}

	public void monitoring(User user, String msg) {
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (r.roomName.equalsIgnoreCase(msg)) {
				if (user.id.equals("master")) {
					user.rNum = r.roomNumber;
					r.roomUser.add(user);
					waitRoom.roomUser.remove(user);
					break;
				}
			}
		}
	}

	public void managerDelete(User user, String msg) {
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (r.roomName.equals(msg)) {
				if (user.id.equals("master")) {
					for (int j = 0; j < userList.size(); j++) {
						User u = userList.get(j);
						u.rNum = 0;
						waitRoom.roomUser.add(u);
					}
					roomList.remove(r);
					return;
				}
			}
		}
	}

	public void blackList(User user, String msg) throws SQLException {
		if (user.id.equals("master")) {
			String sql = "update member set blackList = 1 where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, msg);
			pstmt.executeUpdate();
		}
	}

	public void sendOne(String user, String msg) {
		try {
			PrintWriter it_out = (PrintWriter) clientMap.get(user);
			if (it_out != null)
				it_out.println(user + "(귓속말)>>" + URLEncoder.encode(msg, "UTF-8"));
			else
				it_out.println("존재하지 않는 사용자입니다.");
		} catch (Exception e) {
			System.out.println("예외 : " + e);
		}
	}

	public void list(PrintWriter out) {
		// 출력 스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();
		String msg = "사용자 리스트 [";
		while (it.hasNext()) {
			msg += (String) it.next() + ",";
		}
		msg = msg.substring(0, msg.length() - 1) + "]";
		try {
			out.println(URLEncoder.encode(msg, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void banWord(User user, String ban) {
		user.banWord.add(ban);
	}

	public void banUser(User user, String banuser) {
		Iterator<User> it = userList.iterator();
		while (it.hasNext()) {
			User u = it.next();
			if (u.nickName.equals(banuser)) {
				user.banUser.add(u);
			}
		}
	}

	public void createRoom(User user, String msg, String pw, PrintWriter out, BufferedReader in) throws IOException {
		String check = "";
		int num = 0;
		out.println("┌──────────────────────┐");
		out.println("│ 방 정원을 입력하세요 │");
		out.println("└──────────────────────┘");
		num = Integer.parseInt(in.readLine());
		out.println("┌───────────────────────┐");
		out.println("│ 방 정원은 " + num + "명 입니다. │");
		out.println("└───────────────────────┘");
		while (pw.equals("Y") || pw.equals("y")) {
			if (check.equals(""))
				out.println("┌────────────────────────────┐");
			out.println("│   비밀 번호를 입력하세요   │");
			out.println("└────────────────────────────┘");

			check = in.readLine();
			if (!check.equals(""))
				break;
		}

		roomNumber++;
		room = new Room();
		room.roomName = msg;
		room.roomNumber = roomNumber;
		room.roomOwner = user.nickName;
		room.totalNumber = num;
		room.memberNumber = 1;
		room.passWord = check;
		roomList.add(room);
		room.roomUser.add(user);
		user.rNum = roomNumber;
		waitRoom.roomUser.remove(user);
	}

	public void enterRoom(User user, String msg, PrintWriter out, BufferedReader in) throws IOException {
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (r.roomName.equals(msg) && r.memberNumber < r.totalNumber && user.rNum != r.roomNumber) {
				if (!r.passWord.equals("")) {
					out.println("┌────────────────────────────┐");
					out.println("│   비밀 번호를 입력하세요   │");
					out.println("└────────────────────────────┘");
					String check = in.readLine();
					if (check.equals(r.passWord)) {
						user.rNum = r.roomNumber;
						r.memberNumber++;
						r.roomUser.add(user);
						waitRoom.roomUser.remove(user);
						break;
					} else {
						out.println("┌────────────────────────────┐");
						out.println("│   비밀 번호가 틀렸습니다.  │");
						out.println("└────────────────────────────┘");
						return;
					}
				}
				user.rNum = r.roomNumber;
				r.memberNumber++;
				r.roomUser.add(user);
				waitRoom.roomUser.remove(user);
				break;
			}
//			else if (r.memberNumber == r.totalNumber) {
//				out.println("인원이 가득찼습니다.");
//				return;
//			}
		}
	}

	public void outRoom(User user) {
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (r.roomNumber == user.rNum) {
				Iterator<User> uit = r.roomUser.iterator();
				while (uit.hasNext()) {
					if (uit.next().nickName.equals(user.nickName)) {
						if (r.roomOwner.equals(user.nickName)) {
							r.roomOwner = uit.next().nickName;
						}
					}
				}
				r.roomUser.remove(user);
				r.memberNumber--;
				user.rNum = 0;
				waitRoom.roomUser.add(user);
				if (r.memberNumber == 0) {
					roomList.remove(r);
				}
			}
		}
	}

	public void invite(User user, String msg, PrintWriter out, BufferedReader in) throws IOException {
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (r.roomNumber == user.rNum && r.roomOwner.equals(user.nickName)) {
				for (int j = 0; j < userList.size(); j++) {
					User u = userList.get(j);
					if (u.nickName.equals(msg) && u.rNum != r.roomNumber && r.memberNumber < r.totalNumber) {
						u.rNum = r.roomNumber;
						r.memberNumber++;
						r.roomUser.add(u);
						waitRoom.roomUser.remove(u);
//						for(int k=0;k<roomList.size();k++) {
//							Room nr = roomList.get(k);
//							if(nr.roomNumber == u.rNum) {
//								nr.roomUser.remove(u);
//								if(nr.memberNumber == 0) {
//									roomList.remove(nr);
//								}
//							}
//						}
//						if (answer(u).equals("Y") || answer(u).equals("y")) {
//						}
					} else if (r.memberNumber == r.totalNumber) {
						out.println("┌─────────────────────────┐");
						out.println("│   인원이 가득찼습니다.  │");
						out.println("└─────────────────────────┘");
						return;
					}
				}
			}
		}
	}

	public void kickOut(User user, String msg) {
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (r.roomNumber == user.rNum && r.roomOwner.equals(user.nickName)) {
				for (int j = 0; j < userList.size(); j++) {
					User u = userList.get(j);
					if (u.nickName.equals(msg)) {
						r.memberNumber--;
						u.rNum = 0;
						r.roomUser.remove(u);
						waitRoom.roomUser.add(u);
					}
				}
			}
		}
	}

	public void changeOwner(User user, String msg) {
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (r.roomNumber == user.rNum && r.roomOwner.equals(user.nickName)) {
				for (int j = 0; j < userList.size(); j++) {
					User u = userList.get(j);
					if (u.nickName.equals(msg)) {
						r.roomOwner = u.nickName;
					}
				}
			}
		}
	}

	public void roomDelete(User user) {
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (r.roomNumber == user.rNum && r.roomOwner.equals(user.nickName)) {
				for (int j = 0; j < userList.size(); j++) {
					User u = userList.get(j);
					u.rNum = 0;
					waitRoom.roomUser.add(u);
				}
				roomList.remove(r);
				return;
			}
		}
	}

	public String answer(User user) throws IOException {
		String ans = null;
		PrintWriter it_out = (PrintWriter) clientMap.get(user.id);
		if (it_out != null) {
			it_out.println(user.nickName + "님이 초대 하셨습니다. 수락하시겠습니까? (Y/N)");
			ans = user.getIn();
			it_out.println(ans + " 응");
			return ans;
		} else
			it_out.println("존재하지 않는 사용자입니다.");
		return ans;
	}

	public String getRoomInfo() {
		String s = "";
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			s += "RoomName : " + r.roomName + " RoomNumber : " + r.roomNumber;
			if (it.hasNext())
				s += "\n";
		}
		return s;
	}

	public String getORoomInfo() {
		String s = "";
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (r.passWord.equals("")) {
				s += "RoomName : " + r.roomName + " RoomNumber : " + r.roomNumber;
				if (it.hasNext())
					s += "\n";
			}
		}
		return s;
	}

	public String getCRoomInfo() {
		String s = "";
		Iterator<Room> it = roomList.iterator();
		while (it.hasNext()) {
			Room r = it.next();
			if (!r.passWord.equals("") && r.roomNumber != 0) {
				s += "RoomName : " + r.roomName + " RoomNumber : " + r.roomNumber;
				if (it.hasNext())
					s += "\n";
			}
		}
		return s;
	}

	public String getRoomMember(User user) {
		String s = "";
		for (int i = 0; i < roomList.size(); i++) {
			Room r = roomList.get(i);
			if (r.roomNumber == user.rNum)
				for (int j = 0; j < userList.size(); j++) {
					User u = userList.get(j);
					if (u.rNum == r.roomNumber) {
						s += "닉네임 : " + u.nickName;
						if (i < userList.size() - 1)
							s += "  ";
					}
				}
		}
		return s;
	}

	public String getWaitMember(User user) {
		String s = "";
		for (int i = 0; i < userList.size(); i++) {
			User u = userList.get(i);
			if (u.rNum == 0) {
				s += "닉네임 : " + u.nickName;
				if (i < userList.size() - 1)
					s += "  ";
			}
		}
		return s;
	}

	public static void main(String[] args) {
		// 서버객체 생성 함수,변수들의 static 제거
		MultiServer ms = new MultiServer();
		ms.init();
	}

	// 내부 클래스
	// 클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket) 에 보내는 역할을 하는 메서드

	class MultiServerT extends Thread {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		// 생성자
		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(this.socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
			} catch (Exception e) {
				System.out.println("server예외T : " + e);
			}
		}

		// 쓰레드를 사용하기 위해서 run()메서드 재정의
		@Override
		public void run() {
			String id = "";
			String name = ""; // 클라이언트로부터 받은 이름을 저장할 변수.
			User user = null;
			try {
				while (id.equals("")) {
					id = doRun();
				}
				name = in.readLine();
				name = "";
				while (name.equals("")) {
					out.println(URLEncoder.encode("닉네임을 입력하세요.", "UTF-8"));
					name = in.readLine();
					for (int i = 0; i < userList.size(); i++) {
						User u = userList.get(i);
						if (u.nickName.equals(name)) {
							out.println("이미 있는 닉네임입니다.");
							name = "";
						}
					}
				}
				name = URLDecoder.decode(name, "UTF-8");
				out.println(URLEncoder.encode("닉네임 : " + name, "UTF-8"));
				sendAllMsg("", "┌───────────────────────────────┐");
				sendAllMsg("", "  ├ " + name + "님이 입장하셨습니다." + " ┤");
				sendAllMsg("", "└───────────────────────────────┘");
				user = new User(name, id);
				userList.add(user);
				user.banWord.add("ban");
				Iterator<Room> it = roomList.iterator();
				if (!roomList.contains(waitRoom))
					roomList.add(waitRoom);

				waitRoom.roomUser.add(user);

				// 현재 객체가 가지고 있는 소켓을 제외하고 다른 소켓(클라이언트)들에게 접속을 알림
				clientMap.put(id, out);// 해쉬멥에 키를 name으로 출력스트림 객체를 저장.
				out.println(URLEncoder.encode("┌───────────────────────────────┐", "UTF-8"));
				out.println(URLEncoder.encode("│ 현재 접속자 수는 " + clientMap.size() + "명 입니다.  │", "UTF-8"));
				out.println(URLEncoder.encode("└───────────────────────────────┘", "UTF-8"));
				String s = "";
				while (in != null) {
					s = in.readLine();
					s = URLDecoder.decode(s, "UTF-8");
					System.out.println(s);
					if (s.substring(0, 1).equals("/"))
						commandRun(s, out, user, in);
					else
						sendMsg(user, s, out);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				clientMap.remove(id);
				String uid = user.id;
				userList.remove(user);
				for (int i = 0; i < roomList.size(); i++) {
					Room r = roomList.get(i);
					if (r.roomNumber == user.rNum)
						r.roomUser.remove(user);
				}
				sendAllMsg("", "┌───────────────────────────────┐");
				sendAllMsg("", "  ├ " + name + "님이 퇴장하셨습니다." + " ┤");
				sendAllMsg("", "└───────────────────────────────┘");
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");
				// out.println(URLEncoder.encode(, "UTF-8"));
				String sql = "update member set login = 0 where id = ?";
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, uid);
					pstmt.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					in.close();
					out.close();

					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String doRun() {
		connectDatabase();
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(this.socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
		} catch (Exception e) {
			System.out.println("server예외dorun : " + e);
		}
		String choice = "";
		String id = "";
		String pw = "";
		while (in != null) {
			while (in != null) {
				try {
					choice = in.readLine();
					if (choice.equals("1") || choice.equals("2"))
						break;
				} catch (IOException e1) {
				}
			}
			switch (choice) {
			case "1":
				while (in != null) {
					try {
						id = in.readLine();
						if (!id.equals(""))
							break;
					} catch (IOException e1) {
					}
				}
				while (in != null) {
					try {
						pw = in.readLine();
						if (!pw.equals(""))
							break;
					} catch (IOException e1) {
					}
				}
				signup(id, pw, out);
				break;
			case "2":
				while (in != null) {
					try {
						id = in.readLine();
						if (!id.equals(""))
							break;
					} catch (IOException e1) {
					}
				}
				while (in != null) {
					try {
						pw = in.readLine();
						if (!pw.equals(""))
							break;
					} catch (IOException e1) {
					}
				}
				id = signin(id, pw, out);
				return id;
			case "3":
				out.println("프로그램을 종료합니다.");
				return "";
			default:
				out.println("잘 못 입력하셨습니다.");
				break;
			}
		}
		return "";
	}

	public void showMenu() {
		System.out.println("[메뉴 선택]");
		System.out.println("1.회원가입");
		System.out.println("2.로그인");
		System.out.println("3.종료");
		System.out.print("선택 : ");
	}

	public void signup(String id, String pw, PrintWriter out) {
		String sql = "insert into member values(?, ?, 0, 0)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // 오라클은 1부터 시작
			pstmt.setString(2, pw);
			int updateCount = pstmt.executeUpdate();
			out.println(URLEncoder.encode("회원가입 되었습니다.", "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out.println(URLEncoder.encode("데이터 베이스 입력 에러입니다.", "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
			}
		}
	}

	public String signin(String id, String pw, PrintWriter out) {
		String sql = "select * from member where id = ? and pw = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(3) != 0) {
					out.println(URLEncoder.encode("접속 불가 대상입니다.", "UTF-8"));
					return "";
				}
				if (rs.getInt(4) != 0) {
					out.println("이미 로그인 되어있습니다.");
					return "";
				}
				if (rs.getString(1).equals(id) && rs.getString(2).equals(pw) && rs.getInt(4) == 0) {
					out.println(URLEncoder.encode("로그인 되었습니다.", "UTF-8"));
					sql = "update member set login = 1 where id = ? and pw = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, pw);
					pstmt.executeUpdate();
					return id;
				} else {
					out.println(URLEncoder.encode("회원정보가 일치하지 않습니다.", "UTF-8"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("알 수 없는 에러가 발생했습니다..");
		}
		return "";
	}

	public void connectDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
