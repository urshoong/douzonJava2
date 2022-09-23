package day4.ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer1_test {
	public static void main(String[] args) throws Exception{
		System.out.println("server start");
		//scanner 전역 선언
		Scanner keyScanner = new Scanner(System.in);
		//서버 소켓 생성
		ServerSocket server = new ServerSocket(5555);
		//클라이언트와 연결될 소켓 생성
		Socket socket = server.accept();
		//클라이언 소켓과 연결될 빨대 선언
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		//클라이언트로부터 메시지를 반복적으로 읽어오는 소스
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					System.out.println(din.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		//클라이언트로부터 메시지를 반복적으로 전달하는 소스
		while(true) {
			dos.writeUTF(keyScanner.nextLine());
		}
	}
}
