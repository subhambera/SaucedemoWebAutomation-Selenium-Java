package SauceDemoWebAutomation;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends main {

    @Test
    @Order(1)
    public void testLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement productsTitle = driver.findElement(By.className("title"));
        Assertions.assertEquals("Products", productsTitle.getText());
    }
}
