package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.qa.AppBaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CustomAdapterPage extends AppBaseTest{
	
	static Logger log = LogManager.getLogger(CustomAdapterPage.class.getName());
	
	
	@AndroidFindBy(accessibility="1. Custom Adapter")   AndroidElement customAdapter;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='People Names']")  AndroidElement peopleNames;
	@AndroidFindBy(xpath="//android.widget.TextView[@text = 'Sample menu']")  AndroidElement sampleMenu;
	
	
	
	public void clickOnCustomAdapter() {
		
		click(customAdapter, "Clicking on Custom Adapter option");
	}
	
	public WebElement clickOnPeopleNames() {
		
		log.info("Clicking on Custom Adapter option");
			return peopleNames;
			
		}
	
	public boolean checkSampleMenutext() {
		
		log.info("Checking Sample Menu text");
		return isDisplayed(sampleMenu);
		
	}
	
	

}
