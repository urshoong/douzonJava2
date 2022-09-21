package day2;

public class PositionUI extends AbstractUI{

	@Override
	public void oper() {
		
		System.out.println("위치 검색");
		double lat = inputDouble("현재 위도를 입력하세요");
		double lng = inputDouble("현재 경도를 입력하세요");
		
		
	}

	
}
