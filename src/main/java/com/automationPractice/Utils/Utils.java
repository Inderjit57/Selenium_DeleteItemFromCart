package com.automationPractice.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automationPractice.BasePackage.TestBase;

public class Utils extends TestBase {
	public static int IMPLICIT_WAIT = 10;
	public static int PAGE_LOAD_TIMEOUT = 30;

	public static String generateRandomEmail() {

		String email = RandomStringUtils.randomNumeric(3);

		String emailID = "SuperMan" + email + "@gmail.com";

		return emailID;

	}

	public static String generateRandomPassword() {

		String randomStringForPassword = RandomStringUtils.randomNumeric(3);

		String password = "cryptonite@" + randomStringForPassword;

		return password;

	}

	public static void takeScreenShotOfFailedTestCase(String nameOfTestCase) {

		// Format the current time in my system
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		// Capturing the screen shot
		File file = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(file,
					new File("./FailedScreenShot\\" + "_" + nameOfTestCase + "_" + timeStamp + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Javascript Executor will got the WebElement to perform click function
	public static void javascriptClick(WebElement element) {
		je.executeScript("arguments[0].click();", element);
	}

	// Javascript Executor will scroll to the WebElement if its not visible on the
	// font
	public static void scrollIntoViewUsingJavascript(WebElement element) {
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void clickOnElement(WebElement element) {
		waitForElementToBeClickable(element, 15).click();
	}

	public static WebElement waitForElementToBeClickable(WebElement element, int timeOutInSeconds) {
		return new WebDriverWait(wd, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
	}

	public static WebElement waitForElementToBeVisible(WebElement element, int timeOutInSeconds) {
		return new WebDriverWait(wd, timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));
	}

	public static Boolean waitForElementToBeSelectable(WebElement element, int timeOutInSeconds) {
		return new WebDriverWait(wd, timeOutInSeconds)
				.until(ExpectedConditions.elementSelectionStateToBe(element, false));
	}

	public static Alert waitForAlertToBePresent(int timeOutInSeconds) {
		return new WebDriverWait(wd, timeOutInSeconds).until(ExpectedConditions.alertIsPresent());
	}

	public static void selectFromDropDownUsingIndex(WebElement element, int index) {
		try {
			if (waitForElementToBeSelectable(element, 15)) {
				new Select(element).selectByIndex(index);
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}

	}

	public static void selectFromDropDownUsingVisibleText(WebElement element, String text) {
		try {
			if (waitForElementToBeSelectable(element, 15)) {
				new Select(element).deselectByVisibleText(text);
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}

	}

	public static void moveToElement(WebElement element) {
		Actions actions = new Actions(wd);
		actions.moveToElement(element).build().perform();
	}

	public void acceptAlert() {
		if (waitForAlertToBePresent(5) != null) {
			wd.switchTo().alert().accept();
		}
	}

	public void dismissAlert() {
		if (waitForAlertToBePresent(5) != null) {
			wd.switchTo().alert().dismiss();
		}
	}

	public static void sendData(WebElement element, String text) {
		waitForElementToBeVisible(element, 10).sendKeys(text);

	}

	public void switchToWindowHandle(WebElement element) {
		String parentHandle = wd.getWindowHandle();
		element.click();
		Set<String> getAllHandles = wd.getWindowHandles();
		for (String individualHandle : getAllHandles) {
			if (!individualHandle.equalsIgnoreCase(parentHandle)) {
				wd.switchTo().window(individualHandle);
			}
		}
	}

	public void switchToFrame(WebElement element) {
		wd.switchTo().frame(waitForElementToBeVisible(element, 10));
	}

	public static String getTextFromWebelement(WebElement element) {
		return waitForElementToBeVisible(element, 30).getText();
	}

}
