package com.hepsiburada.pageObjects;

import com.hepsiburada.testData.LogUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchList {

    WebDriver driver;
    WebDriverWait wait;

    By resultText = By.xpath("//span[contains(text(),'ürün bulduk.')]");
    By pagination = By.xpath("//div[@id='pagination']/ul/li[2]");
    By favouriteButton = By.xpath("//div[@id='productresults']/div[4]/div/div/div/div/ul/li[3]/div/div");
    By favouriteProductName = By.xpath("//div[@id='productresults']/div[4]/div/div/div/div/ul/li[3]/div/a/div[2]/h3");
    By favouriteProduct = By.xpath("//div[@id='productresults']/div[4]/div/div/div/div/ul/li[3]");

    public SearchList(WebDriver driver) {

        this.driver = driver;
    }

    public String getActualText() {
        LogUtil.log("Gelen arama sonucu : " + driver.findElement(resultText).getText());
        return driver.findElement(resultText).getText();

    }

    @Step("2. sayfaya geçiliyor.")
    public void clickPage() {

        WebElement element = driver.findElement(pagination);
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

    }

    @Step("Ürün beğenilere ekleniyor.")
    public void clickFavourite() throws InterruptedException {

        WebElement element = driver.findElement(favouriteProduct);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();

        wait.until(ExpectedConditions.elementToBeClickable(favouriteButton)).click();


    }


    public String expectedProductName() {

        LogUtil.log("Beğenilen ürün ismi : " + driver.findElement(favouriteProductName).getAttribute("title"));
        return driver.findElement(favouriteProductName).getAttribute("title");

    }


}
