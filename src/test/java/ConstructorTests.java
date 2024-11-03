import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.MainPage;
import java.time.Duration;
import io.qameta.allure.Step;

public class ConstructorTests {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSelectHeaderSelection() {
        mainPage.open();

        selectHeader(1); // Выбор соусов
        verifyHeaders(1); // Проверка соусов

        selectHeader(0); // Выбор хлеба
        verifyHeaders(0); // Проверка хлеба

        selectHeader(2); // Выбор начинки
        verifyHeaders(2); // Проверка начинки
    }

    @Step("Выбираю раздел заголовка: {index}")
    private void selectHeader(int index) {
        WebElement header = mainPage.getSectionHeader(index);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(header)).click();
    }

    @Step("Проверяю заголовки для индекса: {index}")
    private void verifyHeaders(int index) {
        WebElement bunsHeader = mainPage.getSectionHeader(0);
        WebElement saucesHeader = mainPage.getSectionHeader(1);
        WebElement fillingHeader = mainPage.getSectionHeader(2);

        if (index == 0) { // Хлеб выбран
            Assert.assertTrue(bunsHeader.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
            Assert.assertFalse(saucesHeader.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
            Assert.assertFalse(fillingHeader.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
        } else if (index == 1) { // Соусы выбраны
            Assert.assertFalse(bunsHeader.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
            Assert.assertTrue(saucesHeader.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
            Assert.assertFalse(fillingHeader.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
        } else if (index == 2) { // Начинка выбрана
            Assert.assertFalse(bunsHeader.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
            Assert.assertFalse(saucesHeader.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
            Assert.assertTrue(fillingHeader.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
        }
    }
}
