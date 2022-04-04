package com.automationPractice.DeleteItemFromCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automationPractice.BasePackage.TestBase;

public class ShoppingCartSummary extends TestBase {

	public ShoppingCartSummary() {
		PageFactory.initElements(wd, this);
	}
	
	@FindBy(css="p[class='alert alert-warning']")
	WebElement deletedMessage;
	
	//wait for the message to appear on the DOM
	public void waitForElementToAppear() {
		driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p[class='alert alert-warning']")));
	}
	public String getDeletedText() {
		return deletedMessage.getText();
	}

}
