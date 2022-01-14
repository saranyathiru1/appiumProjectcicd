package com.qa.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.qa.AppBaseTest;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;


public class TestUtils extends AppBaseTest {

	
	public static final long WAIT = 10;
	static Logger log = LogManager.getLogger(TestUtils.class.getName());
	
	
	
	public String scrollToText(String text) {
		
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
		log.info("Scrolling down to :" +text);
		return text;
	}
	
	public void keyEvent() {
		
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		log.info("Clicking on android back button");
	}
	
	public WebElement touchAction(WebElement element) {
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(element))).perform();
		log.info("Perform touch action on the element:" + element);
		return element;
		
	}
	
	public WebElement longPressAction(WebElement element) {
		TouchAction t = new TouchAction(driver);
		t.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(2))).release().perform();
		log.info("Perform long press action on the element:" + element);
		return element;
		
	}
	
	public WebElement longPress(WebElement element1) {
		TouchAction t = new TouchAction(driver);
		t.longPress(new LongPressOptions().withElement(new 
	            ElementOption().withElement(element1))).perform();
		log.info("Perform long press action on the element:" + element1);
		return element1;
	}
	
	public WebElement MoveAction (WebElement element2) {
		TouchAction t = new TouchAction(driver);
		t.moveTo(new ElementOption().withElement(element2)).release().perform();
		log.info("Perform move action to element:" + element2);
		return element2;
	}
	
	
	public String dateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	
	public HashMap<String, String> parseStringXML(InputStream file) throws Exception{
		HashMap<String, String> stringMap = new HashMap<String, String>();
		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document = builder.parse(file);
		 
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		 
		//Here comes the root node
		Element root = document.getDocumentElement();
		 
		//Get all elements
		NodeList nList = document.getElementsByTagName("string");
		 
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
		 Node node = nList.item(temp);
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {
		    Element eElement = (Element) node;
		    // Store each element key value in map
		    stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
		 }
		}
		return stringMap;
	}
	
	
	
	

}
