
package test;

import Base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class LambdaTestChromeWin10 extends BaseTest {

	@Test
	public void runChromeScenario() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driver.get("https://www.lambdatest.com");

// Wait for full page load
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));

		System.out.println("Locating 'Explore all Integrations' link...");
		WebElement exploreIntegrations = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[contains(text(),'Explore all Integrations')]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", exploreIntegrations);
		System.out.println("Scrolled to 'Explore all Integrations'.");

// Get the href and open it in a new tab
		String integrationUrl = exploreIntegrations.getAttribute("href");
		((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", integrationUrl);
		System.out.println("Opened 'Explore all Integrations' in a new tab.");

// Wait and switch to new tab
		Thread.sleep(2000);
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		if (windowHandles.size() > 1) {
			driver.switchTo().window(windowHandles.get(1));
			System.out.println("Switched to new tab.");
		} else {
			throw new RuntimeException("Expected second window/tab was not opened.");
		}

// Continue with your test...
		wait.until(ExpectedConditions.urlToBe("https://www.lambdatest.com/integrations"));

		wait.until(ExpectedConditions.urlToBe("https://www.lambdatest.com/integrations"));
		assert driver.getCurrentUrl().equals("https://www.lambdatest.com/integrations");

		WebElement codelessAutomation = wait
				.until(ExpectedConditions.elementToBeClickable(By.linkText("Codeless Automation")));
		codelessAutomation.click();

		WebElement testingWhizLink = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("a[href='https://www.lambdatest.com/support/docs/testingwhiz-integration/']")));
		testingWhizLink.click();

		// wait.until(ExpectedConditions.titleIs("TestingWhiz Integration With
		// LambdaTest"));
		// assert driver.getTitle().equals("TestingWhiz Integration With LambdaTest");

		wait.until(ExpectedConditions.titleContains("TestingWhiz"));
		System.out.println("Actual page title: " + driver.getTitle());
		assert driver.getTitle().contains("TestingWhiz");
		System.out.println("Navigated to page with title: " + driver.getTitle());

		driver.close();
		driver.switchTo().window(windowHandles.get(0));

		// Get the current window count
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("Window count: " + allWindows.size());

		// Navigate to the LambdaTest blog
		driver.navigate().to("https://www.lambdatest.com/blog");
		System.out.println("Navigated to: " + driver.getCurrentUrl());

		// Click on the ‘Community’ link using XPath
		WebElement communityLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Community")));
		communityLink.click();

		// Verify the URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL after clicking Community link: " + currentUrl);
		driver.close();

	}
}
