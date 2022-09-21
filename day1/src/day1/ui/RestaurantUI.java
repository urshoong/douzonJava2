package day1.ui;

import java.util.Scanner;

import day1.service.RestaurantService;

public enum RestaurantUI {
	INSTANCE;
	
	private Scanner scanner;
	private RestaurantService service;
	
	private RestaurantUI() {
		this.scanner = new Scanner(System.in);
		this.service = RestaurantService.INSTANCE;
	}
	
	public void searchByKeyword() {
		System.out.println("INPUT KEYWORD OR INPUT EXIT");
		String keyword = this.scanner.nextLine();
		
		System.out.println(keyword);
		
		if(keyword.equals("EXIT")) {
			return;
		}
		
		service.searchByName(keyword).forEach(res -> System.out.println(res));
		
		searchByKeyword();
	}
	
	public void searchByPosition() {
		System.out.print("INPUT LATITUDE : ");
		double latitude = this.scanner.nextDouble();
		System.out.print("INPUT LONGITUDE : ");
		double longitude = this.scanner.nextDouble();
		
		service.searchByPosition(latitude, longitude).forEach(res -> System.out.println(res));
	}
}