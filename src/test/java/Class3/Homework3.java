package Class3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework3 {
    @Test
    public void feelsLikeTemp() {
       System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

       WebDriver driver = new ChromeDriver();
       driver.get("https://darksky.net/forecast/40.7127,-74.0059/us12/en");

        WebElement flTempValue = driver.findElement(By.className("feels-like-text"));
        String flTempValueWithDegree = flTempValue.getText();
        String flTempValueNoDegree = flTempValueWithDegree.substring(0, flTempValueWithDegree.length()-1);
        int flTemp = Integer.parseInt(flTempValueNoDegree);

        WebElement lowTempValue = driver.findElement(By.className("low-temp-text"));
        String lowTempValueWithDegree = lowTempValue.getText();
        String lowTempValueNoDegree = lowTempValueWithDegree.substring(0, lowTempValueWithDegree.length()-1);
        int lowTemp = Integer.parseInt(lowTempValueNoDegree);

        WebElement highTempValue = driver.findElement(By.className("high-temp-text"));
        String highTempValueWithDegree = highTempValue.getText();
        String highTempValueNoDegree = highTempValueWithDegree.substring(0, highTempValueWithDegree.length()-1);
        int highTemp = Integer.parseInt(highTempValueNoDegree);

        if (flTemp >= lowTemp && flTemp <= highTemp) {
            System.out.println("FeelsLike temp " + flTemp + " is greater than Low temp " + lowTemp + " and less than High temp " + highTemp);
        } else if (flTemp < lowTemp){
            System.out.println("FeelsLike temp " + flTemp + " is less than Low temp " + lowTemp);
        } else if (flTemp > highTemp){
            System.out.println("FeelsLike temp " + flTemp + " is greater than High temp " + highTemp);
        }
        driver.close();
    }

    @Test
    public void convertedTemp() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://darksky.net/forecast/40.7127,-74.0059/us12/en");

        WebElement flTempValue = driver.findElement(By.className("feels-like-text"));
        String flTempValueWithDegree = flTempValue.getText();
        String flTempValueNoDegree = flTempValueWithDegree.substring(0, flTempValueWithDegree.length()-1);
        int flTempF = Integer.parseInt(flTempValueNoDegree);

        driver.findElement(By.xpath("(//*[@class='button'])[1]")).click();
        driver.findElement(By.xpath("(//*[@class='last'])[1]")).click();

        long expectedTempC = (flTempF - 32) * 5 / 9;
        int roundExpectedTempC = Math.round(expectedTempC);

        WebElement flTempValueC = driver.findElement(By.className("feels-like-text"));
        String flTempValueWithDegreeC = flTempValueC.getText();
        String flTempValueNoDegreeC = flTempValueWithDegreeC.substring(0, flTempValueWithDegree.length()-1);
        int actualTempC = Integer.parseInt(flTempValueNoDegreeC);

        Assert.assertEquals(actualTempC, roundExpectedTempC, "Temp value is not converted as expected");
        System.out.println("Temperature " + flTempF + "˚F is converted to Temperature " + actualTempC + "˚C as expected");
        driver.close();
    }

    @Test
    public void genderErrorDisplayed() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://facebook.com/");

        driver.findElement(By.linkText("Create New Account")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.name("firstname")).sendKeys("First");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.name("lastname")).sendKeys("Last");
        driver.findElement(By.name("reg_email__")).sendKeys("9898787800");
        driver.findElement(By.name("reg_passwd__")).sendKeys("passw0rd@123");
        driver.findElement(By.name("websubmit")).click();
        driver.findElement(By.xpath("//i[starts-with(@class, '_5dbc _8esb')]")).click();
        String expGenderErrText = "Please choose a gender. You can change who can see this later.";
        String actualGenderErrText = driver.findElement(By.xpath("//div[contains(text(), 'gender') and starts-with(@style, 'top')]")).getText();
        Assert.assertEquals(expGenderErrText, actualGenderErrText, "Text is not as expected");
        driver.close();
    }

}
