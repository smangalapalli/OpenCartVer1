package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccLogoutPage extends BasePage{

	public AccLogoutPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btn_continue;
	
	public void clickContinue() throws InterruptedException {
		Thread.sleep(5000);
		btn_continue.click();
		System.out.println("Account logout page continue button clicked");
	}

}
