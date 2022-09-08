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
import functionsFE.SelectPokerGame;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStore;

public class OpenPokerGameTest extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectPokerGame sSG = new SelectPokerGame();

	private static String nameOfReport = "OpenPokerGameTest";

	@BeforeClass(groups = { "Start", "poker" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "goToSite", "poker" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "announcement", "poker" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "poker" })
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

	@Test(priority = 3, groups = { "pokerGame", "poker" })
	@Parameters({ "vendorPokerMaster", "vendorPokerFG" })
	public void pokerGame(String vendorPokerMaster, String vendorPokerFG) throws FailedLoginException, InterruptedException {
		cR.createTest("pokerGame");
		sSG.selectPoker();
		if (vendorPokerMaster.equalsIgnoreCase("FG")) {
			sSG.selectVendor(vendorPokerFG);
		}
	}

	@Test(priority = 4, groups = { "selectGamePoker", "poker" })
	@Parameters({ "vendorPokerMaster", "gameNamePokerFG" })
	public void selectGamePoker(String vendorPokerMaster, String gameNamePokerFG) throws FailedLoginException, InterruptedException {
		cR.createTest("selectGamePoker");
		if (vendorPokerMaster.equalsIgnoreCase("FG")) {
			sSG.selectGameInVendor(gameNamePokerFG);
		}
		Thread.sleep(1500);
	}

	@AfterMethod(groups = { "poker" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "poker" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(1500);
		cR.flushTest();
//		bDriver.stopDriver();
	}
}
