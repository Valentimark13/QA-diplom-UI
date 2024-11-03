package pom;

import helper.Generator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalPage {
    private WebDriver driver;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    private final By logoutButtonElement = By.cssSelector(".Account_button__14Yp3.text.text_type_main-medium.text_color_inactive");

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
    }

    public void open() {
        // URL страницы регистрации
        String username = Generator.generateRandomString(8);
        String email = Generator.generateRandomString(6) + "@example.com";
        String password = Generator.generateRandomString(10);
        registrationPage.open();
        registrationPage.setUsername(username);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.submitRegistration();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.submitForm();
        wait.until(ExpectedConditions.urlToBe(mainPage.url));

        mainPage.goToPersonalPage();

        wait.until(ExpectedConditions.urlContains("/account/profile"));
    }

    public void logout() {
        driver.findElement(logoutButtonElement).click();
    }
}
