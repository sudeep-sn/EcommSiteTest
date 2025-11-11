package ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.*;
import com.aventstack.extentreports.reporter.configuration.ResourceCDN;

public class ExtentReportsDemo {
	public static ExtentReports getReportObject()
	{
		String reportDir = System.getProperty("reportDir", "target");
		String reportPath = System.getProperty("user.dir")+ "/" + reportDir + "/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Ecommerce Automation");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sudeep");
		extent.setSystemInfo("Build Number", System.getenv("BUILD_NUMBER"));
		extent.setSystemInfo("Job Name", System.getenv("JOB_NAME"));
		return extent;
	 
	}
}
