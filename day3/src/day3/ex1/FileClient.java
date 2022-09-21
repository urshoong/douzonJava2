package day3.ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

import lombok.Cleanup;

public class FileClient {

	public static void main(String[] args) throws Exception{
		
		//입력받을 변수를 선언해요.
		Scanner keyScanner = new Scanner(System.in);
		
		//파일입력출력해요.
		System.out.println("fileName");
		//입력받아요.
		String oper = keyScanner.nextLine();
		
		//192.168.0.15_무야호 수연
		@Cleanup
		//포트 번호랑 ip번호를 넣어서 소켓을 생성해요.
		Socket socket = new Socket("192.168.0.15", 5555);
		@Cleanup
		//소캣에 있는 inputstream 데이터를 호출하여 datainputstream의 빨대를 꽂아요.
		DataInputStream din = new DataInputStream(socket.getInputStream());
		@Cleanup
		//소캣에 있는 outputstream 데이터를 호출하여 dataoutputstream의 빨대를 꽂아요.
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		//파일이름을 가진 oper를 뿌려줘요.
		dos.writeUTF(oper);
		
		//din을 읽어와서 status에다가 저장해요.
		String status = din.readUTF();
		
		//status값을 출력해요
		System.out.println(status);
		
		//status값이 404이면
		if(status.equals("404")) {
			//해당파일이 없소이다.를 출력해요.
			System.out.println("해당파일이 없소이다.");
			
		 //status값이 200이면
		}else if(status.equals("200")) {
			//oper의 값을 다른용도로 쓸 수 도 있으니 fileName에 임시로 저장해둬요.
			String fileName = oper;
			
			@Cleanup
			//해당경로에 뿌려주기 위해 빨대를 꽂아요.
			FileOutputStream fos = new FileOutputStream("C:\\zzz2\\" + fileName);
			
			//크기가 1024*8인 바이트 타입의 배열인 buffer를 선언해요.
			byte[] buffer = new byte[1024*8];
			

			while(true) {
				//buffer크기만큼 읽어와서 count값에 저장해요.
				int count = din.read(buffer);
				
				//파일을 모두 읽으면 종료해요.
				if(count == -1) { break; }
				
				//buffer의 0번부터 count까지의 값을 적어줘요. 끝이에요. 끄-읕
				fos.write(buffer, 0, count);
			}//end while
			
		}//end if
			
	}
}
