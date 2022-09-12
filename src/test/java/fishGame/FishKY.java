package fishGame;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import functionsFE.Announcement;
import functionsFE.LoginFE;
import functionsFE.LogoutFE;
import functionsFE.SelectGameCategoryVendorAndGame;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStore;

public class FishKY extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectGameCategoryVendorAndGame sGCVAG = new SelectGameCategoryVendorAndGame();

	private static String nameOfReport = "FishKY";

	@BeforeClass(groups = { "Start", "KY" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "KY" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "KY" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "KY" })
	@Parameters({ "userIDFE" })
	public void login(String userIDFE) throws FailedLoginException, InterruptedException {
		cR.createTest("login");
		lFE.loginOptionButton();
		lFE.setUserID(userIDFE);
		lFE.setPassword(passwordFE);
		lFE.setCaptcha(captchaFE);
		lFE.selectLoginButton();
		lFE.verifyLogIn(userIDFE);
	}

	@Test(priority = 3, groups = { "KY" })
	@Parameters({ "fishVendorKY" })
	public void selectFishVendor(String fishVendorKY) throws FailedLoginException, InterruptedException {
		cR.createTest("selectSlotsVendor");
		sGCVAG.hoverGameCategory("3");
		sGCVAG.selectGameVendor(fishVendorKY);
	}

	@Test(priority = 4, groups = { "KY" })
	@Parameters({ "KY1", "KY2", "KY3", "time" })
	public void selectFishGame(String KY1, String KY2, String KY3, int time) throws FailedLoginException, InterruptedException {
		cR.createTest("selectSlotsGame");
		sGCVAG.selectGameFromFishList(KY1, KY2, KY3, time);
	}

	@AfterMethod(groups = { "KY" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "KY" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(1000);
		cR.flushTest();
		bDriver.stopDriver();
	}
}
