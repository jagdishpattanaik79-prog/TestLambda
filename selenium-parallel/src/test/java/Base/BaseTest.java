
package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
	protected WebDriver driver;

	@SuppressWarnings("deprecation")
	@BeforeMethod
	@Parameters({ "browser", "platform" })
	public void setUp(String browser, String platform) throws Exception {
		String username = "jagdishpattanaik79";
		String accessKey = "LT_dICMkl0CuOb0RxMZ1JooLEy8X1pqA1hROb8J7G7Z7imc3a3";

		HashMap<String, Object> ltOptions = new HashMap<>();
		ltOptions.put("username", username);
		ltOptions.put("accessKey", accessKey);
		ltOptions.put("project", "LambdaTest Automation");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		ltOptions.put("platformName", platform);
		ltOptions.put("build", "Cross Browser Test");
		ltOptions.put("name", "LambdaTest Scenario");

		ltOptions.put("video", true);
		ltOptions.put("console", true);
		ltOptions.put("network", true);
		ltOptions.put("visual", true);

		if (browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			ltOptions.put("browserName", "Chrome");
			ltOptions.put("browserVersion", "128.0");
			options.setCapability("LT:Options", ltOptions);
			driver = new RemoteWebDriver(
					new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), options);
		} else if (browser.equalsIgnoreCase("Edge")) {
			EdgeOptions options = new EdgeOptions();
			ltOptions.put("browserName", "MicrosoftEdge");
			ltOptions.put("browserVersion", "127.0");
			options.setCapability("LT:Options", ltOptions);
			driver = new RemoteWebDriver(
					new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), options);
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
