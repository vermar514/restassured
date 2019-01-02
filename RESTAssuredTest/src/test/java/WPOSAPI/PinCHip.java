 package WPOSAPI;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.testng.annotations.Test;


public class PinCHip extends Base {

	
	
	
	//test = rep.startTest("Chipi & Pin Transaction");
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
	@Test (priority=4)
   public void Auth() 
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
			 body("{\"amount\":100, \"currencyCode\":\"GBP\", \"gratuity\":0, \"paymentCard\":{\"applicationPreferredName\":\"ELECTRON DEBIT\", \"captureOrRefundPem\":0,\"cardDetails\":\"476173FFFFFF1141D2012201FFFFFFFFF\", \"cardScheme\":\"Visa\",\"chdEncryptionData\":\"0F59BA3D1DE4C274EBF3413DA55FF1FFC40BD2EAFAE25B68D7A075472CD7D103\",\"chdEncryptionType\":\"MIURA_DUKPT_DATA\",\"chdKsn\":\"FFFF0202799CEC000004\", \"discretionaryData\":\"\", \"emvTerminalType\":\"22\", \"encryptedData\":\"EFE857D3D0B34E6A\", \"expiryDate\":\"201231\",\"iccData\":{\"appIdData\":\"A0000000032010\", \"appInterchangeProfile\":\"7C00\", \"appTransactionCounter\":\"0404\", \"appUsageControl\":\"FFC0\",\"appVersion\":\"0096\", \"cardholderVerificationMethodResults\":\"410302\", \"cryptogram\":\"EFE857D3D0B34E6A\", \"cryptogramInformationData\":\"80\",\"cryptogramTransactionType\":\"0\", \"issuerActionCodeDenial\":\"0000000000\", \"issuerActionCodeOnline\":\"0000000000\", \"issuerAppCodeDefault\":\"0000000000\",\"issuerApplicationData\":\"06010A03A4A802\", \"panSequenceNumber\":\"01\", \"terminalApplicationVersionNumber\":\"0096\",\"terminalVerificationResults\":\"0080008000\", \"unpredictableNumber\":\"A9735AB5\"}, \"issueNo\":\"0\", \"maskedPAN\":\"476173FFFFFF1141\", \"reasonOnlineCode\":\"10\",\"receiptNumber\":\"3\", \"registeredAppProviderId\":\"A000000003\", \"softwareVersion\":\"0.11.02\", \"specialAuthPem\":5, \"terminalCapabilities\":\"E0B8C8\",\"terminalCountryCode\":\"826\", \"track2\":\"476173FFFFFF1141D2012201FFFFFFFFF\", \"transactionReferenceNumber\":\"1003\", \"transactionSequenceNumber\":\"3\",\"transactionStatusInfo\":\"E800\", \"transactionType\":\"0\"}, \"taxAmount\":0, \"terminalOperatorId\":\"rishi.verma@wpmp-wds-test.com\", \"type\":\"Authorisation\"}").
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
	
	@Test (priority=5)
	public void capture() {
		
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
				body("{\"amount\":100, \"authorisationResult\":{\"authorisationCode\":\""+authCode+"\", \"authorisationID\":\""+authID+"\", \"authorisationResponseCode\":\""+authResponseCode+"\",\"issuerAuthenticationData\":\""+issuerAuthData+"\", \"issuerScriptData\":\""+issuerScrData+"\"}, \"currencyCode\":\"GBP\",\"gratuity\":0, \"parentTransactionId\":\""+ParentTransactionId+"\", \"paymentCard\":{\"applicationPreferredName\":\"ELECTRON DEBIT\",\"captureOrRefundPem\":32, \"cardDetails\":\"476173FFFFFF1141D2012201FFFFFFFFF\", \"cardScheme\":\"Visa\",\"chdEncryptionData\":\"0F59BA3D1DE4C274EBF3413DA55FF1FFC40BD2EAFAE25B68D7A075472CD7D103\", \"chdEncryptionType\":\"MIURA_DUKPT_DATA\",\"chdKsn\":\"FFFF0202799CEC000004\", \"discretionaryData\":\"\", \"emvTerminalType\":\"22\", \"encryptedData\":\"F9070F096606C879\", \"expiryDate\":\"201231\",\"iccData\":{\"appIdData\":\"A0000000032010\", \"appInterchangeProfile\":\"7C00\", \"appTransactionCounter\":\"0404\", \"appUsageControl\":\"FFC0\",\"appVersion\":\"0096\", \"cardholderVerificationMethodResults\":\"410302\", \"cryptogram\":\"F9070F096606C879\", \"cryptogramInformationData\":\"40\",\"cryptogramTransactionType\":\"0\", \"issuerActionCodeDenial\":\"0000000000\", \"issuerActionCodeOnline\":\"0000000000\", \"issuerAppCodeDefault\":\"0000000000\",\"issuerApplicationData\":\"06010A036CA802\", \"panSequenceNumber\":\"01\", \"terminalApplicationVersionNumber\":\"0096\",\"terminalVerificationResults\":\"0080008040\", \"unpredictableNumber\":\"A9735AB5\"}, \"issueNo\":\"0\", \"maskedPAN\":\"476173FFFFFF1141\", \"reasonOnlineCode\":\"05\",\"receiptNumber\":\"3\", \"registeredAppProviderId\":\"A000000003\", \"softwareVersion\":\"0.11.02\", \"specialAuthPem\":5, \"terminalCapabilities\":\"E0B8C8\",\"terminalCountryCode\":\"826\", \"track2\":\"476173FFFFFF1141D2012201FFFFFFFFF\", \"transactionReferenceNumber\":\"1003\", \"transactionSequenceNumber\":\"3\",\"transactionStatusInfo\":\"F800\", \"transactionType\":\"0\"}, \"requestTransactionResult\":1, "+ "\"taxAmount\":0,\"terminalOperatorId\":\"rishi.verma@wpmp-wds-test.com\", \"transactionTimestamp\":\""+capdt+"\", \"type\":\"CapturePriorAuth\"}")
				.when().post("wp-cr-osiris/v1/sales/{id}/transactions").then().assertThat().statusCode(201).extract().response();
		res.print();
		
	}
	
	
}
