package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private WebDriver driver;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    // URL страницы регистрации
    private String url = "https://stellarburgers.nomoreparties.site/forgot-password";

    private final By loginLink = By.cssSelector(".Auth_link__1fOlj");

    public void open() {
        // URL страницы регистрации
        driver.get(url);
    }

    public void loginClick() {
        driver.findElement(loginLink).click();
    }
}
