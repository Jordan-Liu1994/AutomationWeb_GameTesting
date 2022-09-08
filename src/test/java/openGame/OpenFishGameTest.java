package openGame;

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
import functionsFE.SelectFishGame;
import functionsFE.SelectSlotsGame;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStore;

public class OpenFishGameTest extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectFishGame sFG = new SelectFishGame();

	private static String nameOfReport = "OpenFishGameTest";

	@BeforeClass(groups = { "Start", "fish" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "goToSite", "fish" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "announcement", "fish" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "fish" })
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

	@Test(priority = 3, groups = { "fishGame", "fish" })
	@Parameters({ "vendorFishMaster", "vendorFishBBIN", "vendorFishPP", "vendorFishMainJDB", "vendorFishMainMT", "vendorFishMainFG" })
	public void fishGame(String vendorFishMaster, String vendorFishBBIN, String vendorFishPP, String vendorFishMainJDB, String vendorFishMainMT, String vendorFishMainFG) throws FailedLoginException, InterruptedException {
		cR.createTest("fishGame");
		sFG.selectFish();
		if (vendorFishMaster.equals("BBIN")) {
			sFG.selectMainVendor();
		} else if (vendorFishMaster.equals("PP")) {
			sFG.selectOtherVendor(vendorFishPP);
		} else if (vendorFishMaster.equals("JDB")) {
			sFG.selectOtherVendor(vendorFishMainJDB);
		} else if (vendorFishMaster.equals("MT")) {
			sFG.selectOtherVendor(vendorFishMainMT);
		} else if (vendorFishMaster.equals("FG")) {
			sFG.selectOtherVendor(vendorFishMainFG);
		}
	}

	@Test(priority = 4, groups = { "selectGameFish", "fish" })
	@Parameters({ "vendorFishMaster", "gameNameFishBBIN", "gameNameFishPP", "gameNameFishJDB", "gameNameFishMT", "gameNameFishFG" })
	public void selectGameFish(String vendorFishMaster, String gameNameFishBBIN, String gameNameFishPP, String gameNameFishJDB, String gameNameFishMT, String gameNameFishFG) throws FailedLoginException, InterruptedException {
		cR.createTest("selectGameFish");
		if (vendorFishMaster.equals("BBIN")) {
			sFG.selectGameInVendor(gameNameFishBBIN);
		} else if (vendorFishMaster.equals("PP")) {
			sFG.selectGameInVendor(gameNameFishPP);
		} else if (vendorFishMaster.equals("JDB")) {
			sFG.selectGameInVendor(gameNameFishJDB);
		} else if (vendorFishMaster.equals("MT")) {
			sFG.selectGameInVendor(gameNameFishMT);
		} else if (vendorFishMaster.equals("FG")) {
			sFG.selectGameInVendor(gameNameFishFG);
		}
		Thread.sleep(1500);
	}

	@AfterMethod(groups = { "fish" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "fish" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(1500);
		cR.flushTest();
//		bDriver.stopDriver();
	}
}
