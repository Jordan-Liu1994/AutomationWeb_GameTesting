package functionsFE;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CreateReport;
import utilities.VariablesStore;

public class Announcement extends VariablesStore {

	CreateReport cR = new CreateReport();
	
	WebDriverWait wait;
	WebElement noShowAgainTodayButton;
	WebElement closeAnnouncementButton;
	String skip = "Skipped";
	
	public void closeAnnouncement() throws FailedLoginException, InterruptedException {
		try {
			for (int number = 0; number <= 10; number++) {
				wait = new WebDriverWait(bDriver.getDriver(), 15);
				noShowAgainTodayButton = bDriver.getDriver().findElement(By.xpath("//label[@for='aip_" + number + "']//div//div//span"));
				Thread.sleep(250);
				wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
				wait.until(ExpectedConditions.elementToBeClickable(noShowAgainTodayButton));
				if (noShowAgainTodayButton.isDisplayed()) {
					String buttonText = noShowAgainTodayButton.getText();
					noShowAgainTodayButton.click();
					cR.getExtentTest().info("Clicked " + buttonText);

					Thread.sleep(250);
					closeAnnouncementButton = bDriver.getDriver().findElement(By.xpath("//div[@class='modal fade web_announcement_image_popout show']//button[@aria-label='Close']"));
					wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
					wait.until(ExpectedConditions.elementToBeClickable(closeAnnouncementButton));
					if (closeAnnouncementButton.isDisplayed()) {
						closeAnnouncementButton.click();
						cR.getExtentTest().info("Clicked closeAnnouncementButton");
					}
				}
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}

	public void closeAnnouncementOverview() throws FailedLoginException, InterruptedException {
		try {
			wait = new WebDriverWait(bDriver.getDriver(), 15);
			noShowAgainTodayButton = bDriver.getDriver().findElement(By.xpath("//label[@for='normal_announcement_radio']//div[@class='checkbox']//div//span"));
			Thread.sleep(250);
			wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
			wait.until(ExpectedConditions.elementToBeClickable(noShowAgainTodayButton));
			if (noShowAgainTodayButton.isDisplayed()) {
				String buttonText = noShowAgainTodayButton.getText();
				noShowAgainTodayButton.click();
				cR.getExtentTest().info("Clicked " + buttonText);

				Thread.sleep(250);
				closeAnnouncementButton = bDriver.getDriver().findElement(By.xpath("//div[@class='modal fade large wna_style show']//button[@aria-label='Close']"));
				wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
				wait.until(ExpectedConditions.elementToBeClickable(closeAnnouncementButton));
				if (closeAnnouncementButton.isDisplayed()) {
					closeAnnouncementButton.click();
					cR.getExtentTest().info("Clicked closeAnnouncementOverview");
				}
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}
	
	public void closeAnnouncementOverviewOldFramework() throws FailedLoginException, InterruptedException {
		try {
			wait = new WebDriverWait(bDriver.getDriver(), 15);
			noShowAgainTodayButton = bDriver.getDriver().findElement(By.xpath("//label[@for='normal_announcement_radio']"));
			Thread.sleep(250);
			wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
			wait.until(ExpectedConditions.elementToBeClickable(noShowAgainTodayButton));
			if (noShowAgainTodayButton.isEnabled()) {
				String buttonText = noShowAgainTodayButton.getText();
				Actions builder = new Actions(bDriver.getDriver());
				Action act = builder.moveToElement(noShowAgainTodayButton).build();
				act.perform();
				Thread.sleep(250);
				noShowAgainTodayButton.click();
				cR.getExtentTest().info("Clicked " + buttonText);

				Thread.sleep(250);
				closeAnnouncementButton = bDriver.getDriver().findElement(By.xpath("(//button[@aria-label='Close'])[1]"));
				wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
				wait.until(ExpectedConditions.elementToBeClickable(closeAnnouncementButton));
				if (closeAnnouncementButton.isDisplayed()) {
					closeAnnouncementButton.click();
					cR.getExtentTest().info("Clicked closeAnnouncementOverviewOldFramework");
				}
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}
}
