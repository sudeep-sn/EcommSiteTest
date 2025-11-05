package BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import MissionSelenium.PageObjects.LoginPage;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import org.openqa.selenium.WebDriver; // For the WebDriver interface
import org.openqa.selenium.chrome.ChromeDriver; // For using Chrome browser
import org.openqa.selenium.firefox.FirefoxDriver; // For using Firefox browser
import org.openqa.selenium.WebElement; // For interacting with web elements
import org.openqa.selenium.By; // For locating web elements
import org.openqa.selenium.support.ui.WebDriverWait; // For explicit waits
import org.openqa.selenium.support.ui.ExpectedConditions; // For expected conditions in waits
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.edge.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.openqa.selenium.*;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginPage;
	
	public WebDriver InitializeDriver() throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream("H:\\New folder (2)\\eclipse-workspace\\BeExpert\\src\\test\\java\\Resources\\global.properties");
		prop.load(fis);
		
		
		String browserName = System.getProperty("browser") != null ?
				System.getProperty("browser") : prop.getProperty("browser");
		
		//String browserName =  prop.getProperty("browser");
		System.out.println(browserName);
		String isHeadless = System.getProperty("headless", "false");
		System.out.println(" The headless mode is " + isHeadless);
				
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
		}
		else if(browserName.contains("edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\Users\\hi\\Downloads\\EdgeDriver\\msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			
			if(isHeadless.equals("true"))
			{
			//	options.addArguments("headless");			
			    options.addArguments("--disable-gpu");
			    options.addArguments("--no-sandbox");
			    options.addArguments("--disable-dev-shm-usage");
			    options.addArguments("--window-size=1920,1080");
			    options.addArguments("--headless=new");

			}
			driver = new EdgeDriver(options);
		}
		if(browserName.contains("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "G:\\2021\\JDK Jmeter Testing\\Firefox Driver\\geckodriver.exe");
			//FirefoxOptions options = new FirefoxOptions();
			//if(browserName.contains("headless"))
			//{
			//	options.addArguments("headless");
			//}
			//driver = new FirefoxDriver(options);
			driver = new FirefoxDriver();
		}
		if(!isHeadless.equals("true"))
		{
		driver.manage().window().maximize();
		}
		else
		{
			driver.manage().window().setSize(new Dimension(1920, 1080));
		}
		Dimension size = driver.manage().window().getSize();
		System.out.println("🪟 Browser viewport: " + size);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage LaunchApplication() throws IOException
	{
		driver = InitializeDriver();
		//driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		loginPage = new LoginPage(driver);
		loginPage.GoTo();
		return loginPage;
	}
	
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}
	
	public List<HashMap<String,String>> getJsonToMap(String datafilepath) throws IOException
	{
		@SuppressWarnings("deprecation")
		String jsonContent =FileUtils.readFileToString(new File(datafilepath));
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference <List<HashMap<String,String>>>(){});
		return data;
	}
	
	@AfterMethod(alwaysRun=true)
	public void CloseBrowser()
	{
		driver.close();
	}
	

}
