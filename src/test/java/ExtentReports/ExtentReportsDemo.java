package ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.*;
import com.aventstack.extentreports.reporter.configuration.ResourceCDN;

public class ExtentReportsDemo {
	public static ExtentReports getReportObject()
	{
		String reportPath = System.getProperty("user.dir")+"//reports//"+"index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Ecommerce Automation");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sudeep");
		return extent;
	 
	}
}
