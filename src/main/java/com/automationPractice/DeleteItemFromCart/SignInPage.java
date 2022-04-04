package com.automationPractice.DeleteItemFromCart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationPractice.BasePackage.TestBase;

public class SignInPage extends TestBase {
	public SignInPage() {
		PageFactory.initElements(wd, this);
	}

	@FindBy(css = ".col-xs-12.col-sm-6 div input[id='email']")
	WebElement emailInput;

	@FindBy(css = "#passwd")
	WebElement passwordInput;

	@FindBy(css = "#SubmitLogin")
	WebElement signInBtn;

	public void enterEmail(String email) {
		emailInput.sendKeys(email);
	}

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	public AccountPage clickSignInBtn() {
		signInBtn.click();
		return new AccountPage();
	}

}
