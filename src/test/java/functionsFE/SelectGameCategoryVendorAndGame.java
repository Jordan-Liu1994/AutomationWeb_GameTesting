package functionsFE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.CreateReport;
import utilities.TakeScreenShot;
import utilities.VariablesStore;

public class SelectGameCategoryVendorAndGame extends VariablesStore {

	CreateReport cR = new CreateReport();
	TakeScreenShot takeSS = new TakeScreenShot();

	String fail;
	WebDriverWait wait;
	Actions builder;
	String parentWindowHandle;
	Set<String> nextWindowHandle;

	public void hoverGameCategory(String categoryXpathNumber) throws FailedLoginException, InterruptedException {
		fail = "hoverGameCategory failed";
		String hoverGameCategoryText;

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		builder = new Actions(bDriver.getDriver());
		WebElement hoverGameCategory = bDriver.getDriver().findElement(By.xpath("(//div[@class='header_menu_item'])[" + categoryXpathNumber + "]"));
		wait.until(ExpectedConditions.visibilityOf(hoverGameCategory));
		hoverGameCategoryText = hoverGameCategory.getText();
		Action act = builder.moveToElement(hoverGameCategory).build();
		act.perform();

		if (hoverGameCategory.isDisplayed()) {
			cR.getExtentTest().info("Hovered over " + hoverGameCategoryText);
			Thread.sleep(500);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	String selectGameVendorText;

	public void selectGameVendor(String gameVendorName) throws FailedLoginException, InterruptedException {
		fail = "selectGameVendor failed";
		parentWindowHandle = bDriver.getDriver().getWindowHandle();

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectGameVendor = bDriver.getDriver().findElement(By.xpath("//div[contains(text(),'" + gameVendorName + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(selectGameVendor));
		selectGameVendorText = selectGameVendor.getText();

		if (selectGameVendor.isDisplayed()) {
			selectGameVendor.click();
			cR.getExtentTest().info("Clicked " + selectGameVendorText);
			Thread.sleep(500);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

	public void selectGameFromSlotsList(String gameName1, String gameName2, String gameName3, String gameName4, String gameName5, int time) throws FailedLoginException, InterruptedException {
		fail = "selectGameFromSlotsList failed";
		parentWindowHandle = bDriver.getDriver().getWindowHandle();

		ArrayList<String> arraylist = new ArrayList<String>();
		arraylist.add(gameName1);
		arraylist.add(gameName2);
		arraylist.add(gameName3);
		arraylist.add(gameName4);
		arraylist.add(gameName5);

		for (int i = 0; i <= 4; i++) {
			String array = arraylist.get(i);
			WebElement selectGameFromSlotsList = bDriver.getDriver().findElement(By.xpath("//div[contains(text(),'" + array + "')]"));
			if (selectGameFromSlotsList.isDisplayed()) {
				selectGameFromSlotsList.click();
				cR.getExtentTest().info("Clicked " + array);

				nextWindowHandle = bDriver.getDriver().getWindowHandles();
				Thread.sleep(500);
				Iterator<String> iterate = nextWindowHandle.iterator();
				while (iterate.hasNext()) {
					String winHandle = iterate.next();
					bDriver.getDriver().switchTo().window(winHandle);
					bDriver.getDriver().manage().window().maximize();
				}
				Thread.sleep(time);
				takeSS.getPassScreenShot(array);
				cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + array + passSS, array);

				bDriver.closeBrowser();
				bDriver.getDriver().switchTo().window(parentWindowHandle);
			} else {
				cR.getExtentTest().fail(fail);
			}
		}
	}
	
	public void selectGameFromFishList(String gameName1, String gameName2, String gameName3, int time) throws FailedLoginException, InterruptedException {
		fail = "selectGameFromFishList failed";
		parentWindowHandle = bDriver.getDriver().getWindowHandle();

		ArrayList<String> arraylist = new ArrayList<String>();
		arraylist.add(gameName1);
		arraylist.add(gameName2);
		arraylist.add(gameName3);

		for (int i = 0; i <= 2; i++) {
			String array = arraylist.get(i);
			WebElement selectGameFromFishList = bDriver.getDriver().findElement(By.xpath("(//div[contains(text(),'" + array + "')])[1]"));
			if (selectGameFromFishList.isDisplayed()) {
				selectGameFromFishList.click();
				cR.getExtentTest().info("Clicked " + array);

				nextWindowHandle = bDriver.getDriver().getWindowHandles();
				Thread.sleep(500);
				Iterator<String> iterate = nextWindowHandle.iterator();
				while (iterate.hasNext()) {
					String winHandle = iterate.next();
					bDriver.getDriver().switchTo().window(winHandle);
				}
				Thread.sleep(time);
				takeSS.getPassScreenShot(array);
				cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + array + passSS, array);

				bDriver.closeBrowser();
				bDriver.getDriver().switchTo().window(parentWindowHandle);
			} else {
				cR.getExtentTest().fail(fail);
			}
		}
	}
	
	public void switchWindow() throws InterruptedException {
		nextWindowHandle = bDriver.getDriver().getWindowHandles();
		Thread.sleep(500);
		Iterator<String> iterate = nextWindowHandle.iterator();
		while (iterate.hasNext()) {
			String winHandle = iterate.next();
			bDriver.getDriver().switchTo().window(winHandle);
			cR.getExtentTest().info("Switched to " + selectGameVendorText);
		}
		Thread.sleep(30000);
		takeSS.getPassScreenShot(selectGameVendorText);
		cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + selectGameVendorText + passSS, selectGameVendorText);
	}
}