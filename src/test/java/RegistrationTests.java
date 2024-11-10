import dto.UserDTO;
import helper.Generator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import io.qameta.allure.Step;
import services.UserService;

public class RegistrationTests extends AbstractTests {
    private UserDTO user;
    @Override
    public void setUp() throws IOException {
        super.setUp();
        String username = Generator.generateRandomString(8);
        String email = Generator.generateRandomString(6) + "@example.com";
        String password = Generator.generateRandomString(10);
        user = new UserDTO(email, password, username);
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void testSuccessfulRegistration() {
        openRegistrationPage();
        fillOutRegistrationForm();
        submitRegistration();
        assertOnLoginPage();

        try {
            deleteUser();
        } catch (Exception ignored) {
        }
    }

    @Step("Удаление пользователя")
    private void deleteUser() {
        UserService service = new UserService();
        String token = service.login(user);
        service.deleteUser(token);
    }

    @Test
    @DisplayName("Проверка обработки слишком короткого пароля")
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
        registrationPage.setUsername(user.name);
        registrationPage.setEmail(user.email);
        registrationPage.setPassword(user.password);
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
        registrationPage.setUsername(user.name);
        registrationPage.setEmail(user.password);
    }

    @Step("Проверяю наличие сообщения об ошибке")
    private void assertErrorMessage() {
        assertTrue(driver.getPageSource().contains("Некорректный пароль"));
    }
}
