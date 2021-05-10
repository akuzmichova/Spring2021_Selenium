package Class1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LabClass1 {


        @Test
        public void practical() {
            System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

            WebDriver driver = new ChromeDriver();
            driver.get("https://facebook.com/");
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            // enter email as abcd@test.com
            String emailId = "email";
            By emaillocator = By.id(emailId);
            WebElement emailBox = driver.findElement(emaillocator);
            emailBox.sendKeys("abcd@test.com");

            //OR
            driver.findElement(By.id("email")).sendKeys("abcd@test.com");

            //enter password as abcd@1234
            String passName = "pass";
            By passlocator = By.name(passName);
            WebElement passBox = driver.findElement(passlocator);
            passBox.sendKeys("abcd@test.com");

            String passClass = "_6ltj";
            By passwordLocator_2 = By.className(passClass);
            WebElement passBox_2 = driver.findElement(passwordLocator_2);
            passBox_2.sendKeys("abcd@1234");

            String createAccountText = "Create New Account";
            By accountTextLocator = By.linkText(createAccountText);
            WebElement createAccountButton = driver.findElement(accountTextLocator);
            createAccountButton.click();

            String createAccountPartialText = "New Acc";
            By createAccountLocator_2 = By.partialLinkText(createAccountPartialText);
            WebElement createAccountButton_2 = driver.findElement(createAccountLocator_2);
            createAccountButton_2.click();


        }


    }

