package day2.server;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import lombok.Cleanup;

public class Server2 {
	//bad code
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(5555);
		
		System.out.println("server is ready..................");
		
		while(true) {
			@Cleanup Socket client = server.accept();
			
//			Scanner inScanner = new Scanner(client.getInputStream());
//			System.out.println(inScanner.nextLine());
			
			@Cleanup DataInputStream din = new DataInputStream(client.getInputStream());
			
			@Cleanup OutputStream out = client.getOutputStream();
			
			String fileName = din.readUTF();//utf인코딩으로 들어오는 데이터 읽어온다
			
			System.out.println(fileName);
			
			@Cleanup FileInputStream fin = new FileInputStream("C:\\zzz\\" + fileName);
			
			byte[] buffer = new byte[1024*8];
			
			while(true) {
				int count = fin.read(buffer);
				if(count == -1) { break; }
				out.write(buffer, 0, count);
			}
			
		}//end of while
	}
}
