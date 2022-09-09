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
import functionsFE.SelectLiveCasinoGame;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStore;

public class OpenLiveCasinoGameTest extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	LogoutFE lOFE = new LogoutFE();
	SelectLiveCasinoGame sLCG = new SelectLiveCasinoGame();

	private static String nameOfReport = "OpenLiveCasinoGameTest";

	@BeforeClass(groups = { "Start", "liveCasino" })
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void startReportAndSetProperty(String platformName, String browserName, String javaVersion, String automationAuthor) {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = { "goToSite", "liveCasino" })
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "announcement", "liveCasino" })
	public void announcement() throws FailedLoginException, InterruptedException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login", "liveCasino" })
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

	@Test(priority = 3, groups = { "liveCasinoGame", "liveCasino" })
	@Parameters({ "vendorLiveCasinoMaster", "vendorLiveCasinoAG", "vendorLiveCasinoBG", "vendorLiveCasinoBBIN", "vendorLiveCasinoDG", "vendorLiveCasinoAllbet", "vendorLiveCasinoEBET" })
	public void liveCasinoGame(String vendorLiveCasinoMaster, String vendorLiveCasinoAG, String vendorLiveCasinoBG, String vendorLiveCasinoBBIN, String vendorLiveCasinoDG, String vendorLiveCasinoAllbet, String vendorLiveCasinoEBET) throws FailedLoginException, InterruptedException {
		cR.createTest("liveCasinoGame");
		sLCG.selectLiveCasino();
		if (vendorLiveCasinoMaster.equalsIgnoreCase("AG")) {
			sLCG.selectVendor(vendorLiveCasinoAG);
		} else if (vendorLiveCasinoMaster.equalsIgnoreCase("BG")) {
			sLCG.selectVendor(vendorLiveCasinoBG);
		} else if (vendorLiveCasinoMaster.equalsIgnoreCase("BBIN")) {
			sLCG.selectVendor(vendorLiveCasinoBBIN);
		}else if (vendorLiveCasinoMaster.equalsIgnoreCase("DG")) {
			sLCG.selectVendor(vendorLiveCasinoDG);
		}else if (vendorLiveCasinoMaster.equalsIgnoreCase("Allbet")) {
			sLCG.selectVendor(vendorLiveCasinoAllbet);
		}else if (vendorLiveCasinoMaster.equalsIgnoreCase("EBET")) {
			sLCG.selectVendor(vendorLiveCasinoEBET);
		}
	}

	@Test(priority = 4, groups = { "switchToLiveCasinoWindow", "liveCasino" })
	public void switchToLiveCasinoWindow() throws InterruptedException {
		cR.createTest("switchToLiveCasinoWindow");
		sLCG.switchWindow();
	}

	@AfterMethod(groups = { "liveCasino" })
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass(groups = { "liveCasino" })
	public void stopDriver() throws InterruptedException {
		Thread.sleep(500);
		cR.flushTest();
	}
}
