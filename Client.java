import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

public class Client {
	Map<String, PrintWriter> clientMap;
	Room room;
	Socket socket;
	String name;

	public Client(Map<String, PrintWriter> clientMap) {
		Iterator<String> it = clientMap.keySet().iterator();
		clientMap.get(it);
	}

	public void enterRoom(Room room) {
		room.enterClient(this);
		this.room = room;
	}

	public void exitRoom(Room room) {
		this.room = null;
	}

	public String getClient(Client client) {
		return this.name = client.name;
	}
}
