package openGame;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import functionsFE.Announcement;
import functionsFE.LoginFE;
import functionsFE.LogoutFE;
import functionsFE.SelectSlotsGame;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStore;

public class OpenSlotsGameTest extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectSlotsGame sSG = new SelectSlotsGame();

	private static String nameOfReport = "OpenSlotsGameTest";

	@BeforeClass(groups = { "Start", "slots" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "goToSite", "slots" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "announcement", "slots" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "slots" })
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

	@Test(priority = 3, groups = { "slotsGame", "slots" })
	@Parameters({ "vendorSlots", "vendorSlotsMain" })
	public void slotsGame(String vendorSlots, String vendorSlotsMain) throws FailedLoginException, InterruptedException {
		cR.createTest("slotsGame");
		sSG.selectSlots();
		if (vendorSlotsMain.equalsIgnoreCase("yes")) {
			sSG.selectMainVendor();
		} else {
			sSG.selectOtherVendor(vendorSlots);
		}
	}

	@Test(priority = 4, groups = { "selectGame", "slots" })
	@Parameters({ "gameNameSlots", "gameNameSlotsMain", "vendorSlotsMain" })
	public void selectGameSlots(String gameNameSlots, String gameNameSlotsMain, String vendorSlotsMain) throws FailedLoginException, InterruptedException {
		cR.createTest("selectGameSlots");
		if (vendorSlotsMain.equals("yes")) {
			sSG.selectGameInVendor(gameNameSlotsMain);
		} else {
			sSG.selectGameInVendor(gameNameSlots);
		}
		Thread.sleep(1500);
	}

	@AfterMethod(groups = { "slots" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "slots" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(1500);
		cR.flushTest();
//		bDriver.stopDriver();
	}
}
