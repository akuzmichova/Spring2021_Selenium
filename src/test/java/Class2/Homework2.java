package Class2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework2 {
    @Test
    public void loginAndPassword() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://facebook.com/");

        String emailId = "email";
        By emaillocator = By.id(emailId);
        WebElement emailBox = driver.findElement(emaillocator);
        emailBox.sendKeys("myemail@gmail.com");

        String passId = "pass";
        By passlocator = By.id(passId);
        WebElement passBox = driver.findElement(passlocator);
        passBox.sendKeys("passw0rd");

        By loginlocator = By.name("login");
        WebElement loginButton = driver.findElement(loginlocator);
        loginButton.click();

        driver.close();
    }

    @Test
    public void createAccount() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://facebook.com/");

        String createAccountText = "Create New Account";
        By accountTextLocator = By.linkText(createAccountText);
        WebElement createAccountButton = driver.findElement(accountTextLocator);
        createAccountButton.click();

        By fnamelocator = By.name("firstname");
        WebElement fNameBox = driver.findElement(fnamelocator);
        fNameBox.sendKeys("First");

        By lnamelocator = By.name("lastname");
        WebElement lNameBox = driver.findElement(lnamelocator);
        lNameBox.sendKeys("Last");

        By mobNumlocator = By.name("reg_email__");
        WebElement mobNumBox = driver.findElement(mobNumlocator);
        mobNumBox.sendKeys("9898787800");

        By newPasslocator = By.name("reg_passwd__");
        WebElement newPassBox = driver.findElement(newPasslocator);
        newPassBox.sendKeys("passw0rd@123");

        By signUplocator = By.name("websubmit");
        WebElement signUpButton = driver.findElement(signUplocator);
        signUpButton.click();

        driver.close();

    }
}

