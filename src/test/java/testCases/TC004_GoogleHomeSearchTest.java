package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.GoogleSearch;
import testBase.BaseClass;

public class TC004_GoogleHomeSearchTest extends BaseClass{
	
	@Test(priority=2, groups={"Regression","Master"})
	//@Test(priority = 1, groups="Regression")
	public void verify_google_search() throws InterruptedException {
		
		logger.info("Entering google search method.");
	        
        // Ensure driver is initialized
        if (driver == null) {
            logger.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        
		logger.info("**************starting TC004_GoogleHomeSearchTest **************");
		try {
			GoogleSearch gs = new GoogleSearch(driver);
			gs.setSearchText(randomString(8).toUpperCase());
			
			logger.info("**************Entered google search text**************");
			
			Thread.sleep(5000);
			//gs.clickBtnIamFeelingLucky();
			
			gs.clickBtnGoogleSearch();
			logger.info("**************clicked on Google Search button **************");
					
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			logger.info("**************GoogleHomeSearchTest caused exception **************");
			//logger.error("Test failed..");
			//logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("**************Finished GoogleHomeSearchTest **************");
	}
}

