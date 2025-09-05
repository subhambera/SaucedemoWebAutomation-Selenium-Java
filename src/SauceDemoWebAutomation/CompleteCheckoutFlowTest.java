package SauceDemoWebAutomation;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompleteCheckoutFlowTest extends main {

    @Test
    @Order(7)
    public void testCompleteCheckoutFlow() throws InterruptedException {
        loginAsStandardUser();
        Thread.sleep(2000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(2000);

        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("first-name")).sendKeys("test");
        driver.findElement(By.id("last-name")).sendKeys("tester");
        driver.findElement(By.id("postal-code")).sendKeys("875954");
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);

        WebElement paymentInfo = driver.findElement(By.className("summary_info_label"));
        Assertions.assertTrue(paymentInfo.getText().contains("Payment Information"));

        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);

        WebElement thankYou = driver.findElement(By.className("complete-header"));
        Assertions.assertEquals("Thank you for your order!", thankYou.getText());
    }
}
