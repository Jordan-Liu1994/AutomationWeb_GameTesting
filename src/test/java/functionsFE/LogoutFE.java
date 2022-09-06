package functionsFE;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CreateReport;
import utilities.VariablesStore;

public class LogoutFE extends VariablesStore {

	CreateReport cR = new CreateReport();
	
	WebDriverWait wait;
	String fail;

	public void selectLogoutButton(String userID) throws FailedLoginException, InterruptedException {
		fail = "selectLogoutButton failed";

		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		if (userIDName.isDisplayed()) {
			Actions builder = new Actions(bDriver.getDriver());
			Action act = builder.moveToElement(userIDName).build();
			act.perform();
			cR.getExtentTest().info("Hovered over " + userID);
		} else {
			cR.getExtentTest().fail(fail);
		}

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement logoutButton = bDriver.getDriver().findElement(By.xpath("//button[@class='btn btn_nav_logout']"));
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
		String logoutButtonText = logoutButton.getText();

		Thread.sleep(250);
		if (logoutButton.isEnabled()) {
			logoutButton.click();
			cR.getExtentTest().info("Clicked " + logoutButtonText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void verifyLogout() throws FailedLoginException, InterruptedException {
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement loginOptionButton = bDriver.getDriver().findElement(By.id("header_login"));
		wait.until(ExpectedConditions.visibilityOf(loginOptionButton));
		String fail = "verifyLogout failed";

		if (loginOptionButton.isDisplayed()) {
			cR.getExtentTest().info("Logout success");
		} else {
			cR.getExtentTest().fail(fail);
		}
	}
}