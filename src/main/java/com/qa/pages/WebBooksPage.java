package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.AppBaseTest;

import com.qa.WebBaseTest;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WebBooksPage extends WebBaseTest{
	
	static Logger log = LogManager.getLogger(WebBooksPage.class.getName());
	
	@FindBy(xpath="//*[@id=\"a-page\"]/div[1]/div/div/div[2]/div/h1/b")  WebElement booksTitle;
	@FindBy(id="nav-ftr-gototop")  WebElement topofPageElement;
	
	
	
	public String checkBooksTitle() {
		
		log.info("Checking Books title is present or not");
		return booksTitle.getText();
	}
	
	public WebElement BottomOfPageElement() {
		
		log.info("Scroll to the bottom of page");
		return topofPageElement;
	
	}
	
public boolean scrollToBottomOfPageElement() {
		
		log.info("Scroll to the bottom of page");
		return isDisplayed(topofPageElement);
	
	}
	
	
	
	
}
