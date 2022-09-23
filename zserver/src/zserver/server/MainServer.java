package zserver.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import zserver.server.let.HelloPorolet;
import zserver.server.let.PoroRes;
import zserver.server.let.Porolet;

public class MainServer {

	public void runServer() {
		
		try {
			ServerSocket server = new ServerSocket(5555);
			System.out.println("server running.................");
			
			Porolet let = new HelloPorolet();
			
			while(true) {
				
				Socket socket = server.accept();
				
				new Thread(() -> {
					try {
						InputStream in = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
					
						PoroRes res = new PoroRes(out);
						
						let.init();
						try {
							let.service(in, res);
						} catch (Exception e) {
							out.write(new String("HTTP/1.1 500 Internel Server Error\r\n").getBytes());
						}
						in.close();
						out.close();
					}catch(Exception e) {
						
					}
				}).start();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}











