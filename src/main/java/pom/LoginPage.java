package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // URL страницы регистрации
    private final String url = "https://stellarburgers.nomoreparties.site/login";

    private final By emailElement = By.name("name");
    private final By passwordElement = By.name("Пароль");
    private final By submitButtonElement = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");

    public void open() {
        driver.get(url);
    }
    public void setEmail(String name) {
        driver.findElement(emailElement).sendKeys(name);
    }

    public void setPassword(String password) {
        driver.findElement(passwordElement).sendKeys(password);
    }

    public void submitForm() {
        driver.findElement(submitButtonElement).click();
    }
}
