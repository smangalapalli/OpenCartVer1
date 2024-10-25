package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath = "//h2[normalize-space()='My Account']") //MyAccount Page Heading
	WebElement msgHeading;
	
	@FindBy(xpath = "//aside[@id='column-right']//a[normalize-space()='Logout']")
	WebElement lnkLogOut;
	
	
	public boolean IsmyAccountPageExists() {
		
		System.out.println ("I am here on account page");
		try {
			return msgHeading.isDisplayed(); //return true if displayed else return false
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public void clickLogout() {
		lnkLogOut.click();
		System.out.println ("Clicked on Logout link on myaccount page");
	}
}
