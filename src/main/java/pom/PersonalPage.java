package pom;

import dto.UserDTO;
import helper.Generator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.UserService;

import java.time.Duration;

public class PersonalPage {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;

    private final By logoutButtonElement = By.cssSelector(".Account_button__14Yp3.text.text_type_main-medium.text_color_inactive");

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    public void open(UserDTO user) {
        UserService service = new UserService();
        service.register(user);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        loginPage.open();
        loginPage.setEmail(user.email);
        loginPage.setPassword(user.password);
        loginPage.submitForm();
        wait.until(ExpectedConditions.urlToBe(mainPage.url));

        mainPage.goToPersonalPage();

        wait.until(ExpectedConditions.urlContains("/account/profile"));
    }

    public void logout() {
        driver.findElement(logoutButtonElement).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    public void goToConstructor() {
        mainPage.clickToConstructor();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(mainPage.url));
    }

    public void goToMainPage() {
        mainPage.clickToLogo();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(mainPage.url));
    }
}
