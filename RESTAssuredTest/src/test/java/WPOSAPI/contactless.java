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

public class contactless extends Base{
	
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
				//body("{\"amount\":100, \"authorisationResult\":{\"authorisationCode\":\""+authCode+"\", \"authorisationID\":\""+authID+"\", \"authorisationResponseCode\":\""+authResponseCode+"\",\"issuerAuthenticationData\":\""+issuerAuthData+"\", \"issuerScriptData\":\""+issuerScrData+"\"}, \"currencyCode\":\"GBP\",\"gratuity\":0, \"parentTransactionId\":\""+ParentTransactionId+"\", \"paymentCard\":{\"applicationPreferredName\":\"ELECTRON DEBIT\",\"captureOrRefundPem\":32, \"cardDetails\":\"476173FFFFFF1141D2012201FFFFFFFFF\", \"cardScheme\":\"Visa\",\"chdEncryptionData\":\"0F59BA3D1DE4C274EBF3413DA55FF1FFC40BD2EAFAE25B68D7A075472CD7D103\", \"chdEncryptionType\":\"MIURA_DUKPT_DATA\",\"chdKsn\":\"FFFF0202799CEC000004\", \"discretionaryData\":\"\", \"emvTerminalType\":\"22\", \"encryptedData\":\"F9070F096606C879\", \"expiryDate\":\"201231\",\"iccData\":{\"appIdData\":\"A0000000032010\", \"appInterchangeProfile\":\"7C00\", \"appTransactionCounter\":\"0404\", \"appUsageControl\":\"FFC0\",\"appVersion\":\"0096\", \"cardholderVerificationMethodResults\":\"410302\", \"cryptogram\":\"F9070F096606C879\", \"cryptogramInformationData\":\"40\",\"cryptogramTransactionType\":\"0\", \"issuerActionCodeDenial\":\"0000000000\", \"issuerActionCodeOnline\":\"0000000000\", \"issuerAppCodeDefault\":\"0000000000\",\"issuerApplicationData\":\"06010A036CA802\", \"panSequenceNumber\":\"01\", \"terminalApplicationVersionNumber\":\"0096\",\"terminalVerificationResults\":\"0080008040\", \"unpredictableNumber\":\"A9735AB5\"}, \"issueNo\":\"0\", \"maskedPAN\":\"476173FFFFFF1141\", \"reasonOnlineCode\":\"05\",\"receiptNumber\":\"3\", \"registeredAppProviderId\":\"A000000003\", \"softwareVersion\":\"0.11.02\", \"specialAuthPem\":5, \"terminalCapabilities\":\"E0B8C8\",\"terminalCountryCode\":\"826\", \"track2\":\"476173FFFFFF1141D2012201FFFFFFFFF\", \"transactionReferenceNumber\":\"1003\", \"transactionSequenceNumber\":\"3\",\"transactionStatusInfo\":\"F800\", \"transactionType\":\"0\"}, \"requestTransactionResult\":1, "+ "\"taxAmount\":0,\"terminalOperatorId\":\"rishi.verma@wpmp-wds-test.com\", \"transactionTimestamp\":\""+capdt+"\", \"type\":\"CapturePriorAuth\"}")
body("{\"amount\":123, \"authorisationResult\":{\"authorisationCode\":\""+authCode+"\", \"authorisationID\":\""+authID+"\", \"authorisationResponseCode\":\""+authResponseCode+"\",\"issuerAuthenticationData\":\""+issuerAuthData+"\"}, \"currencyCode\":\"GBP\", \"gratuity\":0, \"parentTransactionId\":\""+ParentTransactionId+"\",\"paymentCard\":{\"applicationPreferredName\":\"MCC 406 V1 3\", \"captureOrRefundPem\":98, \"cardDetails\":\"541333FFFFFF4062D2512201FFFFFFFFF\",\"cardScheme\":\"MasterCard\", \"chdEncryptionData\":\"F08F9CF838A7552CFE22364CF6505D5FCC9C0DCE8264C9C8E0CAA0ADC478460F\",\"chdEncryptionType\":\"MIURA_DUKPT_DATA\", \"chdKsn\":\"FFFF0202799CEC000006\", \"discretionaryData\":\"\", \"emvTerminalType\":\"22\",\"encryptedData\":\"8A7B7E2BCB3A422A\", \"expiryDate\":\"251231\", \"formFactorContent\":\"9F6E06005601023030\", \"iccData\":{\"appIdData\":\"A0000000041010\",\"appInterchangeProfile\":\"5880\", \"appTransactionCounter\":\"0002\", \"appUsageControl\":\"FFC0\", \"appVersion\":\"0002\",\"cardholderVerificationMethodResults\":\"1F0002\", \"cryptogram\":\"8A7B7E2BCB3A422A\", \"cryptogramInformationData\":\"80\", \"cryptogramTransactionType\":\"0\",\"issuerActionCodeDenial\":\"0000000000\", \"issuerActionCodeOnline\":\"FFFFFFFFFF\", \"issuerAppCodeDefault\":\"FFFFFFFFFF\",\"issuerApplicationData\":\"0110A0000F240000000000000000000000FF\", \"panSequenceNumber\":\"00\", \"terminalApplicationVersionNumber\":\"0002\",\"terminalVerificationResults\":\"8000008000\", \"unpredictableNumber\":\"181601EC\"}, \"issueNo\":\"0\", \"maskedPAN\":\"541333FFFFFF4062\", \"reasonOnlineCode\":\"05\",\"receiptNumber\":\"2\", \"registeredAppProviderId\":\"A000000004\", \"softwareVersion\":\"0.11.02\", \"specialAuthPem\":98, \"terminalCapabilities\":\"E0B8C8\",\"terminalCountryCode\":\"826\", \"track2\":\"541333FFFFFF4062D2512201FFFFFFFFF\", \"transactionReferenceNumber\":\"1002\", \"transactionSequenceNumber\":\"2\",\"transactionStatusInfo\":\"0000\", \"transactionType\":\"0\"}, \"requestTransactionResult\":1, \"taxAmount\":21,\"terminalOperatorId\":\"rishi.verma@wpmp-wds-test.com\", \"transactionTimestamp\":\""+capdt+"\", \"type\":\"CapturePriorAuth\"}").
				when().post("wp-cr-osiris/v1/sales/{id}/transactions").then().assertThat().statusCode(201).extract().response();
		res.print();
		
	}
	

}
