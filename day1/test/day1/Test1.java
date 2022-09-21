package day1;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import day1.service.RestaurantService;
import day1.ui.RestaurantUI;

public class Test1 {
	
	private RestaurantUI ui;
	private RestaurantService rs;
	
	@BeforeEach
	public void ready() {
		System.out.println("ready..");
		ui = RestaurantUI.INSTANCE;
		rs = RestaurantService.INSTANCE;
	}

	@Test
	public void test1() {
		assertNotNull(ui);
	}
	
	@Test
	void test2() {
		ui.searchByKeyword();
	}
	
	@Test
	void test3() {
//		ui.searchByPosition();
		rs.searchByPosition(35.172876, 129.130731).forEach(res -> System.out.println(res));
	}
}
