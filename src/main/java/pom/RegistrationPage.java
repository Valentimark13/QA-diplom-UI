package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы элементов
    private final By usernameInput = By.name("name");
    private final By emailInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordInput = By.name("Пароль");
    private final By registerButton = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx");
    private final By loginLink = By.cssSelector(".Auth_link__1fOlj");

    // Методы для взаимодействия с элементами
    public void open() {
        // URL страницы регистрации
        String url = "https://stellarburgers.nomoreparties.site/register";
        driver.get(url);
    }

    public void setUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void submitRegistration() {
        driver.findElement(registerButton).click();
    }

    public void goToLoginPage() {
        driver.findElement(loginLink).click();
    }
}

