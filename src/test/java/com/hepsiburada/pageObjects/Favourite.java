package com.hepsiburada.pageObjects;

import com.hepsiburada.testData.LogUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Favourite {

    WebDriver driver;
    WebDriverWait wait;

    By favouriteProductName = By.xpath("//div[@class = 'product-list']/div/div/div[3]/div[2]/div[1]");
    By favouriteProduct = By.xpath("//div[@class = 'product-list']/div/div");
    By selectProduct = By.xpath("//div[@class = 'product-list']/div/div/div[1]/div");
    By deleteButton = By.xpath("//button[@id='StickActionHeader-RemoveSelected']");
    By emptyWatchList = By.xpath("//span[contains(text(),', listen şu an boş.')]");


    public Favourite(WebDriver driver) {

        this.driver = driver;
    }


    public String actualProductName() {

        LogUtil.log("Beğendiklerim sayfasındaki ürün ismi :" + driver.findElement(favouriteProductName).getText());
        return driver.findElement(favouriteProductName).getText();


    }

    @Step("Ürün seçildi.")
    public void selectProduct() {

        WebElement element = driver.findElement(favouriteProduct);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();

        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(selectProduct)).click();

    }

    @Step("Ürün silindi.")
    public void deleteProduct() {

        driver.findElement(deleteButton).click();
        driver.findElement(By.xpath("//button[@id='DeleteConfirmationModal-ActionButton']")).click();
    }

    public String emptyListInfo() {

        LogUtil.log("Ürün silindikten sonra ekrandaki bilgi :" + driver.findElement(emptyWatchList).getText());
        return driver.findElement(emptyWatchList).getText();

    }


}