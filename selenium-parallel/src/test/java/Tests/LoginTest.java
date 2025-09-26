
package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void testLogin() {
        driver.get("https://example.com/login");

        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        Assert.assertTrue(driver.getTitle().contains("Dashboard"));
    }
}




