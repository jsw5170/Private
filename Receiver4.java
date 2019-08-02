import java.io.*;
import java.net.*;

// 서버로 메시지를 전송하는 클래스
public class Receiver4 extends Thread {
	Socket socket;
	BufferedReader in = null;

	// Socket 을 매개변수로 받는 생성자.
	public Receiver4(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (Exception e) {
			System.out.println("예외 : " + e);
		}
	}

	// run()메소드 재정의
	@Override
	public void run() {
		while (in != null) {
			try {
				/*
				 * String s = in.nextLine(); if (s.equals("q") || s.equals("Q")) { break; } else
				 * { System.out.println("Thread Receive : " + s); }
				 */
				System.out.println("Thread Receive : " + in.readLine());
			} catch (java.net.SocketException ne) {
				break;
			} catch (Exception e) {
				System.out.println("예외 : " + e);
			}
		}
		try {
			in.close();
		} catch (Exception e) {
			System.out.println("예외3 : " + e);
		}
	}
}
