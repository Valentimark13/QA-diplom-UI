import dto.UserDTO;
import helper.Generator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import io.qameta.allure.Step;
import services.UserService;

import java.io.IOException;

public class PersonalPageTests extends AbstractTests {
    private UserDTO user;

    @Override
    public void setUp() throws IOException {
        super.setUp();

        String username = Generator.generateRandomString(8);
        String email = Generator.generateRandomString(6) + "@example.com";
        String password = Generator.generateRandomString(10);
        user = new UserDTO(email, password, username);
        personalPage.open(user);
    }

    @Override
    public void tearDown() {
        super.tearDown();
        String token = (new UserService()).login(user);
        (new UserService()).deleteUser(token);
    }

    @Test
    @DisplayName("Проверка, открылся ли личный кабинет")
    public void testPersonalPage() {
        assertTrue(driver.getCurrentUrl().contains("/account/profile"));
    }

    @Test
    @DisplayName("Проверка, что заходит в конструктор через кнопку конструктор")
    public void testGoToConstructor() {
        personalPage.goToConstructor();
        assertTrue(driver.getCurrentUrl().equals(mainPage.url));
    }

    @Test
    @DisplayName("Проверка перехода из ЛК на главную страницу через логотип")
    public void testGoToMainPage() {
        personalPage.goToMainPage();
        assertTrue(driver.getCurrentUrl().equals(mainPage.url));
    }

    @Test
    @DisplayName("Проврка логаута из личного кабинета")
    public void testLogout() {
        personalPage.logout();
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}
