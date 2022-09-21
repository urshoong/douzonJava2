package day3.ex1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy2 {

	public static void main(String[] args) {
		// try with resource : AutoCloseable�� ��ӹ��� �͸� �� �� �־�ȿ
		// ���� finally ����̳� ��� catch ��Ͽ� ���� ó���� ���� �ʾƵ� ��ȿ.
		try(
				// �ش� ��ο� ���� ���븦 �ž�ȿ �׸��� fin�� �־���ȿ
				InputStream fin = new FileInputStream("C:\\zzz\\test.jpg");
				// �ش� ��ο� ��� ���븦 �ž�ȿ �׸��� fos�� �־���ȿ
				OutputStream fos = new FileOutputStream("C:\\zzz\\copy.jpg");
			) {
			// �������� �� �ð��� long������ �и���(1/1000s)������ ��ȯ�ϰ� ��ȯ�� ���� start�� �־���ȿ
			long start = System.currentTimeMillis();
			
			// 1024*8 byteũ�� byte�迭�� buffer�� �������ȿ 
			byte[] buffer = new byte[1024*8];
			
			//byte[] buffer = new byte[157356];
			
			while(true) {
				// fin�� ����ִ� buffer���� �а� buffer�� ũ�⸦ count�� �־�ȿ
				int count = fin.read(buffer);
				// ���� ���� ���� ���ٸ� -1�� ��ȯ�ϱ� ������ count�� -1�̸� ��ȯ�� ���� ���ٴ� ���̹Ƿ� break��ȿ
				if(count == -1) { break; }
				// fos�� buffer�� �ε��� ��ȣ 0������ count���� ��ȿ
				fos.write(buffer, 0, count);
			}
			
			// �������� �� �ð��� long������ �и���(1/1000s)������ ��ȯ�ϰ� ��ȯ�� ���� end�� �־���ȿ
			long end = System.currentTimeMillis();
			
			// end�ð����� start�ð��� ���� ó�� ������ �ð����� ���������� �ɸ� �ð��� �����ȿ!!!����ȿ
			System.out.println("TIME: " + (end-start));
		}catch(Exception e) {
			
		}
	}

}
