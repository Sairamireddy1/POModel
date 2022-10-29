package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookPage{

	WebDriver driver;
	public FacebookPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By user = By.xpath("//input[@id='email']");
	By pwd = By.xpath("//input[@id='pass']");
	By loginbtn = By.xpath("//button[@name='login']");
	
	public void enterusername(String username)
	{
		driver.findElement(user).clear();;
		driver.findElement(user).sendKeys(username);
	}
	
	public void enterpassword(String password)
	{
		driver.findElement(pwd).clear();;
		driver.findElement(pwd).sendKeys(password);
	}
	
	public void login()
	{
		driver.findElement(loginbtn).click();
		driver.navigate().back();
	}
	
}
