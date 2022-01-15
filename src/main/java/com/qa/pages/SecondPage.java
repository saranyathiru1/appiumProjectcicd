package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.qa.AppBaseTest;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SecondPage extends AppBaseTest{
	
	static Logger log = LogManager.getLogger(SecondPage.class.getName());
	
	@AndroidFindBy(accessibility="Drag and Drop")  AndroidElement dragAndDrop;
	//@iOSXCUITFindBy (accessibility="Drag and Drop")  IOSElement dragAndDrop1;
	@AndroidFindBy(accessibility="Expandable Lists")  AndroidElement expandList;
	
	public DragAndDropPage clickOnDragandDrop() {
		
		click(dragAndDrop, "Click on Drag and Drop option");
		return new DragAndDropPage();
	}
	
	public WebElement clickOnExpandableList() {
		
		log.info("find element for Expandable lists option");
		return expandList;
	}
	
	

}
