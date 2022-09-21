package day2;

import java.util.Scanner;

public abstract class AbstractUI {

	private Scanner scanner;
	
	public AbstractUI() {
		scanner = new Scanner(System.in);
	}
	
	public String input(String msg) {
		System.out.println(msg);
		return scanner.nextLine();
	}
	
	public int inputInt(String msg) {
		return Integer.parseInt(input(msg));
	}
	
	public double inputDouble(String msg) {
		return Double.parseDouble(input(msg));
	}
	
	public abstract void oper();
	
}



