package org.opencart;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

public class LoginPageStepDef {

    private WebDriver driver;
    private LoginPage loginPage;


    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("que estoy en la página de inicio de sesión de OpenCart")
    public void i_am_on_the_opencart_login_page() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Given("que ingreso un usuario y contraseña válidos")
    public void i_enter_valid_username_and_password() {
        loginPage.enterEmail("test@example.com");
        loginPage.enterPassword("Test1234");
    }

    @Given("que ingreso {string} y {string} inválidos")
    public void i_enter_invalid_credentials(String username, String password) {
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
    }

    @When("hago clic en el botón Login")
    public void i_click_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("debo iniciar sesión correctamente")
    public void i_should_be_logged_in_successfully() {
        Assert.assertTrue(loginPage.checkLogoutLink(), "Logout link should be displayed");
    }

    @And("debo ser redirigido al panel de mi cuenta")
    public void i_should_be_redirected_to_my_account() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("account/account"), "Should be on account page");
    }

    @Then("debo ver un mensaje de error que indique {string}")
    public void i_should_see_error_message(String expectedErrorMessage) {
        // Aquí deberías implementar la validación del mensaje de error
        // Por ahora, verificamos que NO estamos logueados
        Assert.assertFalse(loginPage.checkLogoutLink(), "Should not be logged in");
    }

    @When("hago clic en el enlace {string}")
    public void i_click_on_link(String linkText) {
        if (linkText.equals("Forgotten Password")) {
            loginPage.clickForgottenPasswordLink();
        }
    }

    @Then("debo ser redirigido a la página de recuperación de contraseña")
    public void i_should_be_redirected_to_password_recovery_page() {
        String currentUrl = loginPage.getForgotPwdPageUrl();
        Assert.assertTrue(currentUrl.contains("account/forgotten"),
                         "Should be on forgotten password page");
    }
}
