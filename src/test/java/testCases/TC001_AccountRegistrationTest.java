package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{	
	
	@Test(priority=2, groups={"Regression","Master"})
	//@Test(priority = 1, groups="Regression")
	public void verify_account_registration() throws InterruptedException {
		
		logger.info("Entering verify_account_registration method.");
	        
        // Ensure driver is initialized
        if (driver == null) {
            logger.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        
		logger.info("**************starting TC001_AccountRegistrationTest **************");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("**************clicked on myaccount link**************");
			
			hp.clickRegister();
			logger.info("**************clicked on Register link **************");
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			Thread.sleep(2000);
			//generate random data
			logger.debug("**************Providing customer details **************");
			regpage.setFirstName(randomString(8).toUpperCase());
			regpage.setLastName(randomString(8).toUpperCase());
			regpage.setEmail(randomString(6) + "@gmail.com");
			regpage.setPhone(randomNumber(10));
			String password = randomAlphanumeric();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			regpage.setPrivacyPolicy();
			Thread.sleep(2000);
			regpage.clickContinue();
			Thread.sleep(2000);
			
			logger.info("**************Validating expected message **************");
			
			String confmsg = regpage.getConfirmationMessage();
			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}else {
				logger.error("Test failed..");
				logger.debug("Debug Logs");
				Assert.assertTrue(false);
			}				
		}
		catch (Exception e)
		{
			//logger.error("Test failed..");
			//logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("**************Finished TC001_AccountRegistrationTest **************");
	}	
}

