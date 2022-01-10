package com.qa.webtests;

import org.testng.annotations.Test;

import com.qa.AppBaseTest;

import com.qa.WebBaseTest;

import com.qa.pages.CustomAdapterPage;
import com.qa.pages.DragAndDropPage;
import com.qa.pages.MainPage;
import com.qa.pages.SecondPage;
import com.qa.pages.WebBooksPage;
import com.qa.pages.WebMainPage;
import com.qa.tests.ApiDemoTest;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class AmazonWebTest extends WebBaseTest{

	WebMainPage mainPage;
	WebBooksPage booksPage;
	TestUtils utils;
	
	static Logger log = LogManager.getLogger(ApiDemoTest.class.getName());

	
  
  @BeforeMethod
  public void beforeMethod() {
	  
	  log.info("Instantiating the page object classes for Amazon.com Website");
	  mainPage = new WebMainPage();
	  booksPage = new WebBooksPage();
	  utils = new TestUtils();
	  
  }
  
  @Test(priority=1)
  	public void verifyAmazonLogoTest() {
	   
	  try {
		  Assert.assertTrue(mainPage.checkAmazonLogo());

	  }
	  catch(Exception e) {
		  log.error("Error in Amazon logo test");
	  }
  }

 

@Test(priority=2)
  	public void searchItemsandVerifyTitleTest() throws Exception {
	try {
	 mainPage.searchItems(strings.get("SendKeys_Text"));
	  mainPage.clickSearchButton();
		Thread.sleep(3000);
		
		String actualText =booksPage.checkBooksTitle();
		String expectedText = strings.get("Books_Text");
		
		 Assert.assertEquals(actualText, expectedText);
		}
		catch(Exception e) {
			log.error("Error in search and verify title test");
		}
	
}
  
  @Test(priority=3)
  public void scrollToBottomTest() {
	  try {
		MoveToAction(booksPage.BottomOfPageElement());	
		Assert.assertTrue(booksPage.scrollToBottomOfPageElement());
		}
	  
		catch(Exception e) {
			log.error("Error in scroll to bottom test");
		}
	   
  }
  
 


}
