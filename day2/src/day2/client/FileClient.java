package day2.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

import lombok.Cleanup;

public class FileClient {

	// bad code
	public static void main(String[] args) throws Exception {

		Scanner keyScanner = new Scanner(System.in);

		while (true) {
			String state = "";

			@Cleanup Socket socket = new Socket("127.0.0.1", 5555);
			@Cleanup InputStream in = socket.getInputStream();
			@Cleanup DataInputStream din = new DataInputStream(in);

			state = din.readUTF();
			if(state.equals("403"))	System.out.println("directory is empty");
			else {
				String[] arr = state.replaceAll("[\\[\\]]", "").split(",");
				System.out.println("====== file list ======");
				for(int i = 0; i < arr.length; i++) System.out.println((i + 1) + ". " + arr[i].trim());
				System.out.println("=======================");
			}
			
			System.out.println("input file name : ");
			String fileName = keyScanner.nextLine();

			@Cleanup DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			dos.writeUTF(fileName);

			state = din.readUTF();
			System.out.println("server response state : " + state);

			if (state.equals("404")) {
				System.out.println("file is not found");
				continue;
			}else if(state.equals("200")) {
				System.out.println("file downloading");
				@Cleanup FileOutputStream fos = new FileOutputStream("C:\\zzz2\\" + fileName);

				byte[] buffer = new byte[1024 * 8];

				while (true) {
					int count = in.read(buffer);
					if (count == -1) { break; }
					fos.write(buffer, 0, count);
				}
			}
		} // end while
	}
}
