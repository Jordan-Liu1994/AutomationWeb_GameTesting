package functionsFE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.VariablesStore;

public class GetPastLogin extends VariablesStore {

	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty(driverTypeChrome, ".\\src\\test\\resources\\drivers\\" + driverPathChrome);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://wltestmembersite.the777888.com/");
		
		WebElement loginBtn = driver.findElement(By.id("header_login"));
		loginBtn.click();
		Thread.sleep(3000);
		
		Actions builder = new Actions(driver);
		WebElement captchaBtn = driver.findElement(By.xpath("//div[@class='gt_slider_knob gt_show']"));
		
		if(captchaBtn.isDisplayed()) {
			builder.moveToElement(captchaBtn).clickAndHold(captchaBtn).moveByOffset(100, 0).release().build().perform();
		}
		
		Thread.sleep(3000);
		driver.quit();
	}

}
