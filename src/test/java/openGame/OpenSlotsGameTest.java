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
	@Parameters({ "vendorSlotsMaster", "vendorSlotsBBIN", "vendorSlotsHC", "vendorSlotsPTTCG", "vendorSlotsPP", "vendorSlotsMG", "vendorSlotsCQ9", "vendorSlotsJDB" })
	public void slotsGame(String vendorSlotsMaster, String vendorSlotsBBIN, String vendorSlotsHC, String vendorSlotsPTTCG, String vendorSlotsPP, String vendorSlotsMG, String vendorSlotsCQ9, String vendorSlotsJDB) throws FailedLoginException, InterruptedException {
		cR.createTest("slotsGame");
		sSG.selectSlots();
		if (vendorSlotsMaster.equalsIgnoreCase("AG")) {
			sSG.selectMainVendor();
		}  else if (vendorSlotsMaster.equalsIgnoreCase("BBIN")) {
			sSG.selectOtherVendor(vendorSlotsBBIN);
		} else if (vendorSlotsMaster.equalsIgnoreCase("HC")) {
			sSG.selectOtherVendor(vendorSlotsHC);
		} else if (vendorSlotsMaster.equalsIgnoreCase("PTTCG")) {
			sSG.selectOtherVendor(vendorSlotsPTTCG);
		} else if (vendorSlotsMaster.equalsIgnoreCase("PP")) {
			sSG.selectOtherVendor(vendorSlotsPP);
		} else if (vendorSlotsMaster.equalsIgnoreCase("MG")) {
			sSG.selectOtherVendor(vendorSlotsMG);
		} else if (vendorSlotsMaster.equalsIgnoreCase("CQ9")) {
			sSG.selectOtherVendor(vendorSlotsCQ9);
		} else if (vendorSlotsMaster.equalsIgnoreCase("JDB")) {
			sSG.selectOtherVendor(vendorSlotsJDB);
		}
	}

	@Test(priority = 4, groups = { "selectGame", "slots" })
	@Parameters({ "vendorSlotsMaster", "gameNameSlotsAG", "gameNameSlotsBBIN", "gameNameSlotsHC", "gameNameSlotsPTTCG", "gameNameSlotsPP", "gameNameSlotsMG", "gameNameSlotsCQ9", "gameNameSlotsJDB" })
	public void selectGameSlots(String vendorSlotsMaster, String gameNameSlotsAG, String gameNameSlotsBBIN, String gameNameSlotsHC, String gameNameSlotsPTTCG, String gameNameSlotsPP, String gameNameSlotsMG, String gameNameSlotsCQ9, String gameNameSlotsJDB) throws FailedLoginException, InterruptedException {
		cR.createTest("selectGameSlots");
		if (vendorSlotsMaster.equalsIgnoreCase("AG")) {
			sSG.selectGameInVendor(gameNameSlotsAG);
		} else if (vendorSlotsMaster.equalsIgnoreCase("BBIN")) {
			sSG.selectGameInVendor(gameNameSlotsBBIN);
		} else if (vendorSlotsMaster.equalsIgnoreCase("HC")) {
			sSG.selectGameInVendor(gameNameSlotsHC);
		} else if (vendorSlotsMaster.equalsIgnoreCase("PTTCG")) {
			sSG.selectGameInVendor(gameNameSlotsPTTCG);
		} else if (vendorSlotsMaster.equalsIgnoreCase("PP")) {
			sSG.selectGameInVendor(gameNameSlotsPP);
		} else if (vendorSlotsMaster.equalsIgnoreCase("MG")) {
			sSG.selectGameInVendor(gameNameSlotsMG);
		} else if (vendorSlotsMaster.equalsIgnoreCase("CQ9")) {
			sSG.selectGameInVendor(gameNameSlotsCQ9);
		} else if (vendorSlotsMaster.equalsIgnoreCase("JDB")) {
			sSG.selectGameInVendor(gameNameSlotsJDB);
		}
	}

	@Test(priority = 5, groups = { "switchToGameSlotsWindwo", "slots" })
	public void switchToGameSlotsWindwo() throws FailedLoginException, InterruptedException {
		cR.createTest("switchToGameSlotsWindwo");
		sSG.switchWindow();
	}

	@AfterMethod(groups = { "slots" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "slots" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(500);
		cR.flushTest();
	}
}
