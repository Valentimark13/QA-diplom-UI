import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pom.MainPage;
import pom.PersonalPage;
import pom.RegistrationPage;
import pom.ResetPasswordPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public abstract class AbstractTests {
    protected PersonalPage personalPage;
    protected MainPage mainPage;
    protected RegistrationPage registrationPage;
    protected WebDriver driver;
    protected ResetPasswordPage resetPasswordPage;
    private static final String DEFAULT_BROWSER = "chrome";

    @Before
    public void setUp() throws IOException {
        if(Objects.equals(getBrowserFromProperties(), DEFAULT_BROWSER)) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        registrationPage = new RegistrationPage(driver);
        personalPage = new PersonalPage(driver);
        mainPage = new MainPage(driver);
        resetPasswordPage = new ResetPasswordPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String getBrowserFromProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream("config.properties");
        properties.load(inputStream);
        return properties.getProperty("browser", DEFAULT_BROWSER);
    }
}
