package MissionSelenium.PageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ReusableComponents.Abstract;

public class ProductCatalogue extends Abstract{
	
	public WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}

	@FindBy(partialLinkText="Checkout")
	WebElement CheckOutEle;
	
	@FindBy(css="[class='card-title']")
	List<WebElement> ProductNameEle;
	
	@FindBy(css="[class='btn btn-info']")
	List<WebElement> AddButtonEle;
	
	@FindBy(css=".mb-3")
	List<WebElement> FullProductEle;
	
	public String AddProduct(String product1, String product2)
	{
		WaitForElement(CheckOutEle);
		int size = ProductNameEle.size();
		for(int i=0 ; i<size; i++)
		{
			if(product1.equalsIgnoreCase(ProductNameEle.get(i).getText()))
			{
				AddButtonEle.get(i).click();
			}
			else if(product2.equalsIgnoreCase(ProductNameEle.get(i).getText()))
			{
				AddButtonEle.get(i).click();
			}
		}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,600)");
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.HOME).build().perform();
		
		return CheckOutEle.getText();
		
	}
	
	
	public boolean ProductPageLoaded()
	{
		WaitForElement(CheckOutEle);
		return true;
	}
	
	}


