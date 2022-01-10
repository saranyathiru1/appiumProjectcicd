package com.qa;


import com.qa.utils.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.InputStream;

import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;


public class AppBaseTest {
 
	public static AndroidDriver<AndroidElement> driver;
	public static HashMap<String,String> strings = new HashMap<String,String>();
	static Logger log = LogManager.getLogger(WebBaseTest.class.getName());
	static AppiumDriverLocalService server;
	
	public static Properties  props;
	InputStream inputStream;	
	InputStream stringis;
	TestUtils utils;
	URL url;
	
	//constructor added
	public AppBaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	

	
	@Parameters({"platformName","deviceName"})
  @BeforeTest
  public void beforesecondTest(String platformName, String deviceName) throws Exception {
	  
	  try {
		  props = new Properties();
		  
		  String propFileName = "configapp.properties";
		  
		 
		  String xmlFileName = "strings/strings.xml";
		  
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  
		  stringis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
		  utils = new TestUtils();
		  strings = utils.parseStringXML(stringis);
		  
		  DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("platformName", platformName);
			desiredCapabilities.setCapability("deviceName", deviceName);
			desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
			desiredCapabilities.setCapability("newCommandTimeout", 60);
			desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackageName"));
			desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
			String appURL = System.getProperty("user.dir") + props.getProperty("androidAppLocation");
			desiredCapabilities.setCapability("app", appURL);
			desiredCapabilities.setCapability("noReset", true);
			
			
			driver = new AndroidDriver<AndroidElement>(new URL(props.getProperty("appiumURL")), desiredCapabilities);
			log.info("Initializing driver for native app");
			log.info("Opening Api Demo app");
			
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
	
	public void waitForVisibility(AndroidElement e) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,TestUtils.WAIT);
			wait.until(ExpectedConditions.visibilityOf(e));
		}
		catch(Exception ex) {
			log.error("Element not found");
		}
	
	}
	
	public void click(AndroidElement e) {
		try {
		waitForVisibility(e);
		e.click();
		}
		catch(Exception ex) {
			log.error("click action not performed");
		}
	}
	
	public boolean isDisplayed(AndroidElement e) {
		try {
		waitForVisibility(e);
		}
		catch(Exception ex) {
			log.error("element is not displayed");
		}
		return e.isDisplayed();
	}
	

	public String getAttribute(AndroidElement e, String attribute ) {
		try {
		waitForVisibility(e);
		
		}
		catch(Exception ex) {
			log.error("No attribute value found");
		}
		return e.getAttribute(attribute);
	}
	
	
@AfterTest
public void tearDown() throws Exception {  
	driver.quit();
 }
}
