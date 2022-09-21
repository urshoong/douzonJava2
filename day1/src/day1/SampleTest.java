package day1;

public class SampleTest {

	public static void main(String[] args) {
		
		Sample obj = Sample.builder()
				.name("Hong")
				.age(10)
				.build();
		//Sample obj = new Sample("HONG");
		
		//obj.setAge(11);
		//obj.setName("AAAA");
		
		System.out.println(obj);

	}

}
