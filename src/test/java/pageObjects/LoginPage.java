package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);		
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmailAddress;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//form[@id='form-login']//a[normalize-space()='Forgotten Password']")
	WebElement lnkForgotPassword;
	
	public void setEmail(String email) {
		txtEmailAddress.sendKeys(email);
		//Thread.sleep(5000);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);		
	}
	
	public void clearEmail() {
		txtEmailAddress.clear();
		System.out.println("cleared Email field");		
	}
	public void clearPassword() {
		txtPassword.clear();	
		System.out.println("cleared pwd field");
	}
	
	public void clickLogin() throws InterruptedException {		
		System.out.println("I am clicking the login button");
		btnLogin.click();
		System.out.println("I clicked the login button");
	}
	
}
