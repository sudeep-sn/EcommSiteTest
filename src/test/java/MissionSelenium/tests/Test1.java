package MissionSelenium.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import MissionSelenium.PageObjects.ProductCatalogue;



public class Test1 extends BaseTest{
	
	@Test(groups=("Smoke"))
	public void LoginTestPositive1()
	{
	
		ProductCatalogue catalogue =loginPage.LoginApplication("rahulshettyacademy", "learning", "Teacher", "User");
}

	@Test(groups=("Regression"))
	public void LoginTestPositive2()
	{
		
		ProductCatalogue catalogue =loginPage.LoginApplication("rahulshettyacademy", "learning", "Student", "Admin");
}
	@Test(groups=("Sanity"))
	public void LoginTestPositive3()
	{
		
		//Login login = new Login(driver);
		ProductCatalogue catalogue = loginPage.LoginApplication("rahulshettyacademy", "learning", "Consultant", "User");
		
		Assert.assertEquals(true, catalogue.ProductPageLoaded());
		
}
	@Test(dataProvider="getData", groups=("Regression"))
	public void ProdctToCartTest(HashMap<String,String> input) throws IOException
	{
	
		ProductCatalogue catalogue =loginPage.LoginApplication(input.get("username"), input.get("password"), input.get("type"), input.get("radiotype"));
		//ProductCatalogue catalogue = new ProductCatalogue(driver);
		String cartCount = catalogue.AddProduct(input.get("product1"),input.get("product2"));
		//System.out.println(cartCount);
		Assert.assertTrue(cartCount.contains("Checkout ( 2 )"), "The cart count is not equal to 2");
		
		
}
	
	
@DataProvider
public Object[][] getData() throws IOException
{
	List<HashMap<String,String>> data = getJsonToMap("H:\\\\New folder (2)\\\\eclipse-workspace\\\\BeExpert\\src\\test\\java\\Data\\data1.json");
	return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};
}

}


