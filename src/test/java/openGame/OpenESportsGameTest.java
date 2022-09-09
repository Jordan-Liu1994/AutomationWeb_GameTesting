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
import functionsFE.SelectESportsGame;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStore;

public class OpenESportsGameTest extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectESportsGame sESG = new SelectESportsGame();

	private static String nameOfReport = "OpenESportsGameTest";

	@BeforeClass(groups = { "Start", "eSports" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "goToSite", "eSports" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "announcement", "eSports" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "eSports" })
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

	@Test(priority = 3, groups = { "eSportsGame", "eSports" })
	@Parameters({ "vendorESportsMaster", "vendorESportsIM", "vendorESportsIBC", "vendorESportsIA" })
	public void eSportsGame(String vendorESportsMaster, String vendorESportsIM, String vendorESportsIBC, String vendorESportsIA) throws FailedLoginException, InterruptedException {
		cR.createTest("eSportsGame");
		sESG.selectESports();
		if (vendorESportsMaster.equals("IM")) {
			sESG.selectVendor(vendorESportsIM);
		} else if (vendorESportsMaster.equals("IBC")) {
			sESG.selectVendor(vendorESportsIBC);
		} else if (vendorESportsMaster.equals("IA")) {
			sESG.selectVendor(vendorESportsIA);
		}
	}

	@Test(priority = 4, groups = { "switchToGameESportsWindow", "eSports" })
	public void switchToGameESportsWindow() throws InterruptedException {
		cR.createTest("switchToGameESportsWindow");
		sESG.switchWindow();
	}

	@AfterMethod(groups = { "eSports" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "eSports" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(500);
		cR.flushTest();
	}
}
