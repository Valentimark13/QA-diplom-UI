package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // URL страницы регистрации
    public final String url = "https://stellarburgers.nomoreparties.site/";

    private final By signInButton = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");

    private final By personalPage = By.xpath("//*[@id=\"root\"]/div/header/nav/a");

    private final By constructorElement = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a");
    private final By logoElement = By.cssSelector(".AppHeader_header__logo__2D0X2");

    private final By ingredientSections = By.cssSelector(".tab_tab__1SPyG");

    public void signInClick() {
        driver.findElement(signInButton).click();
    }

    public void goToPersonalPage() {
        driver.findElement(personalPage).click();
    }

    public void open() {
        // URL страницы регистрации
        driver.get(url);
    }

    public void clickToConstructor() {
        driver.findElement(constructorElement).click();
    }

    public void clickToLogo() {
        driver.findElement(logoElement).click();
    }
    public WebElement getSectionHeader(Integer index) {
        return driver.findElements(ingredientSections).get(index);
    }
}
