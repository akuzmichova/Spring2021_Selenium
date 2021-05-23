package Class4;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Homework4 {
    @Test
    public void sameLowHighTemp() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://darksky.net/forecast/40.7127,-74.0059/us12/en");
        WebElement todayTL = driver.findElement(By.xpath("//*[contains(text(), 'Today')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", todayTL);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement plusButton = driver.findElement(By.xpath("//a[@data-day='0']/span[@class='toggle']/span[@class='open']"));
        plusButton.click();

        WebElement todayMinTemp = driver.findElement(By.xpath("//a[@class='day revealed']//span[@class='tempRange']//span[@class='minTemp']"));
        String todayMinTempWithDegree = todayMinTemp.getText();
        String todayMinTempNoDegree = todayMinTempWithDegree.substring(0, todayMinTempWithDegree.length()-1);

        WebElement minTemp = driver.findElement(By.xpath("(//div[@class='dayDetails revealed']//span[@class='temp'])[1]"));
        String minTempWithDegree = minTemp.getText();
        String minTempNoDegree = minTempWithDegree.substring(0, minTempWithDegree.length()-1);
        Assert.assertEquals(todayMinTempNoDegree, minTempNoDegree, "Low TempValue on timeline is NOT the same as Low tempValue in the Today's detail");

        WebElement todayMaxTemp = driver.findElement(By.xpath("//a[@class='day revealed']//span[@class='tempRange']//span[@class='maxTemp']"));
        String todayMaxTempWithDegree = todayMaxTemp.getText();
        String todayMaxTempNoDegree = todayMaxTempWithDegree.substring(0, todayMaxTempWithDegree.length()-1);

        WebElement maxTemp = driver.findElement(By.xpath("(//div[@class='dayDetails revealed']//span[@class='temp'])[2]"));
        String maxTempWithDegree = maxTemp.getText();
        String maxTempNoDegree = maxTempWithDegree.substring(0, maxTempWithDegree.length()-1);
        Assert.assertEquals(todayMaxTempNoDegree, maxTempNoDegree, "High TempValue on timeline is NOT the same as High tempValue in the Today's detail");
        driver.close();
    }

        @Test
        public void isCurrentDateSelected() {
            System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://darksky.net/forecast/40.7127,-74.0059/us12/en");
            WebElement buttonTM = driver.findElement(By.xpath("//a[@class='button' and contains(text(), 'Time Machine')]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", buttonTM);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buttonTM.click();
            Date currentTimeDate = new Date();
            SimpleDateFormat df = new SimpleDateFormat("d");
            String currentDay = df.format(currentTimeDate);
            WebElement todayDate = driver.findElement(By.xpath("//td[@class='is-today']"));
            String todayDateFromPage = todayDate.getAttribute("data-day");
            Assert.assertEquals(currentDay, todayDateFromPage, "Current date is not selected");
            driver.close();
        }

    @Test
    public void articleDatesFormat() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://darksky.net/forecast/40.7127,-74.0059/us12/en");
        WebElement buttonAPI = driver.findElement(By.xpath("//a[contains(text(), 'Dark Sky API')]"));
        buttonAPI.click();
        WebElement buttonBlogPost = driver.findElement(By.xpath("//a[contains(text(), 'blog post')]"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buttonBlogPost.click();
        By articleDate = By.xpath("//time[contains(@datetime, '31T13')]");
        List<WebElement> allArticleDatesWeb = driver.findElements(articleDate);
        List<Date> allArticleDatesDate = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("MMMM d, yyyy");
        for (WebElement date : allArticleDatesWeb) {
            try {
                Date dateDate = df.parse(date.getText());
                Assert.assertTrue((date.getText().equals(df.format(dateDate))), "Article dates are NOT in correct format");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        driver.close();
    }

    @Test
    public void articleDatesOrder() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://darksky.net/forecast/40.7127,-74.0059/us12/en");
        WebElement buttonAPI = driver.findElement(By.xpath("//a[contains(text(), 'Dark Sky API')]"));
        buttonAPI.click();
        WebElement buttonBlogPost = driver.findElement(By.xpath("//a[contains(text(), 'blog post')]"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buttonBlogPost.click();
        By articleDate = By.xpath("//time[contains(@datetime, '31T13')]");
        List<WebElement> allArticleDatesWeb = driver.findElements(articleDate);
        List<Date> allArticleDatesDate = new ArrayList<>();
        for (WebElement date : allArticleDatesWeb) {
            try {
                Date dateDate = new SimpleDateFormat("MMMM d, yyyy").parse(date.getText());
                allArticleDatesDate.add(dateDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for (int i=0; i<allArticleDatesDate.size()-1; i++) {
            Assert.assertTrue(allArticleDatesDate.get(i).after(allArticleDatesDate.get(i+1)), "Article dates are NOT in reverse chronological order");
        }
        driver.close();
    }

    @Test
    public void maxMenueOptionsSize() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://classroomessentialsonline.com/");

        By cChairs = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Church Chairs')]");
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(cChairs)).build().perform();
        By cChairsOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Church Chairs')]/following-sibling::div//a");
        List<WebElement> churchChairsOptions = driver.findElements(cChairsOptions);

        Map<String, Integer> menuMap = new HashMap<>();
        int cChairSize = churchChairsOptions.size();
        String cChairsString = driver.findElement(cChairs).getText();
        menuMap.put(cChairsString,cChairSize);

        By c1Chairs = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Chiavari Chairs')]");
        act.moveToElement(driver.findElement(c1Chairs)).build().perform();
        By c1ChairsOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Chiavari Chairs')]/following-sibling::div//a");
        List<WebElement> chiavariChairsOptions = driver.findElements(c1ChairsOptions);
        int c1ChairSize = chiavariChairsOptions.size();
        String c1ChairsString = driver.findElement(c1Chairs).getText();
        menuMap.put(c1ChairsString,c1ChairSize);

        By bTables = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Banquet Tables')]");
        act.moveToElement(driver.findElement(bTables)).build().perform();
        By bTablesOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Banquet Tables')]/following-sibling::div//a");
        List<WebElement> banquetTablesOptions = driver.findElements(bTablesOptions);
        int bTablesSize = banquetTablesOptions.size();
        String bTablesString = driver.findElement(bTables).getText();
        menuMap.put(bTablesString,bTablesSize);

        By bChairs = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Banquet Chairs')]");
        act.moveToElement(driver.findElement(bChairs)).build().perform();
        By bChairsOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Banquet Chairs')]/following-sibling::div//a");
        List<WebElement> banquetChairsOptions = driver.findElements(bChairsOptions);
        int bChairsSize = banquetChairsOptions.size();
        String bChairsString = driver.findElement(bChairs).getText();
        menuMap.put(bChairsString,bChairsSize);

        By fChairs = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Folding Chairs')]");
        act.moveToElement(driver.findElement(fChairs)).build().perform();
        By fChairsOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Folding Chairs')]/following-sibling::div//a");
        List<WebElement> foldingChairsOptions = driver.findElements(fChairsOptions);
        int fChairsSize = foldingChairsOptions.size();
        String fChairsString = driver.findElement(fChairs).getText();
        menuMap.put(fChairsString,fChairsSize);

        By fTables = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Folding Tables')]");
        act.moveToElement(driver.findElement(fTables)).build().perform();
        By fTablesOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Folding Tables')]/following-sibling::div//a");
        List<WebElement> foldingTablesOptions = driver.findElements(fTablesOptions);
        int fTablesSize = foldingTablesOptions.size();
        String fTablesString = driver.findElement(fTables).getText();
        menuMap.put(fTablesString,fTablesSize);

        By oFurniture = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Office Furniture')]");
        act.moveToElement(driver.findElement(oFurniture)).build().perform();
        By oFurnitureOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Office Furniture')]/following-sibling::div//a");
        List<WebElement> officeFurnitureOptions = driver.findElements(oFurnitureOptions);
        int oFurnitureSize = officeFurnitureOptions.size();
        String oFurnitureString = driver.findElement(oFurniture).getText();
        menuMap.put(oFurnitureString,oFurnitureSize);

        By sFurniture = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'School Furniture')]");
        act.moveToElement(driver.findElement(sFurniture)).build().perform();
        By sFurnitureOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'School Furniture')]/following-sibling::div//a");
        List<WebElement> schoolFurnitureOptions = driver.findElements(sFurnitureOptions);
        int sFurnitureSize = schoolFurnitureOptions.size();
        String sFurnitureString = driver.findElement(sFurniture).getText();
        menuMap.put(sFurnitureString,sFurnitureSize);

        By rFurniture = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Restaurant Furniture')]");
        act.moveToElement(driver.findElement(rFurniture)).build().perform();
        By rFurnitureOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Restaurant Furniture')]/following-sibling::div//a");
        List<WebElement> restaurantFurnitureOptions = driver.findElements(rFurnitureOptions);
        int rFurnitureSize = restaurantFurnitureOptions.size();
        String rFurnitureString = driver.findElement(rFurniture).getText();
        menuMap.put(rFurnitureString,rFurnitureSize);

        By sChairs = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Stacking Chairs')]");
        act.moveToElement(driver.findElement(sChairs)).build().perform();
        By sChairsOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Stacking Chairs')]/following-sibling::div//a");
        List<WebElement> stackingChairsOptions = driver.findElements(sChairsOptions);
        int sChairsSize = stackingChairsOptions.size();
        String sChairsString = driver.findElement(sChairs).getText();
        menuMap.put(sChairsString,sChairsSize);

        By xChairs = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'X-back Chairs')]");
        act.moveToElement(driver.findElement(xChairs)).build().perform();
        By xChairsOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'X-back Chairs')]/following-sibling::div//a");
        List<WebElement> xBackChairsOptions = driver.findElements(xChairsOptions);
        int xChairsSize = xBackChairsOptions.size();
        String xChairsString = driver.findElement(xChairs).getText();
        menuMap.put(xChairsString,xChairsSize);

        Collection<String> menuTabs = menuMap.keySet();
        int maxValue = 0;
        String maxmenuTab = "";
        for (String tab : menuTabs) {
            if (menuMap.get(tab) > maxValue) {
                maxmenuTab = tab;
                maxValue = menuMap.get(tab);
            }
        }
        Assert.assertEquals("SCHOOL FURNITURE", maxmenuTab, "School Furniture' does not have maximum number of options");
        driver.close();
    }

    @Test
    public void chuchChairsMenueOptionsSize() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://classroomessentialsonline.com/");
        By cChairs = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Church Chairs')]");
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(cChairs)).build().perform();
        By cChairsOptions = By.xpath("//a[starts-with(@class, 'navPages-action') and contains(text(), 'Church Chairs')]/following-sibling::div//a");
        List<WebElement> churchChairsOptions = driver.findElements(cChairsOptions);
        int cChairSize = churchChairsOptions.size();
        Assert.assertEquals(cChairSize, 7, "Church Chairs menu size is not equal to 7");
        driver.close();
    }
}

    /**
     * Task1: (darksky.net)
     *
     * 1. Open darksky page
     * 2. Scroll to Today timeline
     * 3. Click the + button
     * 4. Verify tempValues (low and high) on timeline is same as tempValue in the Today's detail
     */

    /**
     * Task2: (darksky.net)
     *
     * 1. Open darksky page
     * 2. Click Time Machine button
     * 3. Verify current date is selected
     */

    /**
     * Task3: (darksky.net)
     *
     * 1. Open darksky page
     * 2. Click DarkSky API on header
     * 3. Click the 'blog post' link
     * 4. Verify that all article dates are in MonthName Date, Year format
     * 5. Verify article dates are in reverse chronological order (recent date first)
     */

    /**
     * Task4: (https://classroomessentialsonline.com/)
     *
     * 1. Open classroom essentials
     * 2. Verify 'School Furniture' has maximum number of options.
     * 3. Verify 'Chruch Chairs' menu has 7 options
     */




