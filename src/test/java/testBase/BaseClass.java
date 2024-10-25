package testBase;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public static WebDriver driver;
protected Logger logger; //log4j2 //import org.apache.logging.log4j.Logger;//import org.apache.logging.log4j.LogManager;
public Properties p;
		
	//Setup method and teardown method, we need for every test case, so moved it to a common class called BaseClass.java for reusability
	@SuppressWarnings("deprecation")
	@BeforeClass(groups={"Sanity", "Regression","Master", "Datadriven"})	
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException, InterruptedException {
			
		//loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		System.out.println("Environment chosen1: " + p.getProperty("execution_env"));
		//set up application logging using log4j2
		logger = LogManager.getLogger(this.getClass()); //import org.apache.logging.log4j.Logger;//import org.apache.logging.log4j.LogManager;
		logger.info("BaseClass setup is starting.");
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			logger.info("Execution environment set is Remote.");
			DesiredCapabilities capabilities = new DesiredCapabilities();	
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
				logger.info("OS Platform is set to Windows.");
			}
			else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
				logger.info("OS Platform is set to LINUX.");
			}
			else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
				logger.info("OS Platform is set to MAC.");
			}
			else {
				System.out.println("No matching os");
				return;
			}			
			
			//browser				  
			switch(br.toLowerCase()) {
			  case "chrome":
				  capabilities.setBrowserName("chrome");
				  logger.info("Browser is set to Chrome.");
				  break;
			  //exits from the switch stmt and continues executing rest of the statements
			  case "edge": 
				  capabilities.setBrowserName("MicrosoftEdge");
				  logger.info("Browser is set to Edge.");
				  break; 
			  case "firefox": 
				  capabilities.setBrowserName("firefox");
				  logger.info("Browser is set to Firefox.");
				  break;		  
			  default: 
				  System.out.println("No matching browser...");
				  return; // exits from the execution of the testcase 				  
			}		
		    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);		  
		}
		
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			logger.info("Execution environment set is local.");
			switch(br.toLowerCase())
			{ case "chrome":
				driver = new ChromeDriver();
				break;
			  case "edge": 
				  driver = new EdgeDriver();
				  break;
			  case "firefox": 
				  driver = new FirefoxDriver();
				  break;
			  default: 
				  System.out.println("Invalid browser name..."); 
				  return; // exits
			}			
		}
		
		System.out.println("Environment chosen: " + p.getProperty("execution_env"));		
		System.out.println("I have initialized the browser");		
		
		if (driver != null) {
		   driver.manage().deleteAllCookies();	
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   driver.get(p.getProperty("appURL1")); //reading URL from config.properties file and running on docker remotely
		   //driver.get(p.getProperty("appURL2")); //reading URL from config.properties file
		   //driver.get(p.getProperty("appURL3")); //running tests on docker use appURL3
		   driver.manage().window().maximize();            
		} else {
		   logger.error("Driver is null. Cannot proceed with launching the application.");
		}		
	}

	@AfterClass(groups={"Sanity","Regression","Master", "Datadriven"})
	public void teardown() {
		driver.quit();
	}
		
	public String randomString(int x) {	
		final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    final Random RANDOM = new Random();
		
		StringBuilder randomString = new StringBuilder(x);
        for (int i = 0; i < x; i++) {
            int index = RANDOM.nextInt(ALPHABET.length());
            randomString.append(ALPHABET.charAt(index));
        }
        System.out.println("random string: " + randomString.toString());
        return randomString.toString();		
	}
		
	public String randomNumber(int y) {	
		final String NUMBER = "1234567890";
	    final Random RANDOM = new Random();
		
		StringBuilder randomNumber = new StringBuilder(y);
        for (int i = 0; i < y; i++) {
            int index = RANDOM.nextInt(NUMBER.length());
            randomNumber.append(NUMBER.charAt(index));
        }
        System.out.println("random number string: " + randomNumber.toString());
        return randomNumber.toString();		
	}
		
	public String randomAlphanumeric() {
		String rstring = randomString(5);
		String rnumber = randomNumber(4);
		String randomalphanum = rstring + rnumber;
		System.out.println ("random alphanum password: " + randomalphanum);
		return randomalphanum;
	}
		
	public String captureScreen(String tname) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);			
		return targetFilePath;		
	}
}

