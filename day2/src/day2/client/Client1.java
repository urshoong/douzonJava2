package day2.client;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
	//bad code
	public static void main(String[] args) throws Exception{
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("msg");
		String msg = scanner.nextLine();
		
//		Socket socket = new Socket("192.168.30.7", 5555);
		Socket socket = new Socket("192.168.0.140", 5555);
		System.out.println(socket);
		OutputStream out = socket.getOutputStream();
		
		byte[] arr = msg.getBytes();
		
		out.write(arr);
		
		
	}
}
