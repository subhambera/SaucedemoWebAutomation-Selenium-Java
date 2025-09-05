package SauceDemoWebAutomation;

import SauceDemoWebAutomation.main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckoutButtonTest extends main {

    @Test
    @Order(6)
    public void testCheckoutButton() throws InterruptedException {
        loginAsStandardUser();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);

        WebElement checkoutTitle = driver.findElement(By.className("title"));
        Assertions.assertEquals("Checkout: Your Information", checkoutTitle.getText());
    }
}
