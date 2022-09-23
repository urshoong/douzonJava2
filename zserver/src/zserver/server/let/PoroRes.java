package zserver.server.let;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PoroRes {

	private OutputStream out;
	
	public void setContentType(String type) {
		try {
			out.write(new String("HTTP/1.1 200 OK\r\n").getBytes());
			out.write(new String("Content-Type: "+ type +" \r\n\r\n").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print(String msg) {
		try {
			out.write(msg.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}





