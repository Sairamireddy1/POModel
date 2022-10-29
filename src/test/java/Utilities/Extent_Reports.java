package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Tast_Scenarios.Test_Facebook;

public class Extent_Reports {
	
	static WebDriver driver = new ChromeDriver();
	
	Date CurrentDate = new Date();
	String screenshotfilename= CurrentDate.toString().replace(" ","-").replace(":", "-");
	
	public void extent_report_screenshot() throws IOException
	{
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+driver.getTitle());
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+driver.getCurrentUrl());

		ExtentReports extent = new ExtentReports();
		File file = new File(screenshotfilename+"Extent_report.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Report Name:");
		spark.config().setDocumentTitle("URL:");
		extent.attachReporter(spark);

		extent.createTest("Screenshot Test onBASE64 FILURE","This is for attaching the SS in Test Level")
		.info("This is a info msg")
		.assignAuthor("Bhargav","Animi","Naveen")
		.assignCategory("Regression","Sanity")
		.assignDevice("Chrome","safari","Firefox","ios")
		.fail("This test is Failed")
		.addScreenCaptureFromBase64String(onBase64Failure());

		extent.createTest("Screenshot Test on FAILURE","This is for attaching the SS in Test Level")
		.info("This is a info msg")
		.assignAuthor("Sai","ram","Reddy")
		.assignCategory("Smoke","Sanity")
		.assignDevice("Chrome","safari","IE","Samsung")
		.addScreenCaptureFromPath(onTestFailure());	
/*	
		ExtentTest test1 = extent.createTest("Test 1");
		test1.pass("this is passed"); 	
		
		extent.createTest("Font Types")
		.log(Status.INFO,"info 0")
		.log(Status.SKIP,"<b>info 1</b>")
		.log(Status.WARNING,"<i>info 2</i>")
		.log(Status.PASS,"<b><i>info 1</i></b>");
		
		String Jsondata = "{\"abcd\" : \"menu\"}";
		extent.createTest("(JSON,XML) Data")
		.log(Status.PASS, MarkupHelper.createCodeBlock(Jsondata,CodeLanguage.JSON));
		
		Map<Integer,String> mapData = new HashMap<>();
		mapData.put(101, "saiarm");
		mapData.put(102, "Bhargav");
		extent.createTest("Collection (Map,List,Set) Data")
		.log(Status.PASS, MarkupHelper.createUnorderedList(mapData))
		.log(Status.PASS, MarkupHelper.createOrderedList(mapData));

		try {
			int i =5/0;
		}catch (Exception e) {
			extent.createTest("Exception Test 1")
			.fail(e);
		}
		
		Throwable t = new RuntimeException("This is RUN TIME Exception");
		extent.createTest("Exception Test 2")
		.info(t);
*/		
		extent.flush();
		Desktop.getDesktop().browse(new File("Extent_report.html").toURI());
	}

	
	public String onTestFailure() throws IOException
	{
				
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File("./screenshot/"+screenshotfilename+".png");
		//Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
			} 
		catch(IOException e) 
			{ 
				e.printStackTrace(); 
			}
		System.out.println("OnTestFAILURE screenshot captured");
		return DestFile.getAbsolutePath();
	}
	
	public static String onBase64Failure()
	{
		String base64Code =((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		System.out.println("onBase64Failure Screenshot captured");
		return base64Code;
	}
	
	
}
