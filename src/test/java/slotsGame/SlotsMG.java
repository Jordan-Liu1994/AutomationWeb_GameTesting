package slotsGame;

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

public class SlotsMG extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectGameCategoryVendorAndGame sGCVAG = new SelectGameCategoryVendorAndGame();

	private static String nameOfReport = "OpenSlotsGameMG";

	@BeforeClass(groups = { "Start", "MG" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "MG" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "MG" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "MG" })
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

	@Test(priority = 3, groups = { "MG" })
	@Parameters({ "vendorMG" })
	public void selectSlotsVendor(String vendorMG) throws FailedLoginException, InterruptedException {
		cR.createTest("selectSlotsVendor");
		sGCVAG.hoverGameCategory("2");
		sGCVAG.selectGameVendor(vendorMG);
	}

	@Test(priority = 4, groups = { "MG" })
	@Parameters({ "MG1", "MG2", "MG3", "MG4", "MG5" })
	public void selectSlotsGame(String MG1, String MG2, String MG3, String MG4, String MG5) throws FailedLoginException, InterruptedException {
		cR.createTest("selectSlotsGame");
		sGCVAG.selectGameFromSlotsList(MG1, MG2, MG3, MG4, MG5);
	}

	@AfterMethod(groups = { "MG" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "MG" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(1000);
		cR.flushTest();
		bDriver.stopDriver();
	}
}
