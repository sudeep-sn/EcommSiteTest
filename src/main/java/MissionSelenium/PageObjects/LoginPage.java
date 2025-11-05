package MissionSelenium.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ReusableComponents.Abstract;

public class LoginPage extends Abstract{

	WebDriver driver;
	
	@FindBy(id="username")
	WebElement UsernameEle;
	
	@FindBy(id="password")
	WebElement PasswordEle;
	
	@FindBy(css="input[value='user']")
	WebElement UserEle;
	
	@FindBy(xpath="//span[contains(text(),'Admin')]")
	WebElement AdminEle;
	
	@FindBy(css="select[data-style='btn-info']")
	WebElement DropDownEle;
	
	@FindBy(id="terms")
	WebElement TermsEle;
	
	@FindBy(id="signInBtn")
	WebElement SignInEle;
	
	@FindBy(id="okayBtn")
	WebElement ProceedOkEle;
	
	By okPopUp = By.id("okayBtn");
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public ProductCatalogue LoginApplication(String username, String password, String type, String radiotype)
	{
		UsernameEle.sendKeys(username);
		PasswordEle.sendKeys(password);
		if(radiotype.equalsIgnoreCase("admin"))
		{
			AdminEle.click();
		}
		else if(radiotype.equalsIgnoreCase("user"))
		{
			UserEle.click();
			//WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(3000));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(okPopUp));
			WaitForElement(okPopUp);
			ProceedOkEle.click();
		
		}
		Select dropdown = new Select(DropDownEle);
		dropdown.selectByVisibleText(type);
		SignInEle.click();
		ProductCatalogue catalogue = new ProductCatalogue(driver);
		return catalogue;
		
			
	}
	
	public void GoTo()
	{
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
	}

}