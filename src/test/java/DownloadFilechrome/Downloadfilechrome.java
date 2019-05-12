	package DownloadFilechrome;

	import java.io.File;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

	public class Downloadfilechrome {
		
		WebDriver driver;
		File folder;
		
		@BeforeMethod
		public void setUp() {
			
			folder = new File(UUID.randomUUID().toString());
			folder.mkdir();			
			
			System.setProperty("webdriver.chrome.driver", "E:/My Backups/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			
			Map<String, Object> prefs = new HashMap<String, Object>();
			
			prefs.put("profile.default_content_setting.popups", 0);
			prefs.put("download.default_directory", folder.getAbsolutePath());
			
			options.setExperimentalOption("prefs", prefs);
			
			driver = new ChromeDriver(options);
			
		}
		
		@Test
		public void downloadfiletest() throws InterruptedException {
			
			driver.get("http://the-internet.herokuapp.com/download");
			
			driver.findElement(By.linkText("some-file.txt")).click();
			
			Thread.sleep(2000);
			
			File listoffiles[] = folder.listFiles();
			
			Assert.assertTrue(listoffiles.length>0);
			
			for(File file : listoffiles) {
				
				Assert.assertTrue(file.length()>0);
			}
			
		}
		
	
	  @AfterMethod 
	  
	  public void tearDown() { 
		  
		  driver.quit();
	  
	  for(File file: folder.listFiles()) {
		  
		  file.delete();
		  
	  } 
	  
	  folder.delete(); 
	  
	  }
	 		
	}


