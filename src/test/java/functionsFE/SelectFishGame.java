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

public class SelectFishGame extends VariablesStore {

	CreateReport cR = new CreateReport();

	WebDriverWait wait;
	String fail;

	public void selectFish() throws FailedLoginException {
		fail = "selectFish failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectFish = bDriver.getDriver().findElement(By.xpath("(//div[@class='header_menu_item'])[6]"));
		wait.until(ExpectedConditions.elementToBeClickable(selectFish));
		String selectFishText = selectFish.getText();

		if (selectFish.isDisplayed()) {
			selectFish.click();
			cR.getExtentTest().info("Clicked " + selectFishText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void selectMainVendor() throws FailedLoginException, InterruptedException {
		fail = "selectMainVendor failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectMainVendor = bDriver.getDriver().findElement(By.xpath("//div[@class='content_block active']"));
		wait.until(ExpectedConditions.visibilityOf(selectMainVendor));
		String selectMainVendorText = selectMainVendor.getText();

		if (selectMainVendor.isEnabled()) {
			cR.getExtentTest().info(selectMainVendorText + " already selected");
			Thread.sleep(500);
		} else if (!selectMainVendor.isEnabled()) {
			selectMainVendor.click();
			cR.getExtentTest().info("Clicked " + selectMainVendorText);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

	public void selectOtherVendor(String vendorName) throws FailedLoginException, InterruptedException {
		fail = "selectOtherVendor failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectOtherVendor = bDriver.getDriver().findElement(By.xpath("//div[normalize-space()='" + vendorName + "']//span"));

		if (!selectOtherVendor.isSelected()) {
			selectOtherVendor.click();
			cR.getExtentTest().info("Clicked " + vendorName);
			Thread.sleep(500);
		}
	}

	String parentWindowHandle;
	Set<String> nextWindowHandle;

	public void selectGameInVendor(String gameName) throws FailedLoginException, InterruptedException {
		fail = "selectGameInVendor failed";

		WebElement selectGameInVendor = bDriver.getDriver().findElement(By.xpath("//div[contains(text(),'" + gameName + "')]"));
		Actions builder = new Actions(bDriver.getDriver());
		Action act = builder.moveToElement(selectGameInVendor).build();
		act.perform();
		String selectGameInVendorText = selectGameInVendor.getText();
		parentWindowHandle = bDriver.getDriver().getWindowHandle();
		Thread.sleep(500);

		if (selectGameInVendor.isDisplayed()) {
			selectGameInVendor.click();
			nextWindowHandle = bDriver.getDriver().getWindowHandles();
			cR.getExtentTest().info("Clicked " + selectGameInVendorText);
			Thread.sleep(1500);
			Iterator<String> iterate = nextWindowHandle.iterator();
			while (iterate.hasNext()) {
				String winHandle = iterate.next();
				bDriver.getDriver().switchTo().window(winHandle);
				System.out.println(winHandle);
			}
		} else {
			cR.getExtentTest().fail(fail);
		}
	}
}