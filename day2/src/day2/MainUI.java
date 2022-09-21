package day2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MainUI extends AbstractUI{

	@Override
	public void oper() {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("C:\\zzz\\ui.properties"));
			System.out.println(prop);
			
			String className = prop.getProperty(input("1번 키워드 2번 위치"));
			
			System.out.println(className);
			
			Class clz = Class.forName(className);
			
			AbstractUI ui = (AbstractUI)clz.getConstructor(null).newInstance(null);
			
			System.out.println(ui);
			
			ui.oper();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}








