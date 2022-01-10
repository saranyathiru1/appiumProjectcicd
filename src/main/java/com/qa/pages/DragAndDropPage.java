package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.qa.AppBaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DragAndDropPage extends AppBaseTest{
	
	static Logger log = LogManager.getLogger(DragAndDropPage.class.getName());
	
	
	@AndroidFindBy(id="drag_dot_1") AndroidElement dragdot1;
	@AndroidFindBy(id="drag_dot_2") AndroidElement dragdot2;
	@AndroidFindBy(id="drag_result_text") AndroidElement dragResultText;
	
	public String getDragResultText() {
		
		return getAttribute(dragResultText, "text");		
	}
	
	public WebElement dragDot1() {
		
		log.info("Identify element for first circle");
		return dragdot1;
		
	}
	
	
	public WebElement dragDot2() {
		
		log.info("Identify element for second circle");
		return dragdot2;
		
	}
	
	
	
	

}
