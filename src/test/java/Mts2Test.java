import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Mts2Test {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mts.by");
        WebElement cookieButton = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        cookieButton.click();
    }

    //Проверка надписей в незаполненных полях каждого варианта оплаты услуг: 1.1 услуги связи
    @Test
    public void testCheckCommunicationServicesFields() {
        WebElement serviceDropdown = driver.findElement(By.xpath("//button[@class='select__header']"));
        serviceDropdown.click();
        WebElement communicationDropdown = driver.findElement(By.xpath("//p[text()='Услуги связи']"));
        communicationDropdown.click();
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@class='phone']"));
        String expectedResult1 = "Номер телефона";
        String phoneNumberText = phoneNumberField.getAttribute("placeholder");
        assertEquals(expectedResult1, phoneNumberText);
        WebElement sumField = driver.findElement(By.xpath("//input[@class='total_rub']"));
        String expectedResult2 = "Сумма";
        String sumFieldText = sumField.getAttribute("placeholder");
        assertEquals(expectedResult2, sumFieldText);
        WebElement emailField = driver.findElement(By.xpath("//input[@class='email']"));
        String expectedResult3 = "E-mail для отправки чека";
        String emailFieldText = emailField.getAttribute("placeholder");
        assertEquals(expectedResult3, emailFieldText);
    }

    //Проверка надписей в незаполненных полях каждого варианта оплаты услуг: 1.2 домашний интернет
    @Test
    public void testPhoneInternetFields() {
        WebElement serviceDropdown = driver.findElement(By.xpath("//button[@class='select__header']"));
        serviceDropdown.click();
        WebElement homeInternetDropdown = driver.findElement(By.xpath("//p[text()='Домашний интернет']"));
        homeInternetDropdown.click();
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@id ='internet-phone']"));
        String expectedResult1 = "Номер абонента";
        String phoneNumberText = phoneNumberField.getAttribute("placeholder");
        assertEquals(expectedResult1, phoneNumberText);
        WebElement sumField = driver.findElement(By.xpath("//input[@id ='internet-sum']"));
        String expectedResult2 = "Сумма";
        String sumFieldText = sumField.getAttribute("placeholder");
        assertEquals(expectedResult2, sumFieldText);
        WebElement emailField = driver.findElement(By.xpath("//input[@id ='internet-email']"));
        String expectedResult3 = "E-mail для отправки чека";
        String emailFieldText = emailField.getAttribute("placeholder");
        assertEquals(expectedResult3, emailFieldText);
    }

    //Проверка надписей в незаполненных полях каждого варианта оплаты услуг: 1.3 рассрочка
    @Test
    public void testInstallmentFields() {
        WebElement serviceDropdown = driver.findElement(By.xpath("//button[@class='select__header']"));
        serviceDropdown.click();
        WebElement installmentDropdown = driver.findElement(By.xpath("//p[text()='Рассрочка']"));
        installmentDropdown.click();
        WebElement scoreInstallmentField = driver.findElement(By.xpath("//input[@id = 'score-instalment']"));
        String expectedResult1 = "Номер счета на 44";
        String scoreInstallmentText = scoreInstallmentField.getAttribute("placeholder");
        assertEquals(expectedResult1, scoreInstallmentText);
        WebElement sumField = driver.findElement(By.xpath("//input[@id = 'instalment-sum']"));
        String expectedResult2 = "Сумма";
        String sumFieldText = sumField.getAttribute("placeholder");
        assertEquals(expectedResult2, sumFieldText);
        WebElement emailField = driver.findElement(By.xpath("//input[@id = 'instalment-email']"));
        String expectedResult3 = "E-mail для отправки чека";
        String emailFieldText = emailField.getAttribute("placeholder");
        assertEquals(expectedResult3, emailFieldText);
    }

    //Проверка надписей в незаполненных полях каждого варианта оплаты услуг: 1.4 задолженность
    @Test
    public void testDebtFields() {
        WebElement serviceDropdown = driver.findElement(By.xpath("//button[@class='select__header']"));
        serviceDropdown.click();
        WebElement debtDropdown = driver.findElement(By.xpath("//p[text() = 'Задолженность']"));
        debtDropdown.click();
        WebElement scoreNumberField = driver.findElement(By.xpath("//input[@id = 'score-arrears']"));
        String expectedResult1 = "Номер счета на 2073";
        String scoreNumberText = scoreNumberField.getAttribute("placeholder");
        assertEquals(expectedResult1, scoreNumberText);
        WebElement sumField = driver.findElement(By.xpath("//input[@id = 'arrears-sum']"));
        String expectedResult2 = "Сумма";
        String sumFieldText = sumField.getAttribute("placeholder");
        assertEquals(expectedResult2, sumFieldText);
        WebElement emailField = driver.findElement(By.xpath("//input[@id = 'arrears-email']"));
        String expectedResult3 = "E-mail для отправки чека";
        String emailFieldText = emailField.getAttribute("placeholder");
        assertEquals(expectedResult3, emailFieldText);
    }

    /*Для варианта «Услуги связи» заполнить поля
    в соответствии с пререквизитами из предыдущей темы,
    нажать кнопку «Продолжить» и в появившемся окне проверить
    корректность отображения суммы (в том числе на кнопке), номера телефона,
    а также надписей в незаполненных полях для ввода реквизитов карты,
    наличие иконок платёжных систем
     */
    @Test
    public void testCheckCommunicationServicesFillFields() {
        WebElement serviceDropdown = driver.findElement(By.xpath("//button[@class='select__header']"));
        serviceDropdown.click();
        WebElement communicationDropdown = driver.findElement(By.xpath("//p[text()='Услуги связи']"));
        communicationDropdown.click();
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@class='phone']"));
        phoneNumberField.sendKeys("297777777");
        WebElement sumField = driver.findElement(By.xpath("//input[@class='total_rub']"));
        sumField.sendKeys("100");
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Продолжить']"));
        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']")));
        driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']"))));
        WebElement payDescriptionCost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pay-description__cost']")));
        String expectedPayDescriptionCost = "100.00 BYN";
        String actualPayDescriptionCost = payDescriptionCost.getText();
        assertEquals(expectedPayDescriptionCost, actualPayDescriptionCost);
        WebElement payButton = driver.findElement(By.xpath("//button[@class='colored disabled']"));
        String expectedPayButtonText = "Оплатить 100.00 BYN";
        String PayButtonText = payButton.getText();
        assertEquals(expectedPayButtonText, PayButtonText);
        WebElement phoneNumber = driver.findElement(By.xpath("//span[@class='pay-description__text']"));
        String expectedPhoneNumber = "Оплата: Услуги связи Номер:375297777777";
        String actualPhoneNumber = phoneNumber.getText();
        assertEquals(expectedPhoneNumber, actualPhoneNumber);
        WebElement cardNumberField = driver.findElement(By.xpath("//label[text () = 'Номер карты']"));
        String expectedCardNumber = "Номер карты";
        String actualCardNumber = cardNumberField.getText();
        assertEquals(expectedCardNumber, actualCardNumber);
        WebElement validityField = driver.findElement(By.xpath("//label[text () = 'Срок действия']"));
        String expectedValidity = "Срок действия";
        String actualValidity = validityField.getText();
        assertEquals(expectedValidity, actualValidity);
        WebElement cvcField = driver.findElement(By.xpath("//label[text () = 'CVC']"));
        String expectedCVC = "CVC";
        String actualCVC = cvcField.getText();
        assertEquals(expectedCVC, actualCVC);
        WebElement ownerNameField = driver.findElement(By.xpath("//label[text () = 'Имя держателя (как на карте)']"));
        String expectedOwnerName = "Имя держателя (как на карте)";
        String actualOwnerName = ownerNameField.getText();
        assertEquals(expectedOwnerName, actualOwnerName);
        WebElement masterCardLogo = driver.findElement(By.xpath("//img[@class = 'ng-tns-c61-0 ng-star-inserted']"));
        assertTrue(masterCardLogo.isDisplayed());
        WebElement visaLogo = driver.findElement(By.xpath("//img[@class='ng-tns-c61-0 ng-star-inserted']"));
        assertTrue(visaLogo.isDisplayed());
        WebElement belCartLogo = driver.findElement(By.xpath("//img[@class='ng-tns-c61-0 ng-star-inserted']"));
        assertTrue(belCartLogo.isDisplayed());
        WebElement mirCardLogo = driver.findElement(By.xpath("//div[@class='cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted']"));
        assertTrue(mirCardLogo.isDisplayed());
        WebElement googlePayLogo = driver.findElement(By.xpath("//button[@id='gpay-button-online-api-id']"));
        assertTrue(googlePayLogo.isDisplayed());
        WebElement yandexPayLogo = driver.findElement(By.xpath("//div[@id='yandex-button']"));
        assertTrue(yandexPayLogo.isDisplayed());
    }
    @AfterClass
    static public void closeChrome() {
        driver.quit();
    }
}