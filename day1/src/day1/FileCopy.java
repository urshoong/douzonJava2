package day1;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import lombok.Cleanup;

public class FileCopy {
	public static void main(String[] args) throws Exception{
		@Cleanup FileInputStream fin = new FileInputStream("C:\\zzz\\origin.jpg");
		@Cleanup FileOutputStream fos = new FileOutputStream("C:\\zzz\\copy.jpg");
		
		while(true) {
			int data = fin.read();
			if(data == -1) { break; }
			fos.write(data);
		}
	}
}
