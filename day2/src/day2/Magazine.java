package day2;

public class Magazine extends Book{

	public Magazine() {
		super("AAA", "BBB");
		System.out.println("BBBB");
	}
	
	
	
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 1000;
	}




	private int num;
	{
		System.out.println("Magazine");
	}
	
	
}
