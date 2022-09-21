package day1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Restaurant {
	private String name;
	private double lat;
	private double lng;
	private String phone;
}
