package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.AppBaseTest;

import com.qa.pages.CustomAdapterPage;
import com.qa.pages.DragAndDropPage;
import com.qa.pages.MainPage;
import com.qa.pages.SecondPage;
import com.qa.utils.TestUtils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ApiDemoTest extends AppBaseTest{

	MainPage mainPage;
	SecondPage secondPage;
	TestUtils utils;
	DragAndDropPage dragDrop;
	CustomAdapterPage customAdapter;
	
	static Logger log = LogManager.getLogger(ApiDemoTest.class.getName());
	
	
	

  
  @BeforeMethod
  public void beforeMethod() {
	  
	  System.out.println("Api demo test before method");
	  log.info("Instantiating the page object classes for ApiDemos App");
	  mainPage = new MainPage();
	  secondPage = new SecondPage();
	  utils = new TestUtils();
	  dragDrop = new DragAndDropPage();
	  customAdapter = new CustomAdapterPage();
  }
  

  @AfterMethod
  public void afterMethod() {
	  
	  System.out.println("Api demo test after method");
  }
  
  @Test(priority=1)
  	public void scrollTest() {
	  try {
	   mainPage.clickOnViews();
		   String actualText = utils.scrollToText(strings.get("Web_View_Option"));
		   String expectedText = strings.get("Web_View_Option");
	   Assert.assertEquals(actualText, expectedText);
	   utils.keyEvent();
	   }
	   catch(Exception e){
		  log.error("Error in scroll test") ;
	   }
	  
  }

  @Test(priority=2)
  	public void dragandDropTest() {
	 try {
	mainPage.clickOnViews();
	secondPage.clickOnDragandDrop();
	WebElement first = dragDrop.dragDot1();
	WebElement second = dragDrop.dragDot2();
	utils.longPress(first);
	utils.MoveAction(second);
		String actualText = dragDrop.getDragResultText();
		String expectedText = strings.get("Drag_Drop_Text");
		Assert.assertEquals(actualText, expectedText);
		utils.keyEvent();
	}
	catch(Exception e){
		log.error("Error in drag and drop test");
	}
	
	
}
  
  @Test(priority=3)
  public void touchgesturesTest() {
	  try {
	  WebElement expandList = secondPage.clickOnExpandableList();
	   utils.touchAction(expandList);
	   customAdapter.clickOnCustomAdapter();
	   WebElement peopleNames = customAdapter.clickOnPeopleNames();
	   utils.longPressAction(peopleNames);
	  Assert.assertTrue(customAdapter.checkSampleMenutext());
	   }
	   catch(Exception e) {
		   log.error("Error in touch gestures test");
	   }
  }
  
 


}
