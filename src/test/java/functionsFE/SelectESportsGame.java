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

public class SelectESportsGame extends VariablesStore {

	CreateReport cR = new CreateReport();

	WebDriverWait wait;
	String fail;
	String parentWindowHandle;
	Set<String> nextWindowHandle;

	public void selectESports() throws FailedLoginException {
		fail = "selectESports failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectESports = bDriver.getDriver().findElement(By.xpath("(//div[@class='header_menu_item'])[7]"));
		wait.until(ExpectedConditions.elementToBeClickable(selectESports));
		String selectESportsText = selectESports.getText();

		if (selectESports.isDisplayed()) {
			selectESports.click();
			cR.getExtentTest().info("Clicked " + selectESportsText);
			parentWindowHandle = bDriver.getDriver().getWindowHandle();
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

		if (selectVendor.isDisplayed()) {
			selectVendor.click();
			cR.getExtentTest().info("Clicked " + selectVendorText);
			Thread.sleep(1000);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

	public void selectGameInVendor() throws FailedLoginException, InterruptedException {
		fail = "selectGameInVendor failed";

		Thread.sleep(1000);
		nextWindowHandle = bDriver.getDriver().getWindowHandles();
		Thread.sleep(1000);
		Iterator<String> iterate = nextWindowHandle.iterator();
		while (iterate.hasNext()) {
			String winHandle = iterate.next();
			bDriver.getDriver().switchTo().window(winHandle);
			System.out.println(winHandle);
		}
	}
}