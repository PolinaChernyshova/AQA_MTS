import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class MtsTest {
    static WebDriver driver;
    @BeforeClass

    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mts.by");
        WebElement cookieButton = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        cookieButton.click();
    }

    @Test
    //проверка названия блока
    public void checkBlockNameTest() {
        WebElement blockTitle = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2"));
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        Assert.assertEquals(blockTitle.getText(), expectedTitle);
    }

    @Test
    //проверка наличия логотипов платежных систем
    public void checkPaySysLogoTest() {
        WebElement visaLogo = driver.findElement(By.xpath("//*[@alt='Visa']"));
        assertTrue(visaLogo.isDisplayed());
        WebElement verifiedVisaLogo = driver.findElement(By.xpath("//*[@alt='Verified By Visa']"));
        assertTrue(verifiedVisaLogo.isDisplayed());
        WebElement masterCardLogo = driver.findElement(By.xpath("//*[@alt='MasterCard']"));
        assertTrue(masterCardLogo.isDisplayed());
        WebElement masterCardSCLogo = driver.findElement(By.xpath("//*[@alt='MasterCard Secure Code']"));
        assertTrue(masterCardSCLogo.isDisplayed());
        WebElement belCardLogo = driver.findElement(By.xpath("//*[@alt='Белкарт'] "));
        assertTrue(belCardLogo.isDisplayed());
    }
    @Test
    //проверка работы ссылки "Подробнее о сервисе"
    public void checkInfoLinkTest() {
        WebElement infoLink = driver.findElement(By.xpath("//div[@class='pay__wrapper']/a"));
        infoLink.click();
        assertTrue(driver.getCurrentUrl().contains("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));
        WebElement headerLogo = driver.findElement(By.xpath("//div[@class='page-header__top']/a"));
        headerLogo.click();
    }

    @Test
    //проверка работы кнопки "Продолжить" при заполненных обязательных полях
    public void checkSubmitFormButton() {
        WebElement serviceDropdown = driver.findElement(By.xpath("//button[@class='select__header']"));
        serviceDropdown.click();
        WebElement communicationServices = driver.findElement(By.xpath("//p[text()='Услуги связи']"));
        communicationServices.click();
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@id='connection-phone']"));
        phoneNumberField.sendKeys("297777777");
        WebElement sumField = driver.findElement(By.xpath("//input[@id='connection-sum']"));
        sumField.sendKeys("100");
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Продолжить']"));
        submitButton.click();
    }
}