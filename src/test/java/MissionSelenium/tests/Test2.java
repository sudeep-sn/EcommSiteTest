package MissionSelenium.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import MissionSelenium.PageObjects.ProductCatalogue;



public class Test2 extends BaseTest{
	
	@Test(groups=("Sanity"))
	public void LoginTestPositive4()
	{
	
		loginPage.LoginApplication("rahulshettyacademy", "learning", "Teacher", "User");
}

	@Test(groups=("Sanity"))
	public void LoginTestPositive5()
	{
		
		loginPage.LoginApplication("rahulshettyacademy", "learning", "Student", "Admin");
}
	@Test(groups=("Sanity"))
	public void LoginTestPositive6()
	{
		
		//Login login = new Login(driver);
		loginPage.LoginApplication("rahulshettyacademy", "learning", "Consultant", "User");
}
	@Test(dataProvider="getData", groups=("Smoke"))
	public void ProdctToCartTest2(HashMap<String,String> input) throws IOException
	{
	
		loginPage.LoginApplication(input.get("username"), input.get("password"), input.get("type"), input.get("radiotype"));
		ProductCatalogue catalogue = new ProductCatalogue(driver);
		String cartCount = catalogue.AddProduct(input.get("product1"),input.get("product2"));
		System.out.println(cartCount);
		Assert.assertTrue(cartCount.contains("Checkout ( 2 )"), "The cart count is not 2");
		
}
	
	
@DataProvider
public Object[][] getData() throws IOException
{
	List<HashMap<String,String>> data = getJsonToMap("H:\\\\New folder (2)\\\\eclipse-workspace\\\\BeExpert\\src\\test\\java\\Data\\data1.json");
	return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};
}

}


