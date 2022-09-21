package day2.client;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReadClient {
	//bad code
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(8090);

		System.out.println("Listen.................");
		while(true) {
			Socket socket = server.accept();
			
			System.out.println(socket);
			
			InputStream in = socket.getInputStream();
			
			byte[] buffer = new byte[100];
			
			int count = in.read(buffer);
			
			System.out.println("COUNT : " + count);
			
			String clientMsg = new String(buffer, 0, count);
			
			System.out.println(clientMsg);
		}//
	}
}
