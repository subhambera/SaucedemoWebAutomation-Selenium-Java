package SauceDemoWebAutomation;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddToCartTest extends main {

    @Test
    @Order(4)
    public void testAddToCart() throws InterruptedException {
        loginAsStandardUser();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(2000);

        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assertions.assertEquals("1", cartBadge.getText());
    }
}
