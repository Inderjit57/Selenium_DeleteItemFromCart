package com.Inderjit.DeleteItemFromCart;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePackage.TestBase;

public class ShoppingCartSummaryTest extends TestBase {
	Homepage homepage;
	AccountPage accountPage;
	QuickViewIframe quickViewIframe;
	ShoppingCartSummary shoppingCartSummary;
	SignInPage signInPage;
	ViewCart viewCart;
	WomenTab womenTab;

	@BeforeMethod
	public void intialisation() {
		structureInitialisation();

		// Initializing landing page / Homepage
		homepage = new Homepage();
	}

	@Test
	public void deleteItemFromCart() {

		// Click signIn on homepage, Enter Login Credentials and signIn
		signInPage = homepage.clickSignInBtn(); // ClickSignInBtn invoke the constructor of the SignInPage.
		signInPage.enterEmail(properties.getProperty("email"));
		signInPage.enterPassword(properties.getProperty("password"));

		// SignInPage invokes the constructor of AccountPage.
		accountPage = signInPage.clickSignInBtn();

		// Verify if account is successfully opened
		String successfullSignInText = accountPage.getTextFromMessage();
		String accountMessage = properties.getProperty("accountMessage");
		System.out.println("Account login Message : " + accountMessage);
		Assert.assertEquals(successfullSignInText, accountMessage, "Account name is not present");

		// Go to Women Tab
		// AccountPage invokes the constructor of WomenTab.
		womenTab = accountPage.clickOnWomenTab();
		// hover to item quick view
		womenTab.hoverToItem();

		// WomenTab invokes the constructor of QuickViewIFrame
		quickViewIframe = womenTab.switchToIframe();

		// click add to cart
		quickViewIframe.addToCart();
		quickViewIframe.explicitlyWaitForTabToView();

		// Close the tab
		quickViewIframe.closeTheframe();

		// QuickViewIFrame invokes the constructor of ViewCart
		viewCart = quickViewIframe.clickOnCart();
		viewCart.clickDelete(); // Delete the item from the cart

		shoppingCartSummary = viewCart.goToShoppingCart();

		// wait for the element to appear
		shoppingCartSummary.waitForElementToAppear();

		// verify successful deleted message
		String successfulDeletedMessage = shoppingCartSummary.getDeletedText();
		System.out.println("Deleted message: " + successfulDeletedMessage);
		Assert.assertEquals(successfulDeletedMessage, properties.getProperty("ItemDeletedMessage"), "Message not found");

	}

	@AfterMethod
	public void closeBrowser() {
		tearDown();
	}

}
