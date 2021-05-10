package Class1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework1 {

    @Test
    public void launchWebPage() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        String url = "https://trello.com/";
        driver.get(url);

        driver.manage().window().maximize();

        String pageTitle = driver.getTitle();
        String expPageTitle = "Trello";
        Assert.assertEquals(pageTitle, expPageTitle, "Page title is not appearing as expected");

        String pageUrl = driver.getCurrentUrl();
        Assert.assertEquals(url, pageUrl, "Page url is not as expected");

        driver.close();


    }
}
