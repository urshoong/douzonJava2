package day3.ex1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpServer2 {

	//bad code
	public static void main(String[] args) throws Exception{
		
		//mimeMap.get(ext) 호출을 하여 파일확장자를 선택하기 위해 해쉬맵을 선언 함
		Map<String, String> mimeMap = new HashMap<>();
		mimeMap.put("ico", "image/x-icon");
		mimeMap.put("jpeg", "image/jpeg");
		mimeMap.put("jpg", "image/jpg");
		mimeMap.put("pdf", "application/pdf");
		
		ServerSocket server = new ServerSocket(5555);

		while(true) {
			
			Socket socket = server.accept();
			
			new Thread(()-> {
				try {
					OutputStream out = socket.getOutputStream();
					InputStream in = socket.getInputStream();
					Scanner scanner = new Scanner(in);
					
					String firstLine = scanner.nextLine();
					
					System.out.println("-----------------------");
					System.out.println(firstLine);
					
					String fileName = firstLine.split(" ")[1];
					System.out.println(fileName);
					
					String ext = fileName.substring(fileName.indexOf(".") +1);
					
					System.out.println("==========================");
					
					String msg = "<h1>"+fileName+"</h1>";
					
					out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
					//out.write(new String("Cache-Control: private\r\n").getBytes());
					//out.write(new String("Content-Length:"+msg.getBytes("UTF-8").length+"\r\n").getBytes());
//					String contentType = (mimeMap.get(ext) == null ? "application/octet-stream" : mimeMap.get(ext));
					String contentType = mimeMap.get(ext);
					System.out.println("content Type : " + contentType);
					if(contentType.equals(null)) {
						out.write(new String("Content-Disposition: attachment;").getBytes());
					}else {
						out.write(new String("Content-Type: "+ contentType +" \r\n\r\n").getBytes());
					}
					//out.write(msg.getBytes(StandardCharsets.UTF_8));
					
					FileInputStream fin = new FileInputStream("C:\\zzz"+fileName);
					
					byte[] buffer = new byte[1024*8];
					
					while(true) {
						int count = fin.read(buffer);
						
						if(count == -1) { break; }
						
						out.write(buffer,0,count);
					}
					fin.close();
					
					
					out.close();
				}catch(Exception e) {
					
				}
			}).start();
			
			
		}//end while
		
	}
	
}








