package com.test.selenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.TestNGException;

public class ResultPage {

	WebDriver driver=null;

	private By ActualResult = By.xpath("/html/body/p");
	private By goBack = By.linkText("back");

	public ResultPage(WebDriver driver){
		this.driver=driver;
	}

	public WebElement ActualResultTextMessage(){
		try{
			return driver.findElement(ActualResult);	
		}catch(Exception e){
			e.printStackTrace();
			throw new TestNGException("Element not found on page!");
		}
	}

	public WebElement goBack(){
		try{
			return driver.findElement(goBack);	
		}catch(Exception e){
			e.printStackTrace();
			throw new TestNGException("Element not found on page!");
		}
	}
}
