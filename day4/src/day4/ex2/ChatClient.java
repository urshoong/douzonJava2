package day4.ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	// bad code
	public static void main(String[] args) throws Exception{
		System.out.println("Client Start");
		
		Scanner keyScanner = new Scanner(System.in);
		
		// ¶Ç¸£¸¤...
//		Socket socket = new Socket("192.168.0.140", 5555);
		Socket socket = new Socket("localhost", 5555);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		new Thread(() -> {
			try {
				// read while
				for (int i = 0; i < 100; i++) {
					String partnerMsg = din.readUTF();
					System.out.println(partnerMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		// write while
		while (true) {
			String myMsg = keyScanner.nextLine();
			dos.writeUTF(myMsg);
		}

	}
}
