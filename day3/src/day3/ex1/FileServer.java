package day3.ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lombok.Cleanup;

public class FileServer {
	
	//throws FileNotFoundException, IOException - ���� ��Ȳ �߻� �� å���� ȣ���� ������ �����ϱ� ���ؼ� �����մϴ�.
	private static void sendFile(String fileName, DataOutputStream dos) 
			throws FileNotFoundException, IOException{
		
		//�ش� ����� ���Ͽ� ���븦 �Ž��ϴ�.
		FileInputStream fin = new FileInputStream("C:\\zzz\\"+fileName);
		
		//�����͸� ��� ���� 1024*8 ����Ʈ �迭�� buffer������ �����մϴ�.
		byte[] buffer = new byte[1024*8];
		
		//���ڷ� �Ѱܹ��� DataOutputStream�� 200 ���°��� �����մϴ�.
		dos.writeUTF("200");
		
		while(true) {
			//fin���� buffer�� �����͸� �о����, �о�� �������� �� ��ŭ count�� ��ȯ�մϴ�.
			int count = fin.read(buffer);
			//�о�� �������� ���� -1�� ��� �����մϴ�.
			if(count == -1) { break; }
			//dos�� buffer�� ��� �����͸� �ε���0���� count�������� ���ϴ�.
			dos.write(buffer, 0, count);
			
		}
		//success
		//fin �ݽ��ϴ�.
		fin.close();
	}

	public static void main(String[] args) throws Exception{
		//ServerSocket ready
		//���� ������ 5555��Ʈ�� ���ϴ�.
		@Cleanup ServerSocket server = new ServerSocket(5555);
		
		while(true) {
			//�� ���Ͽ� ���� ������ ���� ����ϰ� �����մϴ�. �޼���� ����� ������ �����°� �˴ϴ�.
			Socket socket = server.accept();
			
			//���� ������� ��û������ ���ķ� ó���ϱ� ���Ͽ� �����带 ����մϴ�.
			new Thread(() -> {
				
				try {
					//1------------------����մϴ�.
					System.out.println("1------------------");
					//����(Ŭ���̾�Ʈ) �� DataInputStream ���븦 �Ž��ϴ�.
					DataInputStream din = new DataInputStream(socket.getInputStream());
					//����(Ŭ���̾�Ʈ) �� DataOutputStream ���븦 �Ž��ϴ�.
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					
					//din���κ��� String���� �о����, firstLine�� ����ݴϴ�.
					String firstLine = din.readUTF();
					
					//firstLine�� ��� ���� ����մϴ�.
					System.out.println(firstLine);
					
					try {
						//sendFile �Լ��� ȣ���ϸ�, firstLine ����, dos�� �����մϴ�.
						sendFile(firstLine, dos);
					//FileNotFoundException ���ܻ�Ȳ�� �߻��ߴٸ�,
					}catch (FileNotFoundException fn) {
						//dos�� 404 �����ڵ带 ���ϴ�.
						dos.writeUTF("404");
					}
					
					//din�� �ݽ��ϴ�.
					din.close();
					//dos�� �ݽ��ϴ�.
					dos.close();
					//socket�� �ݽ��ϴ�.
					socket.close();
					//END-----------------�� ����մϴ�.
					System.out.println("END-----------------");
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			//������ �����մϴ�.
			}).start();
			
		}//end while
		

	}

}
