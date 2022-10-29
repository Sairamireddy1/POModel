package Utilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import Tast_Scenarios.Test_Facebook;

public class Listners extends TestListenerAdapter {
//	WebDriver driver;
	Test_Facebook report = new Test_Facebook();
//	report = new Test_Facebook();

	public void onTestStart(ITestResult tr)
	{
		System.out.println("Test started");
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		System.out.println("Test Passed");
//		report.ontestpass();
	}
	
	public void onTestFailure(ITestResult tr)
	{
//		Extent_Reports report;
//		extent_report_screenshot report;
		System.out.println("Test Failed");
			try {
				report.extent_report_screenshot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
	
	public void onTestSkipped(ITestResult tr)
	{
		System.out.println("Test Skipped");
//		report.ontestskip();
	}
	
}
