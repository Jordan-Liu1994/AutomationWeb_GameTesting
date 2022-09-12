package functionsFE;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.VariablesStore;

public class LoginFE extends VariablesStore {

	CreateReport cR = new CreateReport();

	WebDriverWait wait;
	String fail;
	String skip;

	public void loginOptionButton() throws FailedLoginException {
		fail = "loginOptionButton failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement loginOptionButton = bDriver.getDriver().findElement(By.xpath("//a[@class='login']"));
		wait.until(ExpectedConditions.elementToBeClickable(loginOptionButton));
		String loginOptionButtonText = loginOptionButton.getText();

		if (loginOptionButton.isEnabled()) {
			loginOptionButton.click();
			cR.getExtentTest().info("Clicked " + loginOptionButtonText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void setUserID(String userID) throws FailedLoginException {
		fail = "setUserID failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement setUserID = bDriver.getDriver().findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(setUserID));
		String setUserIDText = setUserID.getAttribute(attribute);

		if (setUserID.isDisplayed()) {
			setUserID.clear();
			setUserID.sendKeys(userID);
			cR.getExtentTest().info(userID + keyIn + setUserIDText);
		} else {
			cR.getExtentTest().warning(fail);
		}
	}

	public void setPassword(String password) throws FailedLoginException {
		fail = "setPassword failed";

		WebElement setPassword = bDriver.getDriver().findElement(By.id("password"));
		String setPasswordText = setPassword.getAttribute(attribute);

		if (setPassword.isDisplayed()) {
			setPassword.clear();
			setPassword.sendKeys(password);
			cR.getExtentTest().info(password + keyIn + setPasswordText);
		} else {
			cR.getExtentTest().warning(fail);
		}
	}

	public void setCaptcha(String captcha) throws FailedLoginException {
		fail = "setCaptcha failed";
		skip = "setCaptcha skipped";
		
		try {
			WebElement setCaptcha = bDriver.getDriver().findElement(By.id("captcha_code"));
			String setCaptcha_Text = setCaptcha.getAttribute(attribute);

			if (setCaptcha.isDisplayed()) {
				setCaptcha.clear();
				setCaptcha.sendKeys(captcha);
				cR.getExtentTest().info(captcha + keyIn + setCaptcha_Text);
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}

	public void selectLoginButton() throws FailedLoginException, InterruptedException {
		fail = "selectLoginButton failed";

		WebElement selectLoginButton = bDriver.getDriver().findElement(By.id("login_popup_btn"));
		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String selectLoginButtonText = selectLoginButton.getText();

		if (selectLoginButton.isEnabled()) {
			selectLoginButton.click();
			cR.getExtentTest().info("Clicked " + selectLoginButtonText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void verifyLogIn(String userID) throws FailedLoginException, InterruptedException {
		fail = "verifyLogIn failed";

		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		wait.until(ExpectedConditions.visibilityOf(userIDName));

		if (userIDName.isDisplayed()) {
			cR.getExtentTest().info("Account " + userID + " verified");
		} else {
			cR.getExtentTest().warning(fail);
		}
	}
}