/**
 * 
 */
package com.brawijaya.page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * @author Brawijaya
 *
 */
public class LoginPage {
	WebDriver driv;
	
	@FindBy(id ="email")
	public WebElement loginEmail;
	
	@FindBy(id ="passwd")
	public WebElement loginPass;
	
	@FindBy(xpath = "//a[@title = 'Recover your forgotten password']")
	public WebElement forgotPassLink;
	
	@FindBy(id ="SubmitLogin")
	public WebElement signInButton;
	
	public LoginPage(WebDriver driv) {
		this.driv = driv;
		PageFactory.initElements(driv, this);
	}
	
}
