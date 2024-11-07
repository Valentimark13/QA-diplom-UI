import helper.Generator;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import io.qameta.allure.Step;

public class RegistrationTests extends AbstractTests {

    @Test
    public void testSuccessfulRegistration() {
        openRegistrationPage();
        fillOutRegistrationForm();
        submitRegistration();
        assertOnLoginPage();
    }

    @Test
    public void testPasswordTooShort() {
        openRegistrationPage();
        setShortPassword();

        // Проверка наличия сообщения об ошибке
        assertErrorMessage();
    }

    @Step("Открываю страницу регистрации")
    private void openRegistrationPage() {
        registrationPage.open();
    }

    @Step("Заполняю форму регистрации")
    private void fillOutRegistrationForm() {
        registrationPage.setUsername(Generator.generateRandomString(8));
        registrationPage.setEmail(Generator.generateRandomString(6) + "@example.com");
        registrationPage.setPassword(Generator.generateRandomString(10));
    }

    @Step("Отправляю регистрацию")
    private void submitRegistration() {
        registrationPage.submitRegistration();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Step("Проверяю, что перенаправлено на страницу входа")
    private void assertOnLoginPage() {
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Step("Устанавливаю слишком короткий пароль")
    private void setShortPassword() {
        registrationPage.setPassword(Generator.generateRandomString(4));
        registrationPage.setUsername(Generator.generateRandomString(8));
        registrationPage.setEmail(Generator.generateRandomString(6) + "@example.com");
    }

    @Step("Проверяю наличие сообщения об ошибке")
    private void assertErrorMessage() {
        assertTrue(driver.getPageSource().contains("Некорректный пароль"));
    }
}
