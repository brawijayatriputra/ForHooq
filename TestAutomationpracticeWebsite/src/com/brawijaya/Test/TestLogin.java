/**
 * 
 */
package com.brawijaya.Test;
import com.brawijaya.page.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author Brawijaya
 *
 */
class TestLogin {

	public static WebDriver driv;
	public String validEmail = "test961345@homer.com";
	public String invalidCorrEmail = "test123@homer.com";
	public String invalidWrongEmail = "testing";
	public String validPass = "123456";
	public String invalidPass = "12345";
	public String userName = "Homer Simpson";
	
	@BeforeEach
	public void setUpClass() {
		System.setProperty("webdriver.gecko.driver", "D:\\Program Mentah\\QA Tools\\Web Driver\\geckodriver.exe");
		driv = new FirefoxDriver();
		driv.get("http://automationpractice.com");	
	}
	
	@Test
	void testEmptyEmailEmptyPass(){
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.signInButton.click();
		assertEquals("An email address required.", driv.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText());
	}
	
	@Test
	void testInvalidWrongEmailEmptyPass(){
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.loginEmail.sendKeys(invalidWrongEmail);
		loginPage.signInButton.click();
		assertEquals("Invalid email address.", driv.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText());
	}
	
	@Test
	void testInvalidWrongEmailInvalidPass(){
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.loginEmail.sendKeys(invalidWrongEmail);
		loginPage.loginPass.sendKeys(invalidPass);
		loginPage.signInButton.click();
		assertEquals("Invalid email address.", driv.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText());
	}
	
	@Test
	void testInvalidCorrectEmailEmptyPass(){
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.loginEmail.sendKeys(invalidCorrEmail);
		loginPage.signInButton.click();
		assertEquals("Password is required.", driv.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText());
	}
	
	@Test
	void testInvalidCorrectEmailInvalidPass(){
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.loginEmail.sendKeys(invalidCorrEmail);
		loginPage.loginPass.sendKeys(invalidPass);
		waitUntilClick(loginPage.signInButton);
		assertEquals("Authentication failed.", driv.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText());
	}
	
	@Test
	void testValidEmailEmptyPass(){
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.loginEmail.sendKeys(validEmail);
		loginPage.signInButton.click();
		assertEquals("Password is required.", driv.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText());
	}
	
	@Test
	void testValidEmailInvalidPass(){
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.loginEmail.sendKeys(validEmail);
		loginPage.loginPass.sendKeys(invalidPass);
		loginPage.signInButton.click();
		assertEquals("Authentication failed.", driv.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText());
	}
	
	@Test
	void testValidEmailValidPass(){
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.loginEmail.sendKeys(validEmail);
		loginPage.loginPass.sendKeys(validPass);
		loginPage.signInButton.click();
		checkSuccess();
	}
	
	@Test
	void forgotPassLinkWorking() {
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.forgotPassLink.click();
		assertEquals("http://automationpractice.com/index.php?controller=password",driv.getCurrentUrl());
		assertEquals("Forgot your password - My Store", driv.getTitle());
	}
	
	@Test
	void forgotPassLinkEmptyEmail() {
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.forgotPassLink.click();
		WebElement retrivePassButton = driv.findElements(By.xpath("//button[@type='submit']")).get(1);
		retrivePassButton.click();
		assertEquals("Invalid email address.", driv.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText());
	}
	
	@Test
	void forgotPassLinkInvalidEmail() {
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.forgotPassLink.click();
		WebElement emailForgot = driv.findElement(By.id("email"));
		WebElement retrivePassButton = driv.findElements(By.xpath("//button[@type='submit']")).get(1);
		emailForgot.sendKeys(invalidCorrEmail);
		retrivePassButton.click();
		assertEquals("There is no account registered for this email address.", driv.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText());
	}
	
	@Test
	void forgotPassLinkValidEmail() {
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.forgotPassLink.click();
		WebElement emailForgot = driv.findElement(By.id("email"));
		WebElement retrivePassButton = driv.findElements(By.xpath("//button[@type='submit']")).get(1);
		emailForgot.sendKeys("test587303@homer.com");
		retrivePassButton.click();
		assertEquals("A confirmation email has been sent to your address: test587303@homer.com", driv.findElement(By.xpath("//p[@class='alert alert-success']")).getText());
	}
	
	@Test
	void keyboardNavigate() {
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.loginEmail.click();
		loginPage.loginEmail.sendKeys(Keys.TAB);
		loginPage.loginPass.sendKeys(Keys.TAB);
		loginPage.forgotPassLink.sendKeys(Keys.TAB);
	}
	
	@Test
	void keyboardEnterSignIn() {
		gotoSignIn();
		LoginPage loginPage = new LoginPage(driv);
		loginPage.loginEmail.sendKeys(validEmail);
		loginPage.loginEmail.sendKeys(Keys.TAB);
		loginPage.loginPass.sendKeys(validPass);
		loginPage.loginPass.sendKeys(Keys.ENTER);
		checkSuccess();
	}
	
	@AfterEach
	public void setDownClass() {
	    driv.quit();	
	}
	
	public void gotoSignIn() {
		WebElement loginButtonElement = driv.findElement(By.className("login"));
		loginButtonElement.click();
	}
	
	public void waitUntilClick(WebElement click) {
		WebDriverWait wait = new WebDriverWait(driv, 10);
		wait.until(ExpectedConditions.elementToBeClickable(click));
		click.click();
	}
	
	public void checkSuccess() {
		WebDriverWait wait = new WebDriverWait(driv, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Sign out')]")));
		assertEquals("http://automationpractice.com/index.php?controller=my-account",driv.getCurrentUrl());
		assertEquals("My account - My Store", driv.getTitle());
		assertEquals("Sign out", driv.findElements(By.className("logout")).get(0).getText());
		assertEquals(userName, driv.findElement(By.className("account")).getText());
	}
}
