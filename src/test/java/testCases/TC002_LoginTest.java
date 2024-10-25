package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(priority = 1, groups={"Sanity","Master"})
	public void verifyLogin() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		logger.info("Entering verify_login method.");
	        
        // Ensure driver is initialized
        if (driver == null) {
            logger.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized.");
        }
		logger.info("**************starting TC002_LoginTest **************");
		try {
			
			//HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("**************clicked on MyAccount link**************");
			hp.clickLogin();
			logger.info("**************HomePage - clicked on Login link**************");
			
			//LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			System.out.println("set email: " + p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			System.out.println("set password: " + p.getProperty("password"));
			Thread.sleep(1000);
			lp.clickLogin();
			logger.info("**************Login Page - clicked on Login button**************");
			
			
			Thread.sleep(1000);
			MyAccountPage maccpg = new MyAccountPage(driver);
			Thread.sleep(1000);
			System.out.println("I am here");
			Boolean targetPage = maccpg.IsmyAccountPageExists();
			Thread.sleep(2000);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
			
			System.out.println("maccp exists: " + targetPage );
			//Assert.assertEquals(targetPage, true, "Login Failed");
			Assert.assertTrue(targetPage);
			logger.info("**************Asserted if MyAccount Page exists**************");
			
		}catch (Exception e) {
			System.out.println("Verify login method caused an exception");
			Assert.fail();
		}
		logger.info("**************Finished TC002_LoginTest**************");
	}
	
	
}
