package com.Inderjit.DeleteItemFromCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePackage.TestBase;

public class Homepage extends TestBase {
	public Homepage() {

		PageFactory.initElements(wd, this);
	}
	
	@FindBy(css = "div.header_user_info")
	WebElement signInBtn;

	public SignInPage clickSignInBtn() {
		signInBtn.click();
		driverWait = new WebDriverWait(wd, 20);
		driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".col-xs-12.col-sm-6 div input[id='email']")));
		return new SignInPage();

	}
}
