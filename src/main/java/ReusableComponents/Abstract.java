package ReusableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Abstract {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public Abstract(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofMillis(3000));
	}

	public void WaitForElement(By loc)
	{
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(3000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}
	
	public void WaitForElement(WebElement ele)
	{
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(3000));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

}
