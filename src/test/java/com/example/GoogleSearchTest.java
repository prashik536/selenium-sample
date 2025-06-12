package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class GoogleSearchTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Add Chrome options
        ChromeOptions options = new ChromeOptions();

        // Avoid session error by setting a unique user data dir
        options.addArguments("--user-data-dir=/tmp/selenium-profile-" + System.currentTimeMillis());

        // Headless for Jenkins/Docker
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("OpenAI");
        searchBox.submit();
        assertTrue(driver.getTitle().contains("OpenAI"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
