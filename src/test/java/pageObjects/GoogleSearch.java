package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearch extends BasePage {

	public GoogleSearch(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//Find the Google Search WebElements locators using PageFactory initialized in the BasePage class
	@FindBy(xpath = "//textarea[@id='APjFqb']")
	WebElement txtSearchbox;
	
	//@FindBy(xpath = "//input[@id='gbqfbb']")
	//WebElement btnIamFeelingLucky;
	
	@FindBy(xpath = "//input[@jsaction='trigger.kWlxhc']")
	WebElement btnIamFeelingLucky;
	
	@FindBy(xpath = "//div[@jsname='VlcLAe']//input[@name='btnK']")
	WebElement btnGooglesearch;
	
	//@FindBy(xpath = "//div[@class = 'FPdoLc lJ9FBc']//input[@value='Google Search' ]")
	//WebElement btnGooglesearch;
	
	public void setSearchText(String txt) {
		txtSearchbox.sendKeys(txt);
	}

	public void clickBtnGoogleSearch() {
		btnGooglesearch.click();
	}
	
	public void clickBtnIamFeelingLucky() {
		btnIamFeelingLucky.click();
	}
	
	public String msgConfirm() {
		return driver.getTitle();
	}
	
	
	

}
