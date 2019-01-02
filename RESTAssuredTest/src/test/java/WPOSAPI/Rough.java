package WPOSAPI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;

public class Rough {

	public static void main(String[] args) throws IOException {
		Properties prop=new Properties();
		
		
			
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
			prop.load(fis);
			System.out.println(prop.getProperty("HOST"));
			//RestAssured.baseURI= prop.getProperty("HOST");
			//prop.get("HOST");
		

	}

}
