import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SauceDemoTests {
    WebDriver driver;

    @BeforeEach
    public void setUp() {

        // ChromeOptions to disable password manager & leak detection
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);         // Disable "Save password"
        prefs.put("profile.password_manager_enabled", false);   // Disable password manager
        prefs.put("profile.password_manager_leak_detection", false); // Disable "Change password" popup
        options.setExperimentalOption("prefs", prefs);

        // Extra stability arguments
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Test Case 1: Login with valid credentials
    @Test
    @Order(1)
    public void testLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement productsTitle = driver.findElement(By.className("title"));
        Assertions.assertEquals("Products", productsTitle.getText());
    }

    // Test Case 2: Wrong credentials (moved up)
    @Test
    @Order(2)
    public void testWrongCredentials() {
        driver.findElement(By.id("user-name")).sendKeys("wrong_user");
        driver.findElement(By.id("password")).sendKeys("wrong_pass");
        driver.findElement(By.id("login-button")).click();

        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assertions.assertTrue(error.getText().contains("Username and password do not match"));
    }

    // Test Case 3: Logout after successful login (moved down)
    @Test
    @Order(3)
    public void testLogout() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("logout_sidebar_link")).click();

        Assertions.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }


        // Test Case 4: Add to cart
        @Test
        @Order(4)
        public void testAddToCart() throws InterruptedException {
            loginAsStandardUser();

            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            Thread.sleep(2000);
            WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));

            Assertions.assertEquals("1", cartBadge.getText());
        }

        // Test Case 5: Go to cart
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
            Thread.sleep(2000);
        }

        // Test Case 6: Checkout button
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
            Thread.sleep(2000);
        }

        // 🔹 Helper Method to login quickly
        private void loginAsStandardUser() {
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
        }

    // ✅ Test Case 7: Complete checkout flow
    @Test
    @Order(7)
    public void testCompleteCheckoutFlow() throws InterruptedException {
        loginAsStandardUser();
        Thread.sleep(2000);

        // Add item to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(2000);

        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);

        // Click checkout
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);

        // Fill customer info
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        Thread.sleep(500);

        driver.findElement(By.id("continue")).click();
        Thread.sleep(500);

        // Validate payment information
        WebElement paymentInfo = driver.findElement(By.className("summary_info_label"));
        Assertions.assertTrue(paymentInfo.getText().contains("Payment Information"));

        // Finish order
        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);

        // Validate thank you message
        WebElement thankYou = driver.findElement(By.className("complete-header"));
        Assertions.assertEquals("Thank you for your order!", thankYou.getText());
        Thread.sleep(2000);
    }

}
// End of file
