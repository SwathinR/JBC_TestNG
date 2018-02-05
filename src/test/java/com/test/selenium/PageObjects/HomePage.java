package com.test.selenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.TestNGException;

public class HomePage{
	
	WebDriver driver=null;
	
	 //Page Objects in our Sample Web Application
    private By EnterWordField = By.xpath("//input[@name='p1']");
    private By CountButton = By.xpath("//input[@type='submit']");
    
    public HomePage(WebDriver driver){
    	this.driver=driver;
    }
    
    public WebElement enterWordField(){
    	try{
    		return driver.findElement(EnterWordField);	
    	}catch(Exception e){
    		e.printStackTrace();
    		throw new TestNGException("Element not found on page!");
    	}
    }
    
    public WebElement CountButton(){
    	try{
    		return driver.findElement(CountButton);	
    	}catch(Exception e){
    		e.printStackTrace();
    		throw new TestNGException("Element not found on page!");
    	}
    }
    
   public ResultPage EnterWord_toCount(String word){
	 enterWordField().sendKeys(word);
	 CountButton().click();
	 return new ResultPage(driver);
   }
}
