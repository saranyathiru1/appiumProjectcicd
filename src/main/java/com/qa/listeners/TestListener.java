package com.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.qa.AppBaseTest;
import com.qa.WebBaseTest;

public class TestListener implements ITestListener {
	
	public void onTestFailure(ITestResult result) {
		
		if(result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println(sw.toString());
			
			AppBaseTest base = new AppBaseTest();
			File file = base.getDriver().getScreenshotAs(OutputType.FILE);
			
			Map<String, String> params = new HashMap<String,String>();
			params = result.getTestContext().getCurrentXmlTest().getAllParameters();
			
			DateFormat  dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			
			String imagePath = "Screenshots" + File.separator + params.get("platformName") + "_" + File.separator + params.get("deviceName") + File.separator + dateFormat.format(new Date())
			+ File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".png";
			
			String completeimagePath = System.getProperty("user.dir") + File.separator + imagePath; 
			try {
				FileUtils.copyFile(file, new File(imagePath	));
				Reporter.log("This is sample screenshot");
				Reporter.log("<a href='"+ completeimagePath + "'> <img src='"+ completeimagePath + "' height='400' width='400'/> </a>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	

}
