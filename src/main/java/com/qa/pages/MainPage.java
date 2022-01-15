package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.AppBaseTest;
import com.qa.WebBaseTest;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MainPage extends AppBaseTest{
	
	static Logger log = LogManager.getLogger(MainPage.class.getName());
	

	
	@AndroidFindBy(accessibility="Views")  AndroidElement views;
	
	public SecondPage clickOnViews() {
		
		click(views, "Clicking on Views");
		return new SecondPage();
	}

}
