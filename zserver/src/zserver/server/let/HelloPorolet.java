package zserver.server.let;

import java.io.InputStream;

public class HelloPorolet extends Porolet {

	@Override
	public void service(InputStream in, PoroRes response) throws Exception {
		
		response.setContentType("text/html");
		
		Thread.sleep(3000);
		
		response.print("<h1>Hello World</h1>");

	}

}
