package day3.ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

import lombok.Cleanup;

public class FileClient {

	public static void main(String[] args) throws Exception{
		
		//�Է¹��� ������ �����ؿ�.
		Scanner keyScanner = new Scanner(System.in);
		
		//�����Է�����ؿ�.
		System.out.println("fileName");
		//�Է¹޾ƿ�.
		String oper = keyScanner.nextLine();
		
		//192.168.0.15_����ȣ ����
		@Cleanup
		//��Ʈ ��ȣ�� ip��ȣ�� �־ ������ �����ؿ�.
		Socket socket = new Socket("192.168.0.15", 5555);
		@Cleanup
		//��Ĺ�� �ִ� inputstream �����͸� ȣ���Ͽ� datainputstream�� ���븦 �Ⱦƿ�.
		DataInputStream din = new DataInputStream(socket.getInputStream());
		@Cleanup
		//��Ĺ�� �ִ� outputstream �����͸� ȣ���Ͽ� dataoutputstream�� ���븦 �Ⱦƿ�.
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		//�����̸��� ���� oper�� �ѷ����.
		dos.writeUTF(oper);
		
		//din�� �о�ͼ� status���ٰ� �����ؿ�.
		String status = din.readUTF();
		
		//status���� ����ؿ�
		System.out.println(status);
		
		//status���� 404�̸�
		if(status.equals("404")) {
			//�ش������� �����̴�.�� ����ؿ�.
			System.out.println("�ش������� �����̴�.");
			
		 //status���� 200�̸�
		}else if(status.equals("200")) {
			//oper�� ���� �ٸ��뵵�� �� �� �� ������ fileName�� �ӽ÷� �����صֿ�.
			String fileName = oper;
			
			@Cleanup
			//�ش��ο� �ѷ��ֱ� ���� ���븦 �Ⱦƿ�.
			FileOutputStream fos = new FileOutputStream("C:\\zzz2\\" + fileName);
			
			//ũ�Ⱑ 1024*8�� ����Ʈ Ÿ���� �迭�� buffer�� �����ؿ�.
			byte[] buffer = new byte[1024*8];
			

			while(true) {
				//bufferũ�⸸ŭ �о�ͼ� count���� �����ؿ�.
				int count = din.read(buffer);
				
				//������ ��� ������ �����ؿ�.
				if(count == -1) { break; }
				
				//buffer�� 0������ count������ ���� �������. ���̿���. ��-��
				fos.write(buffer, 0, count);
			}//end while
			
		}//end if
			
	}
}
