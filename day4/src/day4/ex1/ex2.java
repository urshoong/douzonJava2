package day4.ex1;

public class ex2 {
	
	public static void main(String[] args) {
		
		//예전에는 final로 고정해서 사용했어야 했다
//		final StringBuffer str = new StringBuffer();
		StringBuffer str = new StringBuffer();
		
		//Runnable 코드
		new Thread(() -> {
			for(int i = 0; i < 100; i++) {
				System.out.println("A : " + i);
				str.append("" + i);
			}
			System.out.println(str);
		}).start();
		
		for(int i = 0; i < 100; i++) {
			System.out.println("B : " + i);
		}
		
	}
	
}
