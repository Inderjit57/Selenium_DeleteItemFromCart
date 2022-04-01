package BasePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	// Initializing web driver and keeping it public for other packages to access
	public static WebDriver wd;
	public static Properties properties;
	public FileInputStream file;
	public static WebDriverWait driverWait;
	public static JavascriptExecutor je;
	public static Actions actions;

	public TestBase() {
		try {
			properties = new Properties();
			file = new FileInputStream(
					"C:\\Users\\singh\\Inderjit-workspace\\DeleteItemFromCart\\src\\main\\java\\configuration\\configuration.properties");
			properties.load(file);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File not found");
			System.out.println(e);
		}
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

		je = (JavascriptExecutor) wd;
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driverWait = new WebDriverWait(wd, 20);

		wd.get(properties.getProperty("Url"));
	}

	public void tearDown() {
		// wd.quit();
	}
}
