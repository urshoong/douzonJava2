package day2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

	private String title;
	private String author;
	
	public int getPrice(){
		return 100;
	}
	
	{
		System.out.println("Book");
	}
	
}
