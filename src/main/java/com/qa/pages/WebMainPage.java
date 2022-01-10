package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.AppBaseTest;
import com.qa.WebBaseTest;


import io.appium.java_client.pagefactory.AndroidFindBy;

public class WebMainPage extends WebBaseTest{
	
	static Logger log = LogManager.getLogger(WebMainPage.class.getName());
	
	@FindBy(id="nav-logo-sprites")  WebElement amazonLogo;
	@FindBy(css=".nav-input.nav-progressive-attribute")  WebElement searchField;
	@FindBy(css="input[type='submit']")  WebElement searchButton;
	
	
	public boolean checkAmazonLogo() {
		
		log.info("Check if amazon logo is displayed");
		return isDisplayed(amazonLogo);
	}
	
	
	public void searchItems(String string) {
		log.info("Enter books value in search bar");
		sendkeys(searchField, strings.get("SendKeys_Text"));
	
	}
	
	

	public WebBooksPage clickSearchButton() {
		clickButton(searchButton);
		log.info("Click on search button");
		return new WebBooksPage();
	}
	
	
	
}
