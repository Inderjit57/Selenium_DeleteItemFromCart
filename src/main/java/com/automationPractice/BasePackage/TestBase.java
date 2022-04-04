package com.automationPractice.BasePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;

import com.automationPractice.Logger.WebDriverEvents;
import com.automationPractice.Utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver wd;
	public static Properties properties;
	public FileInputStream file;
	public static WebDriverWait driverWait;
	public static JavascriptExecutor je;
	public static Actions actions;
	public static Logger logger;
	public EventFiringWebDriver e_driver;
	public WebDriverEvents eventListner;

	public TestBase() {
		try {
			properties = new Properties();
			file = new FileInputStream(
					"C:\\Users\\singh\\Inderjit-workspace\\DeleteItemFromCart\\src\\main\\java\\com\\automationPractice\\configuration\\configuration.properties");
			properties.load(file);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File not found");
			System.out.println(e);
		}
	}

	@BeforeClass
	public void setUpLogger() {
		logger = Logger.getLogger(TestBase.class);
		PropertyConfigurator.configure("log4j.properties");
		BasicConfigurator.configure();

		logger.setLevel(Level.INFO); // Level from apache log4j

	}

	public void structureInitialisation() {
		/*
		 * WebDriver Manager API to detect the driver version in a system Switch between
		 * browsers when called from configuration
		 */
		String openSelectedBrowser = properties.getProperty("browser");
		switch (openSelectedBrowser) {
		case ("chrome"):
			WebDriverManager.chromedriver().setup();
			wd = new ChromeDriver();
			break;

		case ("edge"):
			WebDriverManager.edgedriver().setup();
			wd = new EdgeDriver();
			break;

		}

		// Intialising Webdriver Event Listener
		e_driver = new EventFiringWebDriver(wd);
		eventListner = new WebDriverEvents();
		e_driver.register(eventListner);
		wd = e_driver;

		je = (JavascriptExecutor) wd;
		wd.manage().window().maximize();
		
		//Implicit wait is used to keep pinging the dom to search for webElement
//		wd.manage().timeouts().implicitlyWait(Utils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		wd.manage().timeouts().pageLoadTimeout(Utils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driverWait = new WebDriverWait(wd, 20);

		wd.get(properties.getProperty("Url"));
	}
	
	/*
	 * In order to verify the page is loaded, 
	 * Javascrptexecutor is used which waits until the page is loaded and then ping the dom to search for web element
	 *  v -> is a functional interface, it will only work on java 1.8 and after. So version has to be changed inside
	 *  the java compiler, if below 1.8
	 */
	public void waitForDocumentCompleteState(int timeOutInSeconds) {
		new WebDriverWait(wd, 15).until((ExpectedCondition<Boolean>) v -> {
			logger.info("Verifying page has loaded......");
			je = (JavascriptExecutor) wd;
			String documentIsReady = je.executeScript("return document.readyState").toString();
			while (true) {
				if (documentIsReady.equalsIgnoreCase("complete")) {
					logger.info("Page has loaded completely......");
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void tearDown() {
		wd.quit();
	}
}
