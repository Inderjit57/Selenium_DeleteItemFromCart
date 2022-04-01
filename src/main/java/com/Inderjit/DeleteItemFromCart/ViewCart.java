package com.Inderjit.DeleteItemFromCart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BasePackage.TestBase;

public class ViewCart extends TestBase {
	public ViewCart() {
		PageFactory.initElements(wd, this);
	}

	@FindBy(css = "a[title='Delete']")
	WebElement deleteItemFromCart;

	public void clickDelete() {
		// Close the tab
		deleteItemFromCart.click();
	}

	public ShoppingCartSummary goToShoppingCart() {
		return new ShoppingCartSummary();
	}
}
