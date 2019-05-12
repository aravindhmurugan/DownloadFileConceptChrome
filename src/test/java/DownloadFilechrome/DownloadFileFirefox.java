package DownloadFilechrome;

import java.io.File;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DownloadFileFirefox {

	WebDriver driver;
	File folder;

	@BeforeMethod
	public void setUp() {

		folder = new File(UUID.randomUUID().toString());
		folder.mkdir();

		FirefoxProfile profile = new FirefoxProfile();
		
		profile.setPreference("browser.download.dir", folder.getAbsolutePath());
			
		profile.setPreference("browser.download.folderList", 2);
		
		profile.setPreference("browser.helperApps.neverAsk.saveToDIsk", "image/jpeg,application/pdf,application/octet-stream");
		
		profile.setPreference("pdfjs.disabled", true);
		
		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.geckodriver.driver", "E:/My Backups/geckodriver.exe");

	}

	@Test
	public void downloadfiletest() throws InterruptedException {

		driver.get("http://the-internet.herokuapp.com/download");

		driver.findElement(By.linkText("some-file.txt")).click();

		Thread.sleep(2000);

		File listoffiles[] = folder.listFiles();

		Assert.assertTrue(listoffiles.length > 0);

		for (File file : listoffiles) {

			Assert.assertTrue(file.length() > 0);
		}

	}

	@AfterMethod

	public void tearDown() {

		driver.quit();

		for (File file : folder.listFiles()) {

			file.delete();

		}

		folder.delete();

	}

}
