package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDriver {

	static WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	private String driverPath = ".\\src\\test\\resources\\drivers\\";

	public void setChromeDriverProperty(String driverType, String path) {
		System.setProperty(driverType, driverPath + path);
	}

	public void startChromeDriver() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	public void doRefresh(String url) {
		driver.navigate().refresh();
	}

	public void getURL(String url) {
		driver.get(url);
	}

	public void closeBrowser() {
		driver.close();
	}

	public void stopDriver() {
		driver.quit();
	}
}
