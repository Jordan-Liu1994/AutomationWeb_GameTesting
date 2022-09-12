package openSportsGame;

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

public class OpenSportsGame1 extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectGameCategoryVendorAndGame sGCVAG = new SelectGameCategoryVendorAndGame();

	private static String nameOfReport = "OpenSportsGame1";
	private static int sports = 1;
	
	@BeforeClass(groups = { "Start", "Sports" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "Sports" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite" + sports);
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "Sports" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement" + sports);
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "Sports" })
	@Parameters({ "userIDFE" })
	public void login(String userIDFE) throws FailedLoginException, InterruptedException {
		cR.createTest("login" + sports);
		lFE.loginOptionButton();
		lFE.setUserID(userIDFE);
		lFE.setPassword(passwordFE);
		lFE.setCaptcha(captchaFE);
		lFE.selectLoginButton();
		lFE.verifyLogIn(userIDFE);
	}

	@Test(priority = 3, groups = { "Sports" })
	@Parameters({ "gameVendorXpathNumber" })
	public void selectSportsVendor(String gameVendorXpathNumber) throws FailedLoginException, InterruptedException {
		cR.createTest("selectSportsVendor" + sports);
		sGCVAG.hoverGameCategory("4");
		sGCVAG.selectGameVendor(gameVendorXpathNumber);
		sGCVAG.switchWindow();
	}

	@AfterMethod(groups = { "Sports" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "Sports" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(500);
		cR.flushTest();
		bDriver.stopDriver();
	}
}
