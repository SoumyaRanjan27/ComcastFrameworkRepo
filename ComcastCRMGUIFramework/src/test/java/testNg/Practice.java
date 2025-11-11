package testNg;

import java.util.Date;

public class Practice {

	public static void main(String[] args) {
		
		
		String time = new Date().toString().replace(" ","_").replace(":","_");
		System.out.println(time);

	}

}
