package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScreenShot extends VariablesStore {

	private String userDir = System.getProperty("user.dir");
	private String pathOfSS = userDir + ".\\src\\test\\resources\\screenshots\\";

	public void getPassScreenShot(String fileName) {
		File screenShot = ((TakesScreenshot) bDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(pathOfSS + fileName + passSS));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getFailScreenShot(String fileName) {
		File screenShot = ((TakesScreenshot) bDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(pathOfSS + fileName + failSS));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String screenShotPathExtent() {
		return pathOfSS;
	}
}