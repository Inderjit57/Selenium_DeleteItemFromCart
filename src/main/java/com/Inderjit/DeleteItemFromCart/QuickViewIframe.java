package com.Inderjit.DeleteItemFromCart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BasePackage.TestBase;

public class QuickViewIframe extends TestBase {

	public QuickViewIframe() {
		PageFactory.initElements(wd, this);
	}

	@FindBy(css = ".box-cart-bottom button[type='submit']")
	WebElement addToCart;

	@FindBy(css = "#layer_cart > div[class='clearfix']")
	WebElement cartTab;

	@FindBy(css = ".layer_cart_product.col-xs-12.col-md-6 > span")
	WebElement closeBtn;

	@FindBy(css = "a[title='View my shopping cart']")
	WebElement goToCart;

	public void addToCart() {
		addToCart.click();
		
		// switch back to default content
		wd.switchTo().defaultContent();
	}

	public void explicitlyWaitForTabToView() {
		// Explicitly waiting for the element to be present
		driverWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("#layer_cart > div[class='clearfix']")));

		// Using JavascriptExecutor to scroll to the tab if its not on the front when page opens
		je.executeScript("arguments[0].scrollIntoView(true);", cartTab);

	}
	
	//Close the item selection frame
	public void closeTheframe() {
		closeBtn.click();
	}
	
	// Go to Cart page 
	public ViewCart clickOnCart() {
		actions = new Actions(wd);
		actions.moveToElement(goToCart).perform();
//		je.executeScript("document.getElementById('goToCart').click();");

		goToCart.click();
		return new ViewCart();
	}
	
	//Return to Women Tab page
	public WomenTab goToCart() {
		closeBtn.click();
		return new WomenTab();
	}

}
