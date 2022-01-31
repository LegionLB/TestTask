package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestForTestTask {

    private WebDriver driver;

    @BeforeTest
    public void profileSetup() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

    }

    @BeforeMethod
    public void testSetup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("http://testingchallenges.thetestingmap.org/index.php");


    }


    @Test(priority = 1)
    public void positiveFlowTestChallenge1(){

        WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstname']"));
        firstNameField.sendKeys("John");

        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "1");


    }


    @Test (priority = 2)
    public void negativeFlowEmptyField(){

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "1");

    }

    @Test (priority = 3)
    public void negativeFlowMaxCharacter(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("supercalifragilisticexpialidoc");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "1");

    }

    @Test (priority = 4)
    public void negativeFlowMinCharacter(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("s");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "1");

    }

    @Test (priority = 5)
    public void negativeFlowNumber(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("1");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "2"); //number and a single character

    }


    @Test(priority = 6)
    public void negativeFlowMoreThanMaxCharacter(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("supercalifragilisticexpialidocious");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "1");

    }

    @Test(priority = 7)
    public void negativeFlowSpace(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(" ");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "1");

    }

    @Test(priority = 8)
    public void negativeFlowSpaceThenWorld(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(" letters");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "1");

    }

    @Test(priority = 9)
    public void negativeFlowSpaceInTheMiddle(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("let word");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "2"); //space in the middle, average value

    }

    @Test(priority = 10)
    public void negativeFlowRussianLetter(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("к");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "2"); // not ASCII, other chr then alphabetic

    }

    @Test(priority = 11)
    public void negativeFlowOtherChar(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("%");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "2"); //minimum value, other character

    }


    @Test(priority = 12)
    public void negativeFlowHTMLTag(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("<h1>123</h1>");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "3"); //HTML code, other char, average value

    }

    @Test(priority = 13)
    public void negativeFlowXSSAttack(){

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("<script>alert(\"This site is bad to XSS\")</script>");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement checked = driver.findElement(By.xpath("//span[@class='values-tested']"));

        assertEquals(checked.getText(), "5"); // Other chars then alphabetic, Space in the middle, You used html tags ,Basic XSS, More than maximum values

    }

    @Test(priority = 14)
    public void positiveFlowRightURL(){

        assertTrue(driver.getCurrentUrl().contains("thetestingmap.org/index.php"));

    }

    @Test(priority = 15)
    public void negativeFlowSQLInjection(){



    }




    @AfterMethod
    public void tearDown() {

        driver.close();

    }


}
