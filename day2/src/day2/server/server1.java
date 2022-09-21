package day2.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class server1 {
	public static void main(String[] args) throws Exception{
		Map<String, String> ipMap = new HashMap<>();
		ipMap.put("A", "192.168.0.182");
		ipMap.put("B", "192.168.0.46");
		ipMap.put("C", "192.168.30.7");
		ipMap.put("D", "192.168.30.8");
		ipMap.put("E", "192.168.0.15");
		ipMap.put("F", "192.168.0.140");
		
		ServerSocket server = new ServerSocket(5555);
		
		System.out.println("ready............................");
		
		for(int i = 0; i < 10; i++) {
			Socket socket = server.accept();
			
			System.out.println(socket);
			
			InputStream in = socket.getInputStream();
			
			byte[] buffer = new byte[100];
			
			int count = in.read(buffer);
			
			System.out.println("COUNT : " + count);
			
			String clientMsg = new String(buffer, 0, count);
			
			//A:Hello
			System.out.println(clientMsg);
			
			String[] arr = clientMsg.split(":");
			
			System.out.println(Arrays.toString(arr));
			
			String targetIp = ipMap.get(arr[0]);
			
			Socket targetSocket = new Socket(targetIp, 8080);
			
			OutputStream targetOut = targetSocket.getOutputStream();
			
			targetOut.write(arr[1].getBytes());
			
		}//
	}
}
