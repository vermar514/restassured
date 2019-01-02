package WPOSAPI;

import static io.restassured.RestAssured.given;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import util.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Base {
	
	
	public  static String MerchantID ;
	public static String authToken ; 
	public static String secret ;
	public static String eMerchantId ;
	public static String terminalId ;
	public static String responseString ;
	public  static String id ;
	public  static String authCode;
	public  static String authID ;
	public  static String authResponseCode ;
	public  static String issuerAuthData ; 
	public  static String ParentTransactionId;
	public  static String issuerScrData ;
	public  static String capdt ;
	public static JsonPath jsonPath ;
	public static String body ;
	public static String dt;
	public static String username ;
	public final static String IDENTITY_PATH = "/identity/v1/accessTokens";
    public final static String MERCHANT_PATH = "/identity/v1/merchants";
    public final static String CATALOGUE_PATH ="/wp-cr/catalogue/v1/catalogue" ;
    public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

public static void date() throws ParseException {
	test.log(LogStatus.INFO, "Date Printing");
	// TODO Auto-generated method stub
       TimeZone tz = TimeZone.getTimeZone("UTC");
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"); // Quoted "Z" to indicate UTC, no timezone offset
       //2018-12-07T07:28:47.750Z
         //2018-12-07T08:19:20:008Z
//         2018-12-07T08:17:10Z
       dt=df.format(new Date()) ;
       System.out.println(dt);
       System.out.println(new Date());
       //System.out.println(df.format(new Date()));
      // String[] parts = df.format(new Date()).split("T");
       //String nowAsISO = parts[0];
      // System.out.println(nowAsISO);
   }

public  static void loginApi()
{
		// TODO Auto-generated method stub

		//BaseURL or Host
		System.out.println();
		RestAssured.baseURI="https://businesshub3.worldpaytd.com";
		//RestAssured.baseURI= prop.getProperty("HOST");
		Response res=given().
		header("Content-Type", "application/json").
		body("{ \"userId\":\"rishi.verma@wpmp-wds-test.com\",\"password\": \"Password1!\"}").
		       when().
		       post(IDENTITY_PATH).
		       then().assertThat().statusCode(200).extract().response() ;
		
		
		 responseString=res.asString();
		System.out.println(responseString);
		       
		
		 jsonPath = new JsonPath(responseString);
		 MerchantID = jsonPath.getString("merchantId") ;
		System.out.println(MerchantID);
		
		 authToken = jsonPath.getString("authToken") ;
		System.out.println(authToken);
		 secret = jsonPath.getString("secret") ;
		System.out.println(secret);
}

public static void getMerchantDetails() throws InterruptedException 
{
	Response res=given().
			header("Content-Type", "application/json").
			header("X-WP-MerchantID", MerchantID).
			header("merchantID", MerchantID).
			header("X-Auth-Token", authToken).
			header("X-WP-Auth-Token", authToken).log()
			.all().
			
			       when().
			       get(MERCHANT_PATH).
			       then().assertThat().statusCode(200).extract().response() ;
	responseString=res.asString();
	System.out.println("getmerchantdetail"+responseString);
	
	Thread.sleep(5000);
	jsonPath = new JsonPath(responseString);
	eMerchantId = jsonPath.getString("eMerchantId") ;
	System.out.println("emerchant id is "+eMerchantId);
	
	terminalId = jsonPath.getString("terminalId") ;
	System.out.println("terminal is is "+terminalId);
	
	
}

public static void createSale() 
{
	
	
	System.out.println("Date "+PinCHip.dt);
	
	System.out.println(authToken);
	Response res=given().
			header("Content-Type", "application/json").
			header("X-WP-MerchantId", MerchantID).
			header("merchantId", MerchantID).
			header("X-Auth-Token", authToken).
			header("X-WP-Auth-Token", authToken).
			header("eMerchantId", eMerchantId).
			header("terminalId", terminalId).
			header("X-WP-TerminalID", terminalId).
			header("X-WP-EMerchantId", eMerchantId).log().all().
			//body("{\"basket\":[{\"isEatIn\":false, \"currencyCode\":\"GBP\", \"grossAmount\":100, \"linePrice\":100, \"lineTax\":0, \"name\":\"rishi\",\"productReference\":{\"productId\":\"569c4159-4ca4-4fd8-9e14-77864305e3a1\", \"variantId\":1}, \"quantity\":1, \"taxAmount\":0, \"taxBasisPoints\":0,\"unitPrice\":100}], \"createdDateTime\":\""+dt+"\", \"terminalOperatorId\":\"rishi.verma@wpmp-wds-test.com\", \"type\":\"SALE\"}").
			body("{\"basket\":[{\"isEatIn\":false, \"currencyCode\":\"GBP\", \"grossAmount\":123, \"linePrice\":123, \"lineTax\":21, \"name\":\"Item 1\",\"productReference\":{\"productId\":\"1dbf3fb2-3703-4e62-b74f-77f114717256\", \"variantId\":1}, \"quantity\":1, \"taxAmount\":21, \"taxBasisPoints\":2000,\"unitPrice\":123}], \"createdDateTime\":\""+dt+"\", \"terminalOperatorId\":\"rishi.verma@wpmp-wds-test.com\", \"type\":\"SALE\"}").      
			when().
			       post("wp-cr-osiris/v1/sales").then().assertThat().statusCode(201).extract().response() ;
	
	
	responseString=res.asString();
	System.out.println(responseString);
	jsonPath = new JsonPath(responseString);
	id = jsonPath.getString("id") ;
	System.out.println("Sale id is  "+id);
	
	
	
}

}
