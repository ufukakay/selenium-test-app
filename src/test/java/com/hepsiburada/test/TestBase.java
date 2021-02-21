package com.hepsiburada.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;
    ChromeOptions chromeOptions;

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Impos\\Desktop\\selenium-test-app\\Driver\\chromedriver.exe");

        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.hepsiburada.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }

}
