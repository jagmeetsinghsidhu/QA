import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.lang.System;

public class Main {


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/jagmeetsidhu/Downloads/chromedriver_mac_arm64/chromedriver");

        long implicitWaitTime = 5; // in seconds
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);

        driver.get("http://localhost:8080/login");

        WebElement usernameField = driver.findElement(By.cssSelector("input[name='username']"));
        usernameField.sendKeys("tomsmith");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();

        assertEquals("clicked: the-internet", alertText);

        WebElement source = driver.findElement(By.cssSelector("div[id='column-a']"));




        // Maximize the browser window
        driver.manage().window().maximize();

        // Declare variables for elements that are used multiple times

        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));

        // Use navigate().to method to navigate to different URLs
        driver.navigate().to("http://localhost:8080/login");
        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword!");
        loginButton.click();
        driver.navigate().to("http://localhost:8080/checkboxes");

        // Use findElements to find multiple elements and iterate through the list
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }

        driver.navigate().to("http://localhost:8080/context_menu");

        WebElement box = driver.findElement(By.cssSelector("div[class='context-menu-one btn']"));

        Actions actions = new Actions(driver);
        actions.contextClick(box).perform();

        WebElement menuItem = driver.findElement(By.cssSelector("li[class='context-menu-item'] a[href='#the-internet']"));
        menuItem.click();

        // Use the Alert class to handle alerts
        driver = new ChromeDriver();
        // Navigate to a page with an alert
        driver.get("http://localhost:8080/javascript_alerts");

        // Click the button that triggers the alert
        WebElement alertButton = driver.findElement(By.cssSelector("button[onclick='jsConfirm()']"));
        alertButton.click();

        // Switch to the alert and get the text
        alert = driver.switchTo().alert();
        alertText = alert.getText();
        alert.dismiss();
        alertText = alert.getText();

        // Use assertions to verify the alert text
        assertEquals("clicked: the-internet", alertText);

        driver.navigate().to("http://localhost:8080/drag_and_drop");

        source = driver.findElement(By.cssSelector("div[id='column-a']"));
        WebElement target = driver.findElement(By.cssSelector("div[id='column-b']"));


        actions.dragAndDrop(source, target).perform();

        driver.get("http://localhost:8080/dropdown");

        WebElement dropdown = driver.findElement(By.cssSelector("select[id='dropdown']"));

        Select select = new Select(dropdown);
        select.selectByVisibleText("Option 1");


        driver.get("http://localhost:8080/dynamic_content");

        WebElement element1 = driver.findElement(By.cssSelector("div[id='content'] div:nth-child(1) h3"));
        String text1 = element1.getText();

        WebElement element2 = driver.findElement(By.cssSelector("div[id='content'] div:nth-child(2) h3"));
        String text2 = element2.getText();

        driver.navigate().refresh();

        WebElement element1Updated = driver.findElement(By.cssSelector("div[id='content'] div:nth-child(1) h3"));
        String text1Updated = element1Updated.getText();

        WebElement element2Updated = driver.findElement(By.cssSelector("div[id='content'] div:nth-child(2) h3"));
        String text2Updated = element2Updated.getText();

        driver.get("http://localhost:8080/dynamic_controls");

        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        WebElement button = driver.findElement(By.cssSelector("button[type='button']"));


        int timeout = 2; // timeout in seconds

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        checkbox.click();
        button.click();

        WebElement message = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#message")));

        driver.get("http://localhost:8080/dynamic_loading/2");

        WebElement startButton = driver.findElement(By.cssSelector("button[type='button']"));
        startButton.click();


        driver.get("http://localhost:8/dynamic_loading/2");

        startButton.click();
        timeout = 10; // timeout in seconds

        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        WebElement loading = driver.findElement(By.cssSelector("#loading"));
        wait.until(ExpectedConditions.invisibilityOf(loading));

        WebElement finish = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish")));

        driver.get("http://localhost:8080/download");

        WebElement link = driver.findElement(By.cssSelector("a[href='download/some-file.txt']"));
        link.click();

        driver.get("http://localhost:8080/upload");

        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys("/path/to/your/file.txt");

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        driver.get("http://localhost:8080/floating_menu");

        WebElement menu = driver.findElement(By.cssSelector("div[id='menu']"));


        actions.moveToElement(menu).perform();

        driver.get("http://localhost:8080/iframe");

        driver.switchTo().frame(0);

        WebElement element = driver.findElement(By.cssSelector("body"));

        driver.get("http://localhost:8080/hovers");

        WebElement image1 = driver.findElement(By.cssSelector("div[class='figure']:nth-child(1) img"));


        actions.moveToElement(image1).perform();

        driver.get("http://localhost:8080/javascript_alerts");
       button = driver.findElement(By.cssSelector("button[onclick='jsConfirm()']"));
        button.click();

        alert = driver.switchTo().alert();
        alert.accept();

        driver.get("http://localhost:8080/javascript_error");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("invalid code");

        Logs logs = driver.manage().logs();
        List<LogEntry> logEntries = logs.get(LogType.BROWSER).getAll();

        for (LogEntry entry : logEntries) {
            System.out.println(entry.getLevel() + ": " + entry.getMessage());
        }

        driver.get("http://localhost:8080/windows");
         link = driver.findElement(By.cssSelector("a[href='/windows/new']"));
        link.sendKeys(Keys.CONTROL + "t");
        link.click();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(windowHandle);
            }
        }
        driver.get("http://localhost:8080/notification_message_rendered");
        button = driver.findElement(By.cssSelector("button[onclick='myFunction()']"));
        button.click();

        timeout = 10; // timeout in seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#flash")));

        assertEquals("The notification message was displayed", "Action successful", message.getText());
    }


}