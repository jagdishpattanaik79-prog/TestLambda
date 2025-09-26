package Tests;



import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseTest {
    @Test
    public void testSearchFunctionality() {
        driver.get("https://example.com");

        driver.findElement(By.cssSelector("input.search-box")).sendKeys("LambdaTest");
        driver.findElement(By.className("search-button")).click();

        List<WebElement> results = driver.findElements(By.tagName("h2"));
        Assert.assertTrue(results.size() > 0);
    }
}



