package com.hepsiburada.pageObjects;

import com.hepsiburada.testData.LogUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    By signInButton = By.id("login");
    By search = By.xpath("//input[@placeholder = 'Ürün, kategori veya marka ara']");
    By signInListMenu = By.xpath("//span[contains(text(),'Giriş Yap')]");
    By accountListMenu = By.xpath("//span[contains(text(),'Hesabım')]");
    By favouriteListButton = By.cssSelector("a[title = 'Beğendiklerim']");


    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    @Step("Kullanıcı girişi sayfasına git.")
    public void signIn() throws InterruptedException {

        wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(signInListMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();


    }

    @Step("Ürün aranıyor. {productName} ")
    public void setSearch(String productName) throws InterruptedException {

        driver.findElement(search).sendKeys(productName, Keys.ENTER);

    }

    @Step("Beğendiklerim sayfasına git.")
    public void favouriteList() {

        wait.until(ExpectedConditions.elementToBeClickable(accountListMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(favouriteListButton)).click();

    }

    public String getActualTitle() {

        LogUtil.log("Gelen title bilgisi : " + driver.getTitle());
        return driver.getTitle();

    }


}