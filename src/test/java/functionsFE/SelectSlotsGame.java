package functionsFE;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.VariablesStore;

public class SelectSlotsGame extends VariablesStore {

	CreateReport cR = new CreateReport();

	WebDriverWait wait;
	String fail;

	public void selectSlots() throws FailedLoginException {
		fail = "selectSlots failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectSlots = bDriver.getDriver().findElement(By.xpath("//div[@class='header_bottom_medium']//div[2]//a[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(selectSlots));
		String selectSlotsText = selectSlots.getText();

		if (selectSlots.isDisplayed()) {
			selectSlots.click();
			cR.getExtentTest().info("Clicked " + selectSlotsText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void selectVendorAG() throws FailedLoginException, InterruptedException {
		fail = "selectVendorAG failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectVendorAG = bDriver.getDriver().findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[1]"));
		wait.until(ExpectedConditions.visibilityOf(selectVendorAG));
		wait.until(ExpectedConditions.elementToBeClickable(selectVendorAG));
		String selectVendorAGText = selectVendorAG.getText();

		if (!selectVendorAG.isEnabled()) {
			selectVendorAG.click();
			cR.getExtentTest().info("Clicked " + selectVendorAGText);
			Thread.sleep(1500);
		} else if (selectVendorAG.isEnabled()) {
			cR.getExtentTest().info(selectVendorAGText + " already selected");
		}
	}

	public void selectVendorBBIN() throws FailedLoginException, InterruptedException {
		fail = "selectVendorBBIN failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectVendorBBIN = bDriver.getDriver().findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[2]"));
		wait.until(ExpectedConditions.visibilityOf(selectVendorBBIN));
		wait.until(ExpectedConditions.elementToBeClickable(selectVendorBBIN));
		String selectVendorBBINText = selectVendorBBIN.getText();

		if (!selectVendorBBIN.isSelected()) {
			selectVendorBBIN.click();
			cR.getExtentTest().info("Clicked " + selectVendorBBINText);
			Thread.sleep(1500);
		} else if (selectVendorBBIN.isSelected()) {
			cR.getExtentTest().info(selectVendorBBINText + " already selected");
		}
	}
	
	public void selectVendorHC() throws FailedLoginException, InterruptedException {
		fail = "selectVendorHC failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectVendorHC = bDriver.getDriver().findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[3]"));
		wait.until(ExpectedConditions.visibilityOf(selectVendorHC));
		wait.until(ExpectedConditions.elementToBeClickable(selectVendorHC));
		String selectVendorHCText = selectVendorHC.getText();

		if (!selectVendorHC.isSelected()) {
			selectVendorHC.click();
			cR.getExtentTest().info("Clicked " + selectVendorHCText);
			Thread.sleep(1500);
		} else if (selectVendorHC.isSelected()) {
			cR.getExtentTest().info(selectVendorHCText + " already selected");
		}
	}
	
	public void selectVendorPTTCG() throws FailedLoginException, InterruptedException {
		fail = "selectVendorPTTCG failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectVendorPTTCG = bDriver.getDriver().findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[4]"));
		wait.until(ExpectedConditions.visibilityOf(selectVendorPTTCG));
		wait.until(ExpectedConditions.elementToBeClickable(selectVendorPTTCG));
		String selectVendorPTTCGText = selectVendorPTTCG.getText();

		if (!selectVendorPTTCG.isSelected()) {
			selectVendorPTTCG.click();
			cR.getExtentTest().info("Clicked " + selectVendorPTTCGText);
			Thread.sleep(1500);
		} else if (selectVendorPTTCG.isSelected()) {
			cR.getExtentTest().info(selectVendorPTTCGText + " already selected");
		}
	}
	
	public void selectVendorPP() throws FailedLoginException, InterruptedException {
		fail = "selectVendorPP failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectVendorPP = bDriver.getDriver().findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[5]"));
		wait.until(ExpectedConditions.visibilityOf(selectVendorPP));
		wait.until(ExpectedConditions.elementToBeClickable(selectVendorPP));
		String selectVendorPPText = selectVendorPP.getText();

		if (!selectVendorPP.isSelected()) {
			selectVendorPP.click();
			cR.getExtentTest().info("Clicked " + selectVendorPPText);
			Thread.sleep(1500);
		} else if (selectVendorPP.isSelected()) {
			cR.getExtentTest().info(selectVendorPPText + " already selected");
		}
	}
	
	public void selectVendorMG() throws FailedLoginException, InterruptedException {
		fail = "selectVendorMG failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectVendorMG = bDriver.getDriver().findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[6]"));
		wait.until(ExpectedConditions.visibilityOf(selectVendorMG));
		wait.until(ExpectedConditions.elementToBeClickable(selectVendorMG));
		String selectVendorMGText = selectVendorMG.getText();

		if (!selectVendorMG.isSelected()) {
			selectVendorMG.click();
			cR.getExtentTest().info("Clicked " + selectVendorMGText);
			Thread.sleep(1500);
		} else if (selectVendorMG.isSelected()) {
			cR.getExtentTest().info(selectVendorMGText + " already selected");
		}
	}
	
	public void selectVendorCQ9() throws FailedLoginException, InterruptedException {
		fail = "selectVendorCQ9 failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectVendorCQ9 = bDriver.getDriver().findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[7]"));
		wait.until(ExpectedConditions.visibilityOf(selectVendorCQ9));
		wait.until(ExpectedConditions.elementToBeClickable(selectVendorCQ9));
		String selectVendorCQ9Text = selectVendorCQ9.getText();

		if (!selectVendorCQ9.isSelected()) {
			selectVendorCQ9.click();
			cR.getExtentTest().info("Clicked " + selectVendorCQ9Text);
			Thread.sleep(1500);
		} else if (selectVendorCQ9.isSelected()) {
			cR.getExtentTest().info(selectVendorCQ9Text + " already selected");
		}
	}
	
	public void selectVendorJDB() throws FailedLoginException, InterruptedException {
		fail = "selectVendorJDB failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectVendorJDB = bDriver.getDriver().findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[8]"));
		wait.until(ExpectedConditions.visibilityOf(selectVendorJDB));
		wait.until(ExpectedConditions.elementToBeClickable(selectVendorJDB));
		String selectVendorJDBText = selectVendorJDB.getText();

		if (!selectVendorJDB.isSelected()) {
			selectVendorJDB.click();
			cR.getExtentTest().info("Clicked " + selectVendorJDBText);
			Thread.sleep(1500);
		} else if (selectVendorJDB.isSelected()) {
			cR.getExtentTest().info(selectVendorJDBText + " already selected");
		}
	}
}