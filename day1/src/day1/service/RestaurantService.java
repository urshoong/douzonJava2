package day1.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import day1.dto.Restaurant;

public enum RestaurantService{
	
	INSTANCE;
	
	private List<Restaurant> stores;
	
	RestaurantService(){
		stores = new ArrayList();
		stores.add(new Restaurant("상국이네", 35.1619919, 129.1629534, "051-742-9001"));
		stores.add(new Restaurant("상국이너분식", 35.1687032, 129.1297466, ""));
		stores.add(new Restaurant("해운대분식", 35.1602037, 129.1665972, "051-731-0030"));
		stores.add(new Restaurant("청년다방 부산센텀점", 35.1751114, 129.1248787, "051-784-7161"));
		stores.add(new Restaurant("미스공 구슬떡볶이", 35.1606727, 129.161601, "051-746-1082"));
		stores.add(new Restaurant("고봉민김밥 센텀점", 35.1729505, 129.1315892, "051-701-6667"));
		stores.add(new Restaurant("해운대명물튀김", 35.16215, 129.1627263, "051-743-3580"));
		stores.add(new Restaurant("신전떡볶이", 35.1788264, 129.1248781, "051-731-1629"));
//		stores.add(new Restaurant("", 0, 0, ""));
	}
	
	
	
	public List<Restaurant> searchByName(String keyword) {
//		return stores.stream().filter(res -> res.getName().indexOf(keyword) >= 0).toList();
		if(keyword.equals("")) return null;
		return stores.stream().filter(res -> res.getName().indexOf(keyword) >= 0).collect(Collectors.toList());
	}
	
	public List<Restaurant> searchByPosition(double lat, double lng){
		System.out.println("내 좌표 lat : " + lat + ", lng : " + lng);
		for(Restaurant r : stores) System.out.println("distance : " + Math.round( getDistance(lat, lng, r.getLat(), r.getLng()) ) + "(m)\t\t" + r);
		
		return stores.stream().sorted((p1, p2) -> Double.valueOf(getDistance(lat, lng, p1.getLat(), p1.getLng())).compareTo(getDistance(lat, lng, p2.getLat(), p2.getLng())) ).collect(Collectors.toList());
	}
	
	public double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lng2 - lng1);
	
		double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(lat1))* Math.cos(Math.toRadians(lat2))* Math.sin(dLon/2)* Math.sin(dLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = 6371 * c * 1000;    // Distance in m
		return d;
	}
}