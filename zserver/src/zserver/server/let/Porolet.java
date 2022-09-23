package zserver.server.let;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class Porolet {

	public void init() {
		System.out.println("----------init----------" + this.getClass().getName());
	}
	
	public abstract void service(InputStream in, PoroRes response)throws Exception;
}
