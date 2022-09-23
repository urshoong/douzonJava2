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
		//scanner ���� ����
		Scanner keyScanner = new Scanner(System.in);
		//���� ���� ����
		ServerSocket server = new ServerSocket(5555);
		//Ŭ���̾�Ʈ�� ����� ���� ����
		Socket socket = server.accept();
		//Ŭ���̾� ���ϰ� ����� ���� ����
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		//Ŭ���̾�Ʈ�κ��� �޽����� �ݺ������� �о���� �ҽ�
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
		//Ŭ���̾�Ʈ�κ��� �޽����� �ݺ������� �����ϴ� �ҽ�
		while(true) {
			dos.writeUTF(keyScanner.nextLine());
		}
	}
}
