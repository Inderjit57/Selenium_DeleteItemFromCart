package com.automationPractice.DeleteItemFromCart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationPractice.BasePackage.TestBase;

public class AccountPage extends TestBase {
	public AccountPage() {
		PageFactory.initElements(wd, this);
	}

	@FindBy(css = "a[title='View my customer account'] span")
	WebElement welcomeMessageText;

	@FindBy(css = "a[title='Women']")
	WebElement womenTab;

	public String getTextFromMessage() {
		return welcomeMessageText.getText();
	}

	public WomenTab clickOnWomenTab() {
		womenTab.click();
		return new WomenTab();

	}

}
