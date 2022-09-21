package day3.ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lombok.Cleanup;

public class FileServer2 {
	
	private static void sendFile(String fileName, DataOutputStream dos)
			throws FileNotFoundException,IOException {
		
		FileInputStream fin = new FileInputStream("C:\\zzz\\"+fileName);
		byte[] buffer = new byte[1024*8];
		
		dos.writeUTF("200");
		
		while(true) {
			int count = fin.read(buffer);
			if(count == -1) { break; }
			dos.write(buffer,0,count);
		}
		
		//success 
		fin.close();
		
		
	}

	public static void main(String[] args)throws Exception {
		
		//ServerSocket ready
		@Cleanup ServerSocket server = new ServerSocket(5555);
		
		while(true) {
			
			Socket socket = server.accept();
			
			new Thread(() -> {
				try {
					DataInputStream din = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					
					String firstLine = din.readUTF();
					
					System.out.println(firstLine);
					try {
						sendFile(firstLine, dos);
					}catch(FileNotFoundException fn) {
						dos.writeUTF("404");
					}
					
					dos.close();
				}catch(Exception e) {
					
				}//end exception
				
			} ).start();
			
			
		}//end while
		
		
	}
}







