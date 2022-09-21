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
	
	//throws FileNotFoundException, IOException - 예외 상황 발생 시 책임을 호출한 쪽으로 전가하기 위해서 선언합니다.
	private static void sendFile(String fileName, DataOutputStream dos) 
			throws FileNotFoundException, IOException{
		
		//해당 경로의 파일에 빨대를 꼽습니다.
		FileInputStream fin = new FileInputStream("C:\\zzz\\"+fileName);
		
		//데이터를 담기 위한 1024*8 바이트 배열의 buffer변수를 선언합니다.
		byte[] buffer = new byte[1024*8];
		
		//인자로 넘겨받은 DataOutputStream에 200 상태값을 전달합니다.
		dos.writeUTF("200");
		
		while(true) {
			//fin에서 buffer에 데이터를 읽어오고, 읽어온 데이터의 양 만큼 count에 반환합니다.
			int count = fin.read(buffer);
			//읽어온 데이터의 양이 -1인 경우 종료합니다.
			if(count == -1) { break; }
			//dos에 buffer에 담긴 데이터를 인덱스0부터 count갯수까지 씁니다.
			dos.write(buffer, 0, count);
			
		}
		//success
		//fin 닫습니다.
		fin.close();
	}

	public static void main(String[] args) throws Exception{
		//ServerSocket ready
		//서버 소켓을 5555포트로 엽니다.
		@Cleanup ServerSocket server = new ServerSocket(5555);
		
		while(true) {
			//이 소켓에 대한 연결을 수신 대기하고 수락합니다. 메서드는 연결될 때까지 대기상태가 됩니다.
			Socket socket = server.accept();
			
			//여러 사용자의 요청사항을 병렬로 처리하기 위하여 쓰레드를 사용합니다.
			new Thread(() -> {
				
				try {
					//1------------------출력합니다.
					System.out.println("1------------------");
					//소켓(클라이언트) 에 DataInputStream 빨대를 꼽습니다.
					DataInputStream din = new DataInputStream(socket.getInputStream());
					//소켓(클라이언트) 에 DataOutputStream 빨대를 꼽습니다.
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					
					//din으로부터 String값을 읽어오고, firstLine에 담아줍니다.
					String firstLine = din.readUTF();
					
					//firstLine에 담긴 값을 출력합니다.
					System.out.println(firstLine);
					
					try {
						//sendFile 함수를 호출하며, firstLine 값과, dos를 전달합니다.
						sendFile(firstLine, dos);
					//FileNotFoundException 예외상황이 발생했다면,
					}catch (FileNotFoundException fn) {
						//dos에 404 상태코드를 씁니다.
						dos.writeUTF("404");
					}
					
					//din을 닫습니다.
					din.close();
					//dos를 닫습니다.
					dos.close();
					//socket를 닫습니다.
					socket.close();
					//END-----------------를 출력합니다.
					System.out.println("END-----------------");
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			//쓰레드 시작합니다.
			}).start();
			
		}//end while
		

	}

}
