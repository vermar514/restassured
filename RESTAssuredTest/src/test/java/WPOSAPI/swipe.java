package WPOSAPI;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class swipe extends Base {
	@Test (priority=0)
	public void Login() {
		Base.loginApi();
		
	}
	@Test (priority=1)
public void Merchant() throws InterruptedException {
		Base.getMerchantDetails();
	}
	@Test (priority=2)
	public void Date() throws ParseException {
			//Base.date();
		
		TimeZone tz = TimeZone.getTimeZone("UTC");
	       DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"); // Quoted "Z" to indicate UTC, no timezone offset
	      dt=df.format(new Date()) ;
	       System.out.println(dt);
	       
		}
	
	@Test (priority=3)
	public void Sale() {
			
			Base.createSale();
			
		}
	
	@Test(priority=4)
	 public void SwipeAuth() 
		{
		Response res=given().pathParam("id", id).
				header("Content-Type", "application/json").
				header("X-WP-MerchantId", MerchantID).
				header("merchantId", MerchantID).
				header("X-Auth-Token", authToken).
				header("X-WP-Auth-Token", authToken).
				header("eMerchantId", eMerchantId).
				header("terminalId", terminalId).
				header("X-WP-TerminalID", terminalId).
				header("X-WP-EMerchantId", eMerchantId).log().all().
                // body("{\"amount\":123, \"currencyCode\":\"GBP\", \"gratuity\":0, \"paymentCard\":{\"applicationPreferredName\":\"MCC 406 V1 3\", \"captureOrRefundPem\":0,\"cardDetails\":\"541333FFFFFF4062D2512201FFFFFFFFF\", \"cardScheme\":\"MasterCard\",\"chdEncryptionData\":\"F08F9CF838A7552CFE22364CF6505D5FCC9C0DCE8264C9C8E0CAA0ADC478460F\", \"chdEncryptionType\":\"MIURA_DUKPT_DATA\",\"chdKsn\":\"FFFF0202799CEC000006\", \"discretionaryData\":\"\", \"emvTerminalType\":\"22\", \"encryptedData\":\"8A7B7E2BCB3A422A\", \"expiryDate\":\"251231\",\"formFactorContent\":\"9F6E06005601023030\", \"iccData\":{\"appIdData\":\"A0000000041010\", \"appInterchangeProfile\":\"5880\", \"appTransactionCounter\":\"0002\",\"appUsageControl\":\"FFC0\", \"appVersion\":\"0002\", \"cardholderVerificationMethodResults\":\"1F0002\", \"cryptogram\":\"8A7B7E2BCB3A422A\",\"cryptogramInformationData\":\"80\", \"cryptogramTransactionType\":\"0\", \"issuerActionCodeDenial\":\"0000000000\", \"issuerActionCodeOnline\":\"FFFFFFFFFF\",\"issuerAppCodeDefault\":\"FFFFFFFFFF\", \"issuerApplicationData\":\"0110A0000F240000000000000000000000FF\", \"panSequenceNumber\":\"00\",\"terminalApplicationVersionNumber\":\"0002\", \"terminalVerificationResults\":\"8000008000\", \"unpredictableNumber\":\"181601EC\"}, \"issueNo\":\"0\",\"maskedPAN\":\"541333FFFFFF4062\", \"reasonOnlineCode\":\"05\", \"receiptNumber\":\"2\", \"registeredAppProviderId\":\"A000000004\", \"softwareVersion\":\"0.11.02\",\"specialAuthPem\":98, \"terminalCapabilities\":\"E0B8C8\", \"terminalCountryCode\":\"826\", \"track2\":\"541333FFFFFF4062D2512201FFFFFFFFF\",\"transactionReferenceNumber\":\"1002\", \"transactionSequenceNumber\":\"2\", \"transactionStatusInfo\":\"0000\", \"transactionType\":\"0\"}, \"taxAmount\":21,\"terminalOperatorId\":\"rishi.verma@wpmp-wds-test.com\", \"type\":\"Authorisation\"}").
				body("\"amount\":123, \"currencyCode\":\"GBP\", \"gratuity\":0, \"paymentCard\":{\"applicationPreferredName\":\"Maestro\", \"captureOrRefundPem\":0,\"cardDetails\":\"3B3637393938352A2A2A2A2A2A303034303D323031323230312A2A2A2A2A2A2A2A2A3F2A\", \"cardScheme\":\"Maestro\",\"chdEncryptionData\":\"AE14F74D2165BE9AEA83EA70995EF368B79AE77DAB8A77D3115EE5A60D367CA14505284001BC6D6D\", \"chdEncryptionType\":\"MIURA_DUKPT_DATA\",\"chdKsn\":\"FFFF0202856EECE00004\", \"discretionaryData\":\"\", \"emvTerminalType\":\"22\", \"encryptedData\":\"\", \"expiryDate\":\"2012\", \"issueNo\":\"0\",\"maskedPAN\":\"679985******0040\", \"reasonOnlineCode\":\"04\", \"receiptNumber\":\"2\", \"registeredAppProviderId\":\"\", \"softwareVersion\":\"0.11.02\",\"specialAuthPem\":2, \"terminalCapabilities\":\"E0B8C8\", \"terminalCountryCode\":\"826\",\"track2\":\"3B3637393938352A2A2A2A2A2A303034303D323031323230312A2A2A2A2A2A2A2A2A3F2A\", \"transactionReferenceNumber\":\"1002\",\"transactionSequenceNumber\":\"2\", \"transactionStatusInfo\":\"0000\", \"transactionType\":\"0\"}, \"taxAmount\":21,\"terminalOperatorId\":\"rishi.verma@wpmp-wds-test.com\", \"type\":\"Authorisation\"").
				when().
			 post("wp-cr-osiris/v1/sales/{id}/transactions").
			 then().assertThat().statusCode(201).extract().response();
				res.print();
				responseString=res.asString();
				jsonPath = new JsonPath(responseString);
				ParentTransactionId = jsonPath.getString("transactionId");
				System.out.println(ParentTransactionId);
				
				authCode = jsonPath.getString("authorisationResult.authorisationCode");
				System.out.println(authCode);
				authID = jsonPath.getString("authorisationResult.authorisationID");
				System.out.println(authID);
				authResponseCode = jsonPath.getString("authorisationResult.authorisationResponseCode");
				System.out.println(authResponseCode);
				issuerAuthData = jsonPath.getString("authorisationResult.issuerAuthenticationData");
				System.out.println(issuerAuthData);
				issuerScrData = jsonPath.getString("authorisationResult.issuerScriptData");
				System.out.println(issuerScrData);
				capdt = jsonPath.getString("transactionTimestamp");
				System.out.println(capdt);
		 
		 
					}

}
