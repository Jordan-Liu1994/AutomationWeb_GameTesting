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
import functionsFE.SelectSportsGame;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStore;

public class OpenSportsGameTest extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectSportsGame sSG = new SelectSportsGame();

	private static String nameOfReport = "OpenPokerGameTest";

	@BeforeClass(groups = { "Start", "sports" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "goToSite", "sports" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "announcement", "sports" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "sports" })
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

	@Test(priority = 3, groups = { "sportsGame", "sports" })
	@Parameters({ "vendorSportsMaster", "vendorSportsOBG", "vendorSportsIM", "vendorSportsIBC", "vendorSportsUG" })
	public void sportsGame(String vendorSportsMaster, String vendorSportsOBG, String vendorSportsIM, String vendorSportsIBC, String vendorSportsUG) throws FailedLoginException, InterruptedException {
		cR.createTest("sportsGame");
		sSG.selectSports();
		if (vendorSportsMaster.equalsIgnoreCase("OBG")) {
			sSG.selectVendor(vendorSportsOBG);
		} else if (vendorSportsMaster.equalsIgnoreCase("IM")) {
			sSG.selectVendor(vendorSportsIM);
		} else if (vendorSportsMaster.equalsIgnoreCase("IBC")) {
			sSG.selectVendor(vendorSportsIBC);
		} else if (vendorSportsMaster.equalsIgnoreCase("UG")) {
			sSG.selectVendor(vendorSportsUG);
		}
	}

	@Test(priority = 4, groups = { "selectGameSports", "sports" })
	public void selectGameSports() throws FailedLoginException, InterruptedException {
		cR.createTest("selectGameSports");
		sSG.selectGameInVendor();
		Thread.sleep(1500);
	}

	@AfterMethod(groups = { "sports" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "sports" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(1500);
		cR.flushTest();
//		bDriver.stopDriver();
	}
}