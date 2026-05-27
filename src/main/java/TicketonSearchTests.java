import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TicketonSearchTests {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String baseUrl = "https://ticketon.kz/";

        try {
            System.out.println("Запуск UI автотестов для модуля 'Глобальный поиск'...");
            testValidSearch(driver, wait, baseUrl);
            testEmptySearch(driver, wait, baseUrl);
            testSpecialCharsSearch(driver, wait, baseUrl);
        } finally {
            driver.quit();
        }
    }

    // Тест 1
    public static void testValidSearch(WebDriver driver, WebDriverWait wait, String baseUrl) {
        driver.get(baseUrl);
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='search']")));
        searchInput.sendKeys("Концерт");
        searchInput.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.urlContains("search"));
        System.out.println("Тест 1 (Валидный поиск): ПРОЙДЕН");
    }

    // Тест 2
    public static void testEmptySearch(WebDriver driver, WebDriverWait wait, String baseUrl) {
        driver.get(baseUrl);
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='search']")));
        searchInput.sendKeys(Keys.ENTER);
        if (!driver.getCurrentUrl().contains("search")) {
            System.out.println("Тест 2 (Пустой поиск): ПРОЙДЕН");
        } else {
            System.out.println("Тест 2: ПРОВАЛЕН (Осуществлен переход по пустому запросу)");
        }
    }

    // Тест 3
    public static void testSpecialCharsSearch(WebDriver driver, WebDriverWait wait, String baseUrl) {
        driver.get(baseUrl);
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='search']")));
        searchInput.sendKeys("<script>alert('test')</script>");
        searchInput.sendKeys(Keys.ENTER);
        try {
            WebElement noResults = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Ничего не найдено')]")));
            if (noResults.isDisplayed()) {
                System.out.println("Тест 3 (Безопасность поля): ПРОЙДЕН");
            }
        } catch (Exception e) {
            System.out.println("Тест 3: ПРОВАЛЕН (Некорректная обработка спецсимволов или системная ошибка)");
        }
    }
}