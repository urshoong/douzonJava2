package day2.client;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
	//bad code
	public static void main(String[] args) throws Exception{
		Scanner keyScanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("input file name");
			String msg = keyScanner.nextLine();
			
			Socket socket = new Socket("127.0.0.1", 5555);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			out.write(msg.getBytes());
			
			FileInputStream fin = new FileInputStream("C:\\zzz2\\" + msg);
			
			
			
		}//end of while
	}
}
