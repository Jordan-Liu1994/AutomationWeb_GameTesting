package openLiveGame;

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

public class OpenLiveGame1 extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectGameCategoryVendorAndGame sGCVAG = new SelectGameCategoryVendorAndGame();

	private static String nameOfReport = "OpenLiveGame1";
	private static int live = 1;
	
	@BeforeClass(groups = { "Start", "Live" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "Live" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite" + live);
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "Live" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement" + live);
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "Live" })
	@Parameters({ "userIDFE" })
	public void login(String userIDFE) throws FailedLoginException, InterruptedException {
		cR.createTest("login" + live);
		lFE.loginOptionButton();
		lFE.setUserID(userIDFE);
		lFE.setPassword(passwordFE);
		lFE.setCaptcha(captchaFE);
		lFE.selectLoginButton();
		lFE.verifyLogIn(userIDFE);
	}

	@Test(priority = 3, groups = { "Live" })
	@Parameters({ "gameVendorXpathNumber" })
	public void selectLiveVendor(String gameVendorXpathNumber) throws FailedLoginException, InterruptedException {
		cR.createTest("selectLiveVendor" + live);
		sGCVAG.hoverGameCategory("1");
		sGCVAG.selectGameVendor(gameVendorXpathNumber);
		sGCVAG.switchWindow();
	}

	@AfterMethod(groups = { "Live" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "Live" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(500);
		cR.flushTest();
		bDriver.stopDriver();
	}
}
