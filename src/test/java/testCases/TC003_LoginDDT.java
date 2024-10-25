package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccLogoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(priority=3, dataProvider="LoginData", dataProviderClass = DataProviders.class, groups="Datadriven") //getting data provider from different class
	public void verify_LoginDDT(String email, String pwd, String result) throws InterruptedException {
		
	 logger.info("Entering verify_LoginDDT method.");
        
    // Ensure driver is initialized
    if (driver == null) {
        logger.error("WebDriver is not initialized.");
        throw new IllegalStateException("WebDriver is not initialized.");
    }	
	
	logger.info("**************starting TC003_LoginTest **************");
	try {
		
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("**************clicked on MyAccount link**************");
		hp.clickLogin();
		logger.info("**************HomePage - clicked on Login link**************");
		Thread.sleep(5000);
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		logger.info("**************Login Page - clicked on Login button**************");
		
		Thread.sleep(5000);
		//MyAccountPage
		MyAccountPage maccpg = new MyAccountPage(driver);
		Boolean targetPage = maccpg.IsmyAccountPageExists();
		Thread.sleep(5000);
		
		/* logic to be implemented
		 * Data is valid-login success-test pass-logout
		 * data is valid-login unsuccessful-test fail
		 * Data is invalid-login success-test fail-logout
		 * data is invalid-login unsuccessful-test pass
		 */
		AccLogoutPage acclogoutpg = new AccLogoutPage(driver);
		
		if(result.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				Assert.assertTrue(true);
				maccpg.clickLogout();	
				Thread.sleep(5000);
				System.out.println("Valid login Successful as expected");
				acclogoutpg.clickContinue();
				Thread.sleep(5000);
			}
			else {
				System.out.println("Valid login UnSuccessful as not expected");
				lp.clearEmail();
				lp.clearPassword();
				Thread.sleep(5000);
				Assert.assertTrue(false);
			}
		}
		
		if(result.equalsIgnoreCase("InValid")) {
			if(targetPage==true) {				
				maccpg.clickLogout();
				Thread.sleep(5000);
				acclogoutpg.clickContinue();
				Thread.sleep(5000);
				System.out.println("Invalid login successful as not expected");
				Assert.assertTrue(false);				
			}
			else {
				System.out.println("Invalid login Unsuccessful as expected");
				lp.clearEmail();
				lp.clearPassword();
				Thread.sleep(5000);
				Assert.assertTrue(true);
			}
		}	
		logger.info("**************Asserted if MyAccount Page login Successful**************");
		
		
	}catch (Exception e) {
		Assert.fail();
	}
	logger.info("**************Finished TC003_LoginTest**************");
}
}
