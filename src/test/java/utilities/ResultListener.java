package utilities;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ResultListener extends VariablesStore {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cR = new CreateReport();
	
	public void logCaseStatus(ITestResult result) {
		String resultOfCaseStatus = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			takeSS.getPassScreenShot(resultOfCaseStatus);
			cR.getExtentTest().pass(step + resultOfCaseStatus + " is passed!");
			cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + resultOfCaseStatus + passSS, resultOfCaseStatus);
		}
		if (result.getStatus() == ITestResult.SKIP) {
			cR.getExtentTest().skip(step + resultOfCaseStatus + " is skipped!");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			takeSS.getFailScreenShot(resultOfCaseStatus);
			cR.getExtentTest().fail(step + resultOfCaseStatus + " is failed!");
			cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + resultOfCaseStatus + failSS, resultOfCaseStatus);
		}
	}
}