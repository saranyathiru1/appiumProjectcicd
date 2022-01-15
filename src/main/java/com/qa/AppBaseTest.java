package com.qa;


import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.screenrecording.CanRecordScreen;

public class AppBaseTest {
 
	public static AndroidDriver<AndroidElement> driver;
	public static HashMap<String,String> strings = new HashMap<String,String>();
	static Logger log = LogManager.getLogger(AppBaseTest.class.getName());
	static AppiumDriverLocalService server;
	static String dateTime;

	
	public static Properties  props;
	InputStream inputStream;	
	InputStream stringis;
	TestUtils utils;
	URL url;
	
	//constructor added for POM
	public AppBaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//get driver constructor
	public AndroidDriver<AndroidElement> getDriver() {
		return driver;
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("super test before method");
		((CanRecordScreen)driver).startRecordingScreen();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		System.out.println("super test after method");
		DateFormat  dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String video = ((CanRecordScreen)driver).stopRecordingScreen();
		
		if(result.getStatus()== 2) {
			
			Map<String,String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
			String dir = "Videos" + File.separator + params.get("platformName") + "_" + File.separator + params.get("deviceName") + File.separator 
					+ dateFormat.format(new Date()) + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".mp4";
			
			File videoDir = new File(dir);
			if(!videoDir.exists()) {
				videoDir.mkdirs();
			}
			
			try {
				FileOutputStream stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
				stream.write(Base64.decodeBase64(video));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
				

	}

	
	@Parameters({"platformName","deviceName"})
  @BeforeTest
  public void beforesecondTest(String platformName, String deviceName) throws Exception {
	  
	  try {
		  utils = new TestUtils();
		  utils.dateTime();
		  props = new Properties();
		  
		  String propFileName = "configapp.properties";
		  
		 
		  String xmlFileName = "strings/strings.xml";
		  
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  
		  stringis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
		  
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
	
	public String getDateTime() {
		return dateTime;
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
