package day4.ex1;

//public class Ex1 extends Thread{
public class Ex1 implements Runnable{
	
	@Override
	public void run() {
		doA();
	}

	public void doA() {
		
		for(int i = 0; i < 100; i++) {
			System.out.println(this + " : " + Thread.currentThread().getName() + " : " + i);
		}
		
	}
	
	public static void main(String[] args) {
		
		//implements Runnable 예제에서는 객체 하나만 생성
		Ex1 obj1 = new Ex1();
//		Ex1 obj2 = new Ex1();
//		Ex1 obj3 = new Ex1();
		
//		obj1.doA();
//		obj2.doA();
//		obj3.doA();
		
		
		//extends Thread 예제
		//start -> 출발, CPU를 점유한다
		//race condition
//		obj1.start();
//		obj2.start();
//		obj3.start();
		
		new Thread(obj1).start();
		new Thread(obj1).start();
		new Thread(obj1).start();
		
	}
}
