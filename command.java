import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class command {
	Connection con;
	String nickName;
	int roomNumber;
	Room room;
	List<User> user;
	List<Room> roomList;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in = null;
	Map<String, PrintWriter> clientMap;

	public command(String nickName, int roomNumber) {
		this.nickName = nickName;
		this.roomNumber = roomNumber;
		try {
			//out = new PrintWriter(this.socket.getOutputStream(), true);
			//in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
		} catch (Exception e) {
			System.out.println("command예외1 : " + e);
			e.printStackTrace();
		}
	}

	public void doRun(String msg,PrintWriter out) {
		try {
			while (true) {
				out.println(URLEncoder.encode("Room", "UTF-8"));
//				String msg = in.readLine();
				String[] msgs = msg.split(" ");
				String command = msgs[0];
				switch (command) {
				case "/crete":
					createRoom(msgs[1]);
					break;
				case "/enter":
					enterRoom(msgs[1]);
					break;
				case "/out":
					outRoom();
					break;
				case "/list":
					list(out);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("command예외2 : " + e);
			e.printStackTrace();
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
	public void createRoom(String msg) {
		roomNumber++;
		room = new Room();
		room.roomName = msg;
		room.roomNumber = 1;
		room.roomOwner = nickName;
		roomList.add(room);
		String sql = "update member set roomNumber = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(4, roomNumber);
			pstmt.setString(1, User.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterRoom(String msg) {
		for (int i = 0; i < roomList.size(); i++) {
			Room r = roomList.get(i);
			if (r.roomName.equals(msg)) {
				room = r;
				room.roomNumber++;
				break;
			}
		}
		String sql = "update member set roomNumber = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(4, roomNumber);
			pstmt.setString(1, User.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void outRoom() {
		room.roomNumber--;
		String sql = "update member set roomNumber = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(4, 0);
			pstmt.setString(1, User.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRoomInfo() {
		String s = "";
		for (int i = 0; i < roomList.size(); i++) {
			Room r = roomList.get(i);
			s += "RoomName" + r.roomName + "MemberNum" + r.roomNumber;
			if (i < roomList.size() - 1)
				s += "\t";
		}
		return s;
	}
	public String getRoomMember() {
		String s = "";
		for(int i=0;i<user.size();i++){
			User users = user.get(i);
			s += users.nickName;
			if(i<roomList.size()-1) s += "\t";
		}
		return s;
	}
	public String getRoomMember(String roomName) {
		String s = "";
		String sql = "select id from phonebook where phoneNumber = 0";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				s += rs.getString(1) + ",";
			}
		}catch(Exception e) {
			System.out.println("알 수 없는 에러가 발생했습니다..");
		}
		return s;
	}
	public String getWaitMember() {
		String s = "";
		String sql = "select id from phonebook where phoneNumber != 0";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				s += rs.getString(1) + ",";
			}
		}catch(Exception e) {
			System.out.println("알 수 없는 에러가 발생했습니다..");
		}
		return s;
	}
}
