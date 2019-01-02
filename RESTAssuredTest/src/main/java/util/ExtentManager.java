package util;
import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentManager {
	private static ExtentReports extent;
	public static ExtentTest test;
	
	private ExtentManager(){}

	public static ExtentReports getInstance() {
		if (extent == null) {
			Date d= new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports(System.getProperty("user.dir")+fileName, true, DisplayOrder.NEWEST_FIRST);

			// optional
		//	extent.config().documentTitle("Automation Report")
		//			.reportName("Regression").reportHeadline("");
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Rest Assured", "3.0.2").addSystemInfo(
					"Environment", "FTE03");
		}
		return extent;
	}
}
