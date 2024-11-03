import helper.Generator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.*;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import io.qameta.allure.Step;

public class PersonalPageTests {
    private WebDriver driver;

    private PersonalPage personalPage;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        personalPage = new PersonalPage(driver);
        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPersonalPage() {
        openPersonalPage();
        assertOnPersonalPage();
    }

    @Test
    public void testGoToConstructor() {
        openPersonalPage();
        goToConstructor();
        assertOnMainPage();
    }

    @Test
    public void testGoToMainPage() {
        openPersonalPage();
        goToMainPage();
        assertOnMainPage();
    }

    @Test
    public void testLogout() {
        openPersonalPage();
        logout();
        assertOnLoginPage();
    }

    @Step("Открываю личную страницу")
    private void openPersonalPage() {
        personalPage.open();
    }

    @Step("Проверяю, что находимся на личной странице")
    private void assertOnPersonalPage() {
        assertTrue(driver.getCurrentUrl().contains("/account/profile"));
    }

    @Step("Перехожу в конструктор")
    private void goToConstructor() {
        mainPage.clickToConstructor();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(mainPage.url));
    }

    @Step("Перехожу на главную страницу")
    private void goToMainPage() {
        mainPage.clickToLogo();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(mainPage.url));
    }

    @Step("Выход из личного кабинета")
    private void logout() {
        personalPage.logout();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Step("Проверяю, что находимся на странице входа")
    private void assertOnLoginPage() {
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Step("Проверяю, что находимся на главной странице")
    private void assertOnMainPage() {
        assertTrue(driver.getCurrentUrl().equals(mainPage.url));
    }
}
