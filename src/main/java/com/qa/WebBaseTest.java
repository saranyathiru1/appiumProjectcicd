 package com.qa;


import com.google.common.collect.ImmutableMap;
import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.io.File;
import java.io.InputStream;

import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class WebBaseTest {
 
	public static AppiumDriver<WebElement> driver;
	public static HashMap<String,String> strings = new HashMap<String,String>();
	static Logger log = LogManager.getLogger(WebBaseTest.class.getName());
	static AppiumDriverLocalService server;
	
	public static Properties  props;
	InputStream inputStream;	
	InputStream stringis;
	TestUtils utils;
	public static String dateTime;
	
	

	//constructor added for POM
	public WebBaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//constructor added for appiumdriver
	public AppiumDriver<WebElement> getDriver() {
		return driver;
	}

	
	public AppiumDriverLocalService getAppiumServerDefault() {
		//return AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withLogFile(new File("ServerLogs/server.log")));
		return AppiumDriverLocalService.buildDefaultService();
	}
	
	@BeforeSuite
	public void beforeSuite() {
		server = getAppiumServerDefault();
		server.start();
		log.info("Appium server started");
	}
	
	@AfterSuite
	public void afterSuite() {
		server.stop();
		log.info("Appium server stopped");
	}
	
	@Parameters({"platformName","deviceName","browserName"})
  @BeforeTest
  public void beforeTest(String platformName, String deviceName, String browserName) throws Exception {
	  
	  try {
		  props = new Properties();
		  
		  String propFileName = "configweb.properties";
		  
		 
		  String xmlFileName = "strings/strings.xml";
		  utils = new TestUtils();
		  utils.getDateTime();
		  
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  
		  stringis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
		 
		  strings = utils.parseStringXML(stringis);
		  
		  DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("platformName", platformName);
			desiredCapabilities.setCapability("deviceName", deviceName);
			desiredCapabilities.setCapability("browserName", browserName);
			desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
			desiredCapabilities.setCapability("newCommandTimeout", 60);
			//desiredCapabilities.setCapability("fullReset", true);
			desiredCapabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c",false));
			desiredCapabilities.setCapability("recreateChromeDriverSessions", true);
			driver = new AppiumDriver<WebElement>(new URL(props.getProperty("appiumURL")), desiredCapabilities);
			log.info("Initializing driver for Web browser");
			driver.get(props.getProperty("webURL"));
			log.info("Launching Amazon website");
			
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  finally{
		  if(inputStream != null) {
			  inputStream.close();
		  }
		  if(stringis != null) {
			  stringis.close();
		  }
	  }
	  
  }
	
	public void waitForVisibilityElements(WebElement e) {
		try {
		WebDriverWait wait = new WebDriverWait(driver,TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
		}
		catch(Exception ex) {
			log.error("Element not found");
		}
	}
	
	
	public void clickButton(WebElement e) {
		try {
		waitForVisibilityElements(e);
		e.click();
		}
		catch(Exception ex) {
			log.error("click action not performed");
			
		}
	}
	
	public boolean isDisplayed(WebElement e) {
		try {
		waitForVisibilityElements(e);
		
		}
		catch(Exception ex) {
			log.error("element is not displayed");
		}
		return e.isDisplayed();
	}
	
	public void sendkeys(WebElement e, String attribute ) {
		try {
			waitForVisibilityElements(e);
			e.sendKeys(attribute);		
		}
		catch(Exception ex) {
			log.error("Unable to perform senkeys for element");
		}
		
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public WebElement MoveToAction(WebElement element) {
		try {
		Actions actions = new Actions(driver);
		log.info("Scrolling to bottom of page");
		actions.moveToElement(element);
		actions.perform();
		log.info("Perform move to action until the element:" + element + "is found");
		log.info("Scrolled to bottom of page");
		}
		catch(Exception ex) {
			log.error("Unable to perform move to element action");
		}
		return element;
	
	}

@AfterTest
  public void tearDown() {
	  
		
	  driver.quit();
  }

}
