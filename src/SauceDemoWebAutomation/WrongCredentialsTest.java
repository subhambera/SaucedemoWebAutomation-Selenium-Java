package SauceDemoWebAutomation;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WrongCredentialsTest extends main {

    @Test
    @Order(2)
    public void testWrongCredentials() {
        driver.findElement(By.id("user-name")).sendKeys("wrong_user");
        driver.findElement(By.id("password")).sendKeys("wrong_pass");
        driver.findElement(By.id("login-button")).click();

        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assertions.assertTrue(error.getText().contains("Username and password do not match"));
    }
}
