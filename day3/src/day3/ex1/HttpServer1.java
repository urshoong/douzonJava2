package day3.ex1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HttpServer1 {
	
	//bad code
	public static void main(String[] args) throws Exception {
		//ServerSocket 포트 5555로 설정하여 server로 선언함둥.
		ServerSocket server = new ServerSocket(5555);
		
		while(true) {
			//서버를 연결함둥.
			Socket socket = server.accept();
			
			//OutputStream 빨대 꽂음둥
			OutputStream out = socket.getOutputStream();
			//InputStream 빨대 꽂음둥
			InputStream in = socket.getInputStream();
			//스캐너 선언
			Scanner scanner = new Scanner(in);
			
			//입력 받은 값 firstLine 변수로 설정둥.
			String firstLine = scanner.nextLine();
			
			System.out.println("-----------------------------------");
			//입력받은 값 출력둥
			System.out.println(firstLine);
			//파일이름의 공백을 기준으로 나누어 저장한 첫 번째 배열을 fileName에 저장
			//결과 : GET /aaa.jpg HTTP/1.1 (겟이 0이고 공백으로 나눠서 1이 파일명)둥
			String fileName = firstLine.split(" ")[1];
			//방금 split해서 저장한 파일명 출력
			System.out.println(fileName);
			System.out.println("===================================");
			
			//msg에 파일명 저장
			String msg = "<h1>" + fileName + "</h1>";
			
			//HTML Header정보를 out.write(뿌림)해줌둥.
			out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
//			out.write(new String("Cache-Control: private\r\n").getBytes());
//			out.write(new String("Content-Length:"+msg.getBytes("UTF-8").length+"\r\n").getBytes());
		
			//실제데이터
			out.write(new String("Content-Type: text/html; charset=UTF-8\r\n\r\n").getBytes());
//			out.write(msg.getBytes(StandardCharsets.UTF_8));
			
			//경로에 FileInputStream을 꽂는다. (목적 : 읽기)둥
			FileInputStream fin = new FileInputStream("C:\\zzz"+fileName);
			
			//바이트 크기1024*8를 바이트 배열 buffer에 저장
			byte[] buffer = new byte[1024*8];
			
			//byte[] buffer = new byte[157356];
			
			while(true) {
				//buffer에 있는 값 크기를 count로 저장
				int count = fin.read(buffer);
				
				//count 가 -1이되면 값을 다 읽은 상ㅌㅐ이므로 끝.둥.
				if(count == -1) { break; }
				
				//buffer를 0부터 크기만큼 뿌림둥.
				out.write(buffer, 0, count);
			}
			
			//fin닫기
			fin.close();
			//out닫기
			out.close();
			
		}//end while
	}

}
