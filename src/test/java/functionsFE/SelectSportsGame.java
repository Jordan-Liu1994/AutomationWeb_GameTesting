package functionsFE;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.CreateReport;
import utilities.VariablesStore;

public class SelectSportsGame extends VariablesStore {

	CreateReport cR = new CreateReport();

	WebDriverWait wait;
	String fail;
	String parentWindowHandle;
	Set<String> nextWindowHandle;

	public void selectSports() throws FailedLoginException {
		fail = "selectSports failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectSports = bDriver.getDriver().findElement(By.xpath("(//div[@class='header_menu_item'])[4]"));
		wait.until(ExpectedConditions.elementToBeClickable(selectSports));
		String selectSportsText = selectSports.getText();

		if (selectSports.isDisplayed()) {
			selectSports.click();
			cR.getExtentTest().info("Clicked " + selectSportsText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void selectVendor(String vendorName) throws FailedLoginException, InterruptedException {
		fail = "selectVendor failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectVendor = bDriver.getDriver().findElement(By.xpath("//div[@class='more_game_item']//div//div[contains(text(),'" + vendorName + "')]"));
		wait.until(ExpectedConditions.visibilityOf(selectVendor));
		wait.until(ExpectedConditions.elementToBeClickable(selectVendor));
		String selectVendorText = selectVendor.getText();
		parentWindowHandle = bDriver.getDriver().getWindowHandle();
		Thread.sleep(500);

		if (selectVendor.isDisplayed()) {
			selectVendor.click();
			cR.getExtentTest().info("Clicked " + selectVendorText);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

	public void switchWindow() throws InterruptedException {
		fail = "selectGameInVendor failed";

		nextWindowHandle = bDriver.getDriver().getWindowHandles();
		Thread.sleep(500);
		Iterator<String> iterate = nextWindowHandle.iterator();
		while (iterate.hasNext()) {
			String winHandle = iterate.next();
			bDriver.getDriver().switchTo().window(winHandle);
			System.out.println(winHandle);
			bDriver.getDriver().manage().window().maximize();
		}
		Thread.sleep(10000);
	}
}