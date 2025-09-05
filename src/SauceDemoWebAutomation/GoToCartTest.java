package SauceDemoWebAutomation;

import SauceDemoWebAutomation.main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoToCartTest extends main {

    @Test
    @Order(5)
    public void testGoToCart() throws InterruptedException {
        loginAsStandardUser();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(2000);

        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);

        WebElement cartItem = driver.findElement(By.className("inventory_item_name"));
        Assertions.assertEquals("Sauce Labs Bike Light", cartItem.getText());
    }
}
