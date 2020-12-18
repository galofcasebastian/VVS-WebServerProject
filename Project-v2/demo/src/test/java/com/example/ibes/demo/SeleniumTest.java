package com.example.ibes.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

    @Test
    public void testS() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sebi\\Desktop\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        WebElement element;

        WebDriverWait wait = new WebDriverWait(driver, 30, 1000);

        driver.get("http://localhost:8080/");
        Thread.sleep(10000); 

        //All button test
        By cars_all = By.id("All");
        wait.until(elementToBeClickable(cars_all));
        driver.findElement(cars_all).click();
        
        Thread.sleep(5000);
        
        //Delete button test
        By cars_delete = By.id("Delete");
        wait.until(elementToBeClickable(cars_delete));
        driver.findElement(cars_delete).click();
        
        Thread.sleep(5000);

        //Less than 8000€ button test
        By cars_less = By.id("getAffordable");
        wait.until(elementToBeClickable(cars_less));
        driver.findElement(cars_less).click();

        Thread.sleep(5000);

        //Title test
        String title = driver.getTitle();
        assertTrue(title.contains("Cars for sale"));

        Thread.sleep(5000);

        //First name text box test
        element = driver.findElement(By.name("fname"));
        element.sendKeys("Sebi");

        Thread.sleep(5000);

        //Last name text box test
        element = driver.findElement(By.name("lname"));
        element.sendKeys("Galofca");

        Thread.sleep(5000);
        
        driver.quit();
    }

}
