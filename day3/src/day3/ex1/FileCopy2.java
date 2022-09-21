package day3.ex1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy2 {

	public static void main(String[] args) {
		// try with resource : AutoCloseable를 상속받은 것만 들어갈 수 있어효
		// 따로 finally 블록이나 모든 catch 블록에 종료 처리를 하지 않아도 돼효.
		try(
				// 해당 경로에 빠는 빨대를 꼽아효 그리고 fin에 넣어줘효
				InputStream fin = new FileInputStream("C:\\zzz\\test.jpg");
				// 해당 경로에 뱉는 빨대를 꼽아효 그리고 fos에 넣어줘효
				OutputStream fos = new FileOutputStream("C:\\zzz\\copy.jpg");
			) {
			// 실행됬을 때 시간을 long형으로 밀리초(1/1000s)단위로 반환하고 반환한 값을 start에 넣어줘효
			long start = System.currentTimeMillis();
			
			// 1024*8 byte크기 byte배열인 buffer에 만들어줘효 
			byte[] buffer = new byte[1024*8];
			
			//byte[] buffer = new byte[157356];
			
			while(true) {
				// fin에 들어있는 buffer값을 읽고 buffer의 크기를 count에 넣어효
				int count = fin.read(buffer);
				// 만약 읽을 값이 없다면 -1을 반환하기 때문에 count가 -1이면 반환할 값이 없다는 뜻이므로 break해효
				if(count == -1) { break; }
				// fos에 buffer를 인덱스 번호 0번부터 count까지 써효
				fos.write(buffer, 0, count);
			}
			
			// 실행됬을 때 시간을 long형으로 밀리초(1/1000s)단위로 반환하고 반환한 값을 end에 넣어줘효
			long end = System.currentTimeMillis();
			
			// end시간에서 start시간을 빼서 처음 실행한 시간부터 끝날때까지 걸린 시간을 출력해효!!!끝이효
			System.out.println("TIME: " + (end-start));
		}catch(Exception e) {
			
		}
	}

}
