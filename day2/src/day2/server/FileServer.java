package day2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import lombok.Cleanup;

public class FileServer {

	// bad code
	public static void main(String[] args) throws Exception {

		//지정된 포트에 바인딩된 서버 소켓을 생성
		ServerSocket server = new ServerSocket(5555);

		//파일 서버 디렉토리 명
		String Data_file_path = "C:\\zzz\\";
		
		//상태값 또는 리턴값 담을 변수
		String state = "";
		
		//디렉토리 설정
		File dir = new File(Data_file_path);

		while (true) {
			//디렉토리의 파일 목록 String배열로 반환, 파일이 없으면 빈 배열로 반환
			String[] nameList = dir.list();

//			for (String str : nameList) System.out.println(str);

			//server.accept() : 이 소켓에 대한 연결을 수신 대기하고 수락. 메소드는 연결될 때 까지 차단.
			@Cleanup Socket client = server.accept();
			@Cleanup OutputStream out = client.getOutputStream();
			@Cleanup DataOutputStream dos = new DataOutputStream(out);

			// nameList배열의 크기가 0인 경우 파일이 없다는 메시지를 변수에 담고 continue
			if (nameList.length == 0) {
				//modified UTF-8 인코딩을 사용하여 기본 출력 스트림에 문자열을 쓴다.
				dos.writeUTF("403");
				//반복문(while)을 다시 처음부터 수행
				continue;
			}

			// nameList배열의 값을 문자열로 변환하여 변수에 담기
			String nameListString = Arrays.toString(nameList);

			// 해당 변수값을 dos.writeUTF로 Client에 전달
			dos.writeUTF(nameListString);

			@Cleanup DataInputStream din = new DataInputStream(client.getInputStream());

			//modified UTF-8 인코딩을 사용하여 문자열을 읽어온다
			String fileName = din.readUTF();
			System.out.println("request filename : " + fileName);

			try {
				//FileInputStream실제 파일에 대한 연결을 열어 파일 시스템의 경로 이름으로 명명된 파일을 만든다. 
				@Cleanup FileInputStream fin = new FileInputStream("C:\\zzz\\" + fileName);

				//modified UTF-8 인코딩을 사용하여 기본 출력 스트림에 문자열 쓰기
				dos.writeUTF("200");
				//데이터를 담을 byte배열의 변수(buffer) 설정
				byte[] buffer = new byte[1024 * 8];

				while (true) {
					//FileInputstream에서 byte배열인 buffer만큼 데이터 읽어옴 
					int count = fin.read(buffer);
					//읽어온 결과가 -1 (읽을 데이터 없음) 인 경우 반복문(while) 종료 
					if (count == -1) { break; }
					//OutputStream에 byte배열인 buffer에 담긴 데이터를 0(시작 인덱스) 부터 count(크기)만큼 쓰기
					out.write(buffer, 0, count);
				}
			} catch (FileNotFoundException e) {
				//modified UTF-8 인코딩을 사용하여 기본 출력 스트림에 문자열 쓰기
				dos.writeUTF("404");
			}
		} // end while

	}
}
