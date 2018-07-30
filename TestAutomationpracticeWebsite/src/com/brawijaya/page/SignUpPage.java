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
public class SignUpPage {
	WebDriver driv;
	@FindBy (xpath = "//input[@id='id_gender1' and @type='radio']")
	public WebElement radioMr;
	
	@FindBy (xpath = "//input[@id='id_gender2' and @type='radio']")
	public WebElement radioMrs;
	
	@FindBy (id = "customer_firstname")
	public WebElement firstName;
	
	@FindBy (id = "customer_lastname")
	public WebElement lastName;
	
	@FindBy (id = "email")
	public WebElement email;
	
	@FindBy (id = "passwd")
	public WebElement password;
	
	@FindBy (id = "days")
	public WebElement comboDay;
	
	@FindBy (id = "months")
	public WebElement comboMonth;
	
	@FindBy (id = "years")
	public WebElement comboYear;
	
	@FindBy (id = "newsletter")
	public WebElement checkNews;
	
	@FindBy (id = "optin")
	public WebElement checkOptin;
	
	@FindBy (id = "firstname")
	public WebElement addFirstName;
	
	@FindBy (id = "lastname")
	public WebElement addLastName;
	
	@FindBy (id = "company")
	public WebElement company;
	
	@FindBy (id = "address1")
	public WebElement address;
	
	@FindBy (id = "address2")
	public WebElement address2;
	
	@FindBy (id = "city")
	public WebElement city;
	
	@FindBy (id = "id_state")
	public WebElement State;
	
	@FindBy (id = "postcode")
	public WebElement zipCode;
	
	@FindBy(id ="id_country")
	public WebElement country;
	
	@FindBy (id = "other")
	public WebElement addInfo;
	
	@FindBy (id = "phone")
	public WebElement homePhone;
	
	@FindBy (id = "phone_mobile")
	public WebElement mobilePhone;
	
	@FindBy (id = "alias")
	public WebElement alias;
	
	@FindBy (id = "submitAccount")
	public WebElement registerButton;
	
	public SignUpPage(WebDriver driv)
	{
		this.driv=driv;
		PageFactory.initElements(driv, this);
	}
	
	
}
