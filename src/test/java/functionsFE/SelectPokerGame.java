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

public class SelectPokerGame extends VariablesStore {

	CreateReport cR = new CreateReport();

	WebDriverWait wait;
	String fail;

	public void selectPoker() throws FailedLoginException {
		fail = "selectPoker failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectPoker = bDriver.getDriver().findElement(By.xpath("(//div[@class='header_menu_item'])[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(selectPoker));
		String selectSlotsText = selectPoker.getText();

		if (selectPoker.isDisplayed()) {
			selectPoker.click();
			cR.getExtentTest().info("Clicked " + selectSlotsText);
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
			Thread.sleep(1500);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

	String parentWindowHandle;
	Set<String> nextWindowHandle;

	public void selectGameInVendor(String gameName) throws FailedLoginException, InterruptedException {
		fail = "selectGameInVendor failed";

		WebElement selectGameInVendor = bDriver.getDriver().findElement(By.xpath("//div[normalize-space()='" + gameName + "']"));
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