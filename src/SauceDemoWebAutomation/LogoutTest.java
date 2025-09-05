package SauceDemoWebAutomation;

import SauceDemoWebAutomation.main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogoutTest extends main {

    @Test
    @Order(3)
    public void testLogout() throws InterruptedException {
        loginAsStandardUser();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("logout_sidebar_link")).click();

        Assertions.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }
}
