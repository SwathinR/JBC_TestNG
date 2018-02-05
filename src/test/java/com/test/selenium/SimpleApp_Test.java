package com.test.selenium;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.test.selenium.PageObjects.HomePage;
import com.test.selenium.PageObjects.ResultPage;

/**
 * End to End test for simple App.
 */ 
public class SimpleApp_Test
{
	//Test Objects & Parameters
	static DesiredCapabilities capabilities = null; 
    static WebDriver driver = null;
    static String Browser = "firefox";
    static String App_URL= "http://rcchn0027:8090/welcome/jsp/welcome.jsp";
    static String TestData = "TestWord!";
    
	@BeforeTest
	public static void SetUpBrowser(){
		if(Browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			System.out.println("Executing test on Chrome browser");
			capabilities= DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(new ChromeOptions());
			driver.manage().window().maximize();
		}else if(Browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/geckodriver.exe");
			System.out.println("Executing test on Firefox browser");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
	}	
	
	public static WebDriver setDriver(){
	return driver;	
	}
	
	@Test (priority=0)
	public static void Test1_PageDisplay(){
		//Opens the Web Application in browser
		driver.get(App_URL);
		//Check presence of Element on the Web page displayed in browser to ensure/verify Web application display
		HomePage homepage = new HomePage(driver);
		WebElement Countbutton = homepage.CountButton();
		Assert.assertTrue(Countbutton.isDisplayed());
	}
	
	@Test (priority=1)
	public static void Test2_VerifyCountMessage(){
		HomePage homepage = new HomePage(driver);
		
		//Enter Input word in the text field on page. 
		ResultPage resultpage = homepage.EnterWord_toCount(TestData);
		
	
		//Specify Expected Result
		String ExpectedTextMessage = "The word "+TestData+" has "+TestData.length()+" characters.";
		//Get the Actual Result
		String ActualTextMessage = 	resultpage.ActualResultTextMessage().getText();
		Assert.assertTrue(ActualTextMessage.equals(ExpectedTextMessage),"Actual Text Message on page Doesn't match Expected Text Message!");
		
		resultpage.goBack().click();
		Assert.assertTrue(homepage.CountButton().isDisplayed());
	}
	
	@Test (priority=2)
	public static void Test3_VerifyCount_Empty_Message(){
		HomePage homepage = new HomePage(driver);
		
		//Enter Input word in the text field on page. 
		homepage.enterWordField().sendKeys("");
		homepage.CountButton().click();
		
		ResultPage resultpage = new ResultPage(driver);
		
		//Specify Expected Result
		String ExpectedTextMessage = "The word has 0 characters.";
		//Get the Actual Result
		String ActualTextMessage = resultpage.ActualResultTextMessage().getText();
		Assert.assertTrue(ActualTextMessage.equals(ExpectedTextMessage),"Actual Text Message on page Doesn't match Expected Text Message!");
		resultpage.goBack().click();
		Assert.assertTrue(homepage.CountButton().isDisplayed());
	}
	
	@AfterTest
	public static void CleanUp(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
