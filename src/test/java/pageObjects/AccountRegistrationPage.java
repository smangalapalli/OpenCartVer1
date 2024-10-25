package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//Find the registration WebElements locators using PageFactory initialized in the BasePage class
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtPhone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;	
	
	@FindBy(xpath = "//input[@name='agree' and @type = 'checkbox']")
	WebElement chkPolicy;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPhone(String phone) {
		txtPhone.sendKeys(phone);
	}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy() {
		//sol1 --did not work
		//chkPolicy.click();
		
		//sol2 --did not work
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//mywait.until(ExpectedConditions.elementToBeClickable(chkPolicy)).click();
		
		//sol3 --did not work
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].scrollIntoView(true);", chkPolicy);
		
		//sol3--worked
		Actions act = new Actions(driver);
		act.moveToElement(chkPolicy).click().perform();
		
	}
	
	public void clickContinue() {
	
		//sol1 --worked
		//btnContinue.click();
		
		//sol2
		//btnContinue.submit();
		
		//sol3
		Actions act = new Actions(driver);
		act.moveToElement(btnContinue).click().perform();
		
		//sol4
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", btnContinue);
				
		//sol5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//sol6
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public String getConfirmationMessage() {
		try {
			return msgConfirmation.getText();
		} catch(Exception e) {
			return e.getMessage();
		}		
	}
	
	
	
}
