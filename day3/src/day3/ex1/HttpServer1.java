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
		//ServerSocket ��Ʈ 5555�� �����Ͽ� server�� �����Ե�.
		ServerSocket server = new ServerSocket(5555);
		
		while(true) {
			//������ �����Ե�.
			Socket socket = server.accept();
			
			//OutputStream ���� ������
			OutputStream out = socket.getOutputStream();
			//InputStream ���� ������
			InputStream in = socket.getInputStream();
			//��ĳ�� ����
			Scanner scanner = new Scanner(in);
			
			//�Է� ���� �� firstLine ������ ������.
			String firstLine = scanner.nextLine();
			
			System.out.println("-----------------------------------");
			//�Է¹��� �� ��µ�
			System.out.println(firstLine);
			//�����̸��� ������ �������� ������ ������ ù ��° �迭�� fileName�� ����
			//��� : GET /aaa.jpg HTTP/1.1 (���� 0�̰� �������� ������ 1�� ���ϸ�)��
			String fileName = firstLine.split(" ")[1];
			//��� split�ؼ� ������ ���ϸ� ���
			System.out.println(fileName);
			System.out.println("===================================");
			
			//msg�� ���ϸ� ����
			String msg = "<h1>" + fileName + "</h1>";
			
			//HTML Header������ out.write(�Ѹ�)���ܵ�.
			out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
//			out.write(new String("Cache-Control: private\r\n").getBytes());
//			out.write(new String("Content-Length:"+msg.getBytes("UTF-8").length+"\r\n").getBytes());
		
			//����������
			out.write(new String("Content-Type: text/html; charset=UTF-8\r\n\r\n").getBytes());
//			out.write(msg.getBytes(StandardCharsets.UTF_8));
			
			//��ο� FileInputStream�� �ȴ´�. (���� : �б�)��
			FileInputStream fin = new FileInputStream("C:\\zzz"+fileName);
			
			//����Ʈ ũ��1024*8�� ����Ʈ �迭 buffer�� ����
			byte[] buffer = new byte[1024*8];
			
			//byte[] buffer = new byte[157356];
			
			while(true) {
				//buffer�� �ִ� �� ũ�⸦ count�� ����
				int count = fin.read(buffer);
				
				//count �� -1�̵Ǹ� ���� �� ���� �󤼤��̹Ƿ� ��.��.
				if(count == -1) { break; }
				
				//buffer�� 0���� ũ�⸸ŭ �Ѹ���.
				out.write(buffer, 0, count);
			}
			
			//fin�ݱ�
			fin.close();
			//out�ݱ�
			out.close();
			
		}//end while
	}

}
