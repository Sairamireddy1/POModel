package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchPage {

	WebDriver driver;
	public GoogleSearchPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By googleseach = By.xpath("//input[@class='gLFyf gsfi' and @title='Search']");
	By searchbtn = By.xpath("//input[@class='gNO89b']");
	By facebook = By.xpath("//h3[contains(text(),'Facebook - log in or sign up')]");
	
	public void SearchGoogle(String name)
	{
		driver.findElement(googleseach).sendKeys(name);
		driver.findElement(searchbtn).click();
	}
	
	public void ClickFacebook()
	{
		driver.findElement(facebook).click();
	}
	
}
