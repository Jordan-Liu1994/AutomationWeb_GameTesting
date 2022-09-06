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
import utilities.TakeScreenShot;
import utilities.VariablesStore;

public class OpenGameTest extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectSlotsGame sSG = new SelectSlotsGame();

	private static String nameOfReport = "OpenGameTest";

	@BeforeClass(groups = { "Start" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "goToSite" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "announcement" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login" })
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

	@Test(priority = 3, groups = { "slotsGame" })
	@Parameters({ "vendor" })
	public void slotsGame(String vendor) throws FailedLoginException, InterruptedException {
		cR.createTest("slotsGame");
		sSG.selectSlots();
		if (vendor.equalsIgnoreCase("AG")) {
			sSG.selectVendorAG();
		} else if (vendor.equalsIgnoreCase("BBIN")) {
			sSG.selectVendorBBIN();
		}else if (vendor.equalsIgnoreCase("HC")) {
			sSG.selectVendorHC();
		}else if (vendor.equalsIgnoreCase("PTTCG")) {
			sSG.selectVendorPTTCG();
		}else if (vendor.equalsIgnoreCase("PP")) {
			sSG.selectVendorPP();
		}else if (vendor.equalsIgnoreCase("MG")) {
			sSG.selectVendorMG();
		}else if (vendor.equalsIgnoreCase("CQ9")) {
			sSG.selectVendorCQ9();
		}else if (vendor.equalsIgnoreCase("JDB")) {
			sSG.selectVendorJDB();
		}
	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		String resultOfCaseStatus = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			cR.getExtentTest().pass(step + resultOfCaseStatus + " is passed!");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			cR.getExtentTest().skip(step + resultOfCaseStatus + " is skipped!");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			takeSS.getFailScreenShot(resultOfCaseStatus);
			cR.getExtentTest().fail(step + resultOfCaseStatus + " is failed!");
			cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + resultOfCaseStatus + failSS, resultOfCaseStatus);
		}
	}

	@AfterClass
	public void stopDriver() throws InterruptedException {
		Thread.sleep(1000);
		cR.flushTest();
		bDriver.stopDriver();
	}
}
