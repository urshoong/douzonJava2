package day1;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import lombok.Cleanup;

public class FileSave {
	//bad code
	public static void main(String[] args) throws Exception{
		URL url = new URL("https://postfiles.pstatic.net/MjAyMjA5MTlfMTI3/MDAxNjYzNTU3MTU2NjMy.P_8bDcB6voIcziP3NvWueU1EBrEDVcaTrwM4ZS9s4Bwg._45nzKsykJbv-b_xL9Rn-kTMMoSl9qOb3gQOLKeY0JIg.JPEG.nuri-jht/%ED%83%91%EA%B1%B4%EB%A7%A4%EB%B2%84%EB%A6%AD.jpg?type=w773");
		
		@Cleanup InputStream in = url.openStream();
		@Cleanup OutputStream out = new FileOutputStream("C:\\zzz\\test.jpg");
		
		while(true) {
			int data = in.read();
			
			if(data == -1) { break; }
			
			out.write(data);
		}
	}
}
