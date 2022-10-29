package Tast_Scenarios;

	import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
	import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

	import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//import com.google.common.io.Files;

	import Objects.FacebookPage;
	import Objects.GoogleSearchPage;
import Utilities.Dataprovider_Exel;
import Utilities.Extent_Reports;
import Utilities.Listners;
import io.github.bonigarcia.wdm.WebDriverManager;

	
public class Test_Facebook {

//	Dataprovider_Exel Exel = new Dataprovider_Exel();
	
		static WebDriver driver;  //parent P(instantiation)
		GoogleSearchPage objectrepo;
		FacebookPage Page;
//		Extent_Reports report;
//		Listners listners;
		
//		Date CurrentDate = new Date();
//		String screenshotfilename= CurrentDate.toString().replace(" ","-").replace(":", "-");
		
		@SuppressWarnings("deprecation")
		@BeforeTest
		public void beforetest()
		{
//			WebDriverManager.chromedriver().setup();
//			driver= new ChromeDriver();  //(instantiated) P = new child      
//			driver.manage().deleteAllCookies();
			
			System.setProperty("webdriver.chrome.driver", ".//Driver//chromedriver.exe");

			driver=new ChromeDriver();

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			
			driver.get("https://www.google.com/");
			Assert.assertEquals(driver.getTitle(),"Google");
			
			
		}

		@Test(priority=0)
		public void searchOperation() throws IOException
		{
			String title = driver.getTitle();
			System.out.println(title);
			objectrepo = new GoogleSearchPage(driver);
			objectrepo.SearchGoogle("facebook");
			Assert.assertEquals(driver.getTitle(),"Log in to Facebook");
		}
		
		@SuppressWarnings("deprecation")
		@Test(priority=1)
		public void VerifyandAccessFacebook()
		{
//			driver= new ChromeDriver();  //(instantiated) P = new child      

			objectrepo = new GoogleSearchPage(driver);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			objectrepo.ClickFacebook();
//			Assert.assertEquals(driver.getTitle(),"facebook login - Google Search");
		}
		
		@SuppressWarnings("deprecation")
		@Test(priority=2,dataProvider="testdata",dataProviderClass = Dataprovider_Exel.class)
		public void verifylogin(Map mapdata) throws IOException
		{
//			Object[][] obj = Exel.dataprovider_excel();
			Page = new FacebookPage(driver);
			
			String username = String.valueOf(mapdata.get("name"));
			String password = String.valueOf(mapdata.get("salary"));
			String age = String.valueOf(mapdata.get("age"));

			System.out.println("----test started----------------------------------------------");			
			System.out.println("----test started---------------------------------------------");
			System.out.println(username);
			System.out.println(password);
			System.out.println(age);
			System.out.println("----test End---------------------------------------------------");
			System.out.println("----test end-------------------------------------------------");

			
			
//			WebDriverWait wait = new WebDriverWait(driver, null);
//			WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			Page.enterusername(username);
			Page.enterpassword(password);
			Page.login();
			driver.navigate().back();
//			Assert.assertEquals(driver.getTitle(),"Log in to Facebook");
		}
	/*	
		@AfterMethod
		public void screenshot(ITestResult result) throws IOException{
//			String title = driver.getTitle();
//			System.out.println(title);
			if(result.getStatus() == ITestResult.FAILURE);
			{ 
			TakesScreenshot screenshot = (TakesScreenshot)driver;  //Converted driver to take SS
			File src= screenshot.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src,new File("./screenshots/"+driver.getTitle()+"screen.png"));
			}
		}
	

		@AfterMethod
		public void onTestFailure(ITestResult result) throws IOException{
		listners = new Listners();
//		report = new Extent_Reports();
		if(result.getStatus() == ITestResult.FAILURE)
		{
			listners.onTestFailure(result);
		}
		else
		{
			listners.onTestSuccess(result);
		}
	}

		
		@AfterMethod
		public static void main(String[] args) throws IOException {
		Date CurrentDate = new Date();
		String screenshotfilename= CurrentDate.toString().replace(" ","-").replace(":", "-");
		System.out.println(screenshotfilename);
		
		File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(f,new File("/screenshot/"+screenshotfilename+".jpg"));
		}
	*/
		
		@AfterTest
		public void aftertest()
		{
//			String title = driver.getTitle();
//			System.out.println(title);
			driver.quit(); 
//			extent.flush();

		}

		
		
//	 	static WebDriver driver;
//		Test_Facebook testbase= new Test_Facebook(driver);

		Date CurrentDate = new Date();
		String screenshotfilename= CurrentDate.toString().replace(" ","-").replace(":", "-");
	/*	
		public Extent_Reports(WebDriver driver)
		{
			this.driver= driver;
		}
	*/	
//		ExtentReports extent= new ExtentReports();
		public void extent_report_screenshot() throws IOException
		{
			System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
			System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");

			File file = new File("./Reports/"+screenshotfilename+"-Extent_report.html");
//			spark = new ExtentSparkReporter(file);
			ExtentReports extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(file);
			
			spark.config().setTheme(Theme.DARK);
			spark.config().setReportName("Report Name: POM TEST REPORT");
			spark.config().setDocumentTitle("POM report");
//			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("java Version", System.getProperty("java.version"));
			
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
			.fail("This test is Failed")
			.addScreenCaptureFromPath(onTestFailure());
			
			extent.flush();
		}
/*		
		public void ontestfailure() throws IOException
		{
			ExtentSparkReporter spark = null;

			extent.attachReporter(spark);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("java Version", System.getProperty("java.version"));
			
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
			.fail("This test is Failed")
			.addScreenCaptureFromPath(onTestFailure());	
	
//			Desktop.getDesktop().browse(new File("Extent_report.html").toURI());
		}
		
		public void ontestpass()
		{
			File file = new File("./Reports/"+screenshotfilename+"-Extent_report2.html");
//			spark = new ExtentSparkReporter(file);
			ExtentReports extent1 = new ExtentReports();
			ExtentSparkReporter spark1 = new ExtentSparkReporter(file);
			
			spark1.config().setTheme(Theme.DARK);
			spark1.config().setReportName("Report Name: POM TEST REPORT");
			spark1.config().setDocumentTitle("POM report");
//			extent = new ExtentReports();
			extent1.attachReporter(spark1);
			extent1.setSystemInfo("OS", System.getProperty("os.name"));
			extent1.setSystemInfo("java Version", System.getProperty("java.version"));
			extent1.attachReporter(spark1);
			extent1.setSystemInfo("OS", System.getProperty("os.name"));
			extent1.setSystemInfo("java Version", System.getProperty("java.version"));
			
			extent1.createTest("Test case passed","This test got passed")
			.info("This is indicated PASSED TEST")
			.assignAuthor("Sai","ram","Reddy")
			.assignCategory("Smoke","Sanity")
			.assignDevice("Chrome","safari","IE","Samsung")
			.pass("This test is Failed");
		}

		public void ontestskip()
		{
			ExtentSparkReporter spark = null;

			extent.attachReporter(spark);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("java Version", System.getProperty("java.version"));
			
			extent.createTest("Test case Skipped","This test got skipped")
			.info("This is indicated SKPEED TEST")
			.assignAuthor("Sai","ram","Reddy")
			.assignCategory("Smoke","Sanity")
			.assignDevice("Chrome","safari","IE","Samsung")
			.skip("This test is skipped");
		}
*/		
		public String onTestFailure() throws IOException
		{
//					driver = new ChromeDriver();
//			WebDriver driver = null;
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
//			driver=new ChromeDriver();
//			WebDriver driver = null;
			String base64Code =((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
			System.out.println("onBase64Failure Screenshot captured");
			return base64Code;
		}
		
		
		
}
	
