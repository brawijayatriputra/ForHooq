/**
 * 
 */
package com.brawijaya.Test;
import com.brawijaya.page.SignUpPage;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
/**
 * @author Brawijaya
 *
 */
class TestSignUp {

	public static WebDriver driv;
	public static String email,pass = null;
	
	@BeforeAll
	public static void setUpClass() {
		System.setProperty("webdriver.gecko.driver", "D:\\Program Mentah\\QA Tools\\Web Driver\\geckodriver.exe");
		driv = new FirefoxDriver();
		driv.get("http://automationpractice.com");
	}
	
	@Test
	void testSignUp(){
		test1gotoSignIn();
		test2createNewAccount();
		test3addInfoDetail();
	}
	
	
	@AfterAll
	public static void setDownClass() {
	    driv.quit();
	    File file = new File("Log.txt");
	    try
        {
	        FileWriter fileWriter = new FileWriter(file,true);
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        bufferedWriter.write(email+"\r\n");
	        bufferedWriter.write(pass+"\r\n");
	        // Always close files.
	        bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '"+ file + "'");
        }
	    
	}
	
	void test1gotoSignIn() {
		WebElement loginButtonElement = driv.findElement(By.className("login"));
		loginButtonElement.click();
		checkLoginPage();
	}
	
	void test2createNewAccount() {
		checkLoginPage();
		WebElement emailTextBox = driv.findElement(By.id("email_create"));
		WebElement createAccButton = driv.findElement(By.id("SubmitCreate"));
		email = "test"+randomString()+"@homer.com";
		//Registered Email
		//email = "test197740@homer.com";
		emailTextBox.sendKeys(email);
		createAccButton.click();
		if(checkEmailAlert()==false) {
			emailTextBox.clear();
			email = "test"+randomString()+"@homer.com";
			emailTextBox.sendKeys(email);
			createAccButton.click();
		}
		checkAddInfoPageHeading();
		System.out.println(email);
	}
	
	void test3addInfoDetail() {
		checkLoginPage();
		checkAddInfoPageHeading();
		SignUpPage signUp = new SignUpPage(driv);
		pass = "123456";
		String firstName = "Homer";
		String lastName = "Simpson";
		signUp.radioMr.click();;
		signUp.firstName.sendKeys(firstName);
		signUp.lastName.sendKeys(lastName);
		signUp.password.sendKeys(pass);
		signUp.comboDay.sendKeys("12");
		signUp.comboMonth.sendKeys("May");
		signUp.comboYear.sendKeys("1956");
		signUp.checkNews.click();
		signUp.checkOptin.click();
		signUp.company.sendKeys("Compu-Global-Hyper-Mega-Net");
		signUp.address.sendKeys("742 Evergreen Terrace");
		signUp.city.sendKeys("Springfields");
		signUp.country.sendKeys("United States");
		new WebDriverWait(driv, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("id_state")));
		signUp.State.sendKeys("Vermont");
		signUp.zipCode.sendKeys("54321");
		signUp.addInfo.sendKeys("YEAH!!!");
		signUp.homePhone.sendKeys("5550113");
		signUp.registerButton.click();
		checkSuccess(firstName,lastName);
		System.out.println(pass);
	}
	
	public String randomString() {
        String CHAR = "1234567890";
        StringBuilder temp = new StringBuilder();
        Random rnd = new Random();
        while (temp.length() < 6) { 
            int index = (int) (rnd.nextFloat() * CHAR.length());
            temp.append(CHAR.charAt(index));
        }
        String randString = temp.toString();
        return randString;
    }
	
	public void checkLoginPage() {
		assertEquals("Login - My Store", driv.getTitle());
	}
	
	public boolean checkEmailAlert() {
		String alert = "An account using this email address has already been registered. Please enter a valid password or request a new one. ";
		if(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+alert+"')]")) != null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void checkAddInfoPageHeading() {
		WebDriverWait wait = new WebDriverWait(driv, 10);
		String excpectedText = "CREATE AN ACCOUNT";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Create an account')]")));
		assertEquals(excpectedText, driv.findElement(By.xpath("//h1")).getText());
	}
	
	public void checkSuccess(String firstName,String lastName) {
		String Name = firstName+" "+lastName;
		assertEquals("http://automationpractice.com/index.php?controller=my-account",driv.getCurrentUrl());
		assertEquals("My account - My Store", driv.getTitle());
		assertEquals("Sign out", driv.findElements(By.className("logout")).get(0).getText());
		assertEquals(Name, driv.findElement(By.className("account")).getText());
	}

}
