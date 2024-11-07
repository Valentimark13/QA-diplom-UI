import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

import io.qameta.allure.Step;

public class ConstructorTests extends AbstractTests {
    @Test
    @DisplayName("Проверка выбора раздела соуса")
    public void testSelectSauceSection() {
        mainPage.open();
        mainPage.selectHeader(1);
        verifySauceSectionSelected();
    }

    @Test
    @DisplayName("Проверка активного раздела булки по умолчанию при открытии сайта")
    public void testSelectBreadSection() {
        mainPage.open();
        verifyBreadSectionSelected();
    }

    @Test
    @DisplayName("Проверка выбора раздела начинка")
    public void testSelectFillingSection() {
        mainPage.open();
        mainPage.selectHeader(2);
        verifyFillingSectionSelected();
    }

    @Step("Проверяю, что раздел соусов выбран")
    private void verifySauceSectionSelected() {
        Assert.assertTrue(mainPage.isActiveSection(1));
        Assert.assertTrue(mainPage.isNotActiveSection(0));
        Assert.assertTrue(mainPage.isNotActiveSection(2));
    }

    @Step("Проверяю, что раздел хлеба выбран")
    private void verifyBreadSectionSelected() {
        Assert.assertTrue(mainPage.isActiveSection(0));
        Assert.assertTrue(mainPage.isNotActiveSection(1));
        Assert.assertTrue(mainPage.isNotActiveSection(2));
    }

    @Step("Проверяю, что раздел начинки выбран")
    private void verifyFillingSectionSelected() {
        Assert.assertTrue(mainPage.isActiveSection(2));
        Assert.assertTrue(mainPage.isNotActiveSection(0));
        Assert.assertTrue(mainPage.isNotActiveSection(1));
    }
}
