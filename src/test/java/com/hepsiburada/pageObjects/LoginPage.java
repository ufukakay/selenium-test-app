package com.hepsiburada.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.concurrent.TimeUnit;


public class LoginPage {

    WebDriver driver;

    By email = By.id("txtUserName");
    By password = By.id("txtPassword");
    By loginButton = By.id("btnLogin");
    By myAccount = By.xpath("//span[contains(text(),'Hesabım')]");

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    @Step("Kullanıcı email : {email} ")
    public void setEmail(String email) throws InterruptedException {

        driver.findElement(this.email).clear();
        driver.findElement(this.email).sendKeys(email);

    }

    @Step("Kullanıcı şifresi : {password} ")
    public void setPassword(String password) {

        driver.findElement(this.password).clear();
        driver.findElement(this.password).sendKeys(password);

    }

    @Step("Kullanıcı girişi yapılıyor.")
    public void login() {

        driver.findElement(loginButton).click();
    }

    public String myAccount() {

        return driver.findElement(myAccount).getText();

    }


}
