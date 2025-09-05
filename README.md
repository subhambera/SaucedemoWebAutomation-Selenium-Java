# 🧪 SauceDemo Web Automation Test using Java-Selenium.

This project contains automated test cases for SauceDemo using Java, JUnit 5, and Selenium WebDriver. Each test case is written in a separate class, with a shared main.java class for browser setup/teardown.

# ✨ Features

One test per class for clarity and isolation.

- Shared main: Chrome setup, implicit wait, window maximize, teardown.

- Hardened ChromeOptions: disables password manager & leak detection popups.

- Reusable helper: loginAsStandardUser() for faster test steps.

- Deterministic execution via @TestMethodOrder(OrderAnnotation.class) (while keeping each test independent).

- GitHub Actions workflow to run tests on push/PR (.github/workflows/tests.yml).

- Easy headless mode: add options.addArguments("--headless=new") in BaseTest for CI-only runs.

# 📂 Project Structure

<div style="max-height: 400px; overflow-y: auto;">

```bash
src/test/java/
 ├── main/
 │    └── main.java
 ├── tests/
 │    ├── LoginTest.java
 │    ├── WrongCredentialsTest.java
 │    ├── LogoutTest.java
 │    ├── AddToCartTest.java
 │    ├── GoToCartTest.java
 │    ├── CheckoutButtonTest.java
 │    └── CompleteCheckoutFlowTest.java
 └── main.java
```
</div>

# 🚀 Test Cases

1. LoginTest – Login with valid credentials

2. WrongCredentialsTest – Attempt login with invalid credentials

3. LogoutTest – Logout after successful login

4. AddToCartTest – Add an item to the shopping cart

5. GoToCartTest – Navigate to the cart and validate items

6. CheckoutButtonTest – Verify checkout button navigation

7. CompleteCheckoutFlowTest – End-to-end checkout flow with order confirmation

# ⚙️ Tech Stack

- Java 24+

- Selenium

- JUnit

- Google Chrome

- ChromeDriver (same version as Chrome)

# 📌 Notes

- main.java handles WebDriver lifecycle per test method.

- Thread.sleep() calls are for demo pacing; prefer WebDriverWait for stability.

- You can make tests headless by adding --headless=new to ChromeOptions for CI.

---

# 👨‍💻 *Subham Bera* - QA Analyst | Automation Enthusiast 
               
<p align="center", fontsize="bold">✨ Happy Testing! ✨</p>
