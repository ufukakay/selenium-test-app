package com.hepsiburada.test;


import com.hepsiburada.pageObjects.*;
import com.hepsiburada.testData.ExcelFile;
import com.hepsiburada.testData.LogUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class HBTest extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    SearchList searchList;
    Favourite favourite;

    ExcelFile excelFile;

    @Severity(SeverityLevel.CRITICAL)
    @Story("Anasayfa Giriş Kontrolü")
    @Description("Anasayfa'nın açılıp açılmadığını kontrol et.")
    @Test(description = "Anasayfa'nın açıldığını doğrula.", priority = 1)
    public void verifyHomePage() throws InterruptedException {

        homePage = new HomePage(driver);
        String expectedTitle = "Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
        LogUtil.log("Beklenen title bilgisi :" + expectedTitle);
        Assert.assertEquals(homePage.getActualTitle(), expectedTitle);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Kullanıcı Giriş Kontrolü")
    @Description("Doğru email ve doğru şifre ile kullanıcı girişini kontrol et. ")
    @Test(description = "Doğru email ve doğru şifre ile doğrula. ", priority = 2)
    public void verifyLogin() throws InterruptedException, IOException {

        homePage.signIn();
        loginPage = new LoginPage(driver);

        excelFile = new ExcelFile();
        excelFile.setExcelFile();

        String[] columns = excelFile.getDataCell();

        loginPage.setEmail(columns[0]);
        loginPage.setPassword(columns[1]);
        loginPage.login();


        Assert.assertEquals(loginPage.myAccount(), "Hesabım");
        excelFile.setDataCell("Başarılı");

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Ürün ismi aratıldığında sonuç döndürüldüğünü kontrol et.")
    @Story("Ürün Arama Kontrolü")
    @Test(description = "Ürün aramayı doğrula. ", priority = 3)
    public void verifySearchBar() throws InterruptedException {

        homePage.setSearch("samsung");
        searchList = new SearchList(driver);
        String expectedText = "ürün bulduk.";
        LogUtil.log("Beklenen arama sonucu : " + expectedText);
        Assert.assertEquals(searchList.getActualText(), "ürün bulduk.");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Ürünün listelendiği sayfalamayı kontrol et.")
    @Story("2.Sayfa Kontrolü")
    @Test(description = "2. sayfaya gittiğini doğrula.", priority = 4)
    public void verifyPageTwo() throws InterruptedException {

        searchList.clickPage();
        String expectedUrl = "https://www.hepsiburada.com/ara?q=samsung&sayfa=2";
        LogUtil.log("Beklenen sayfa bilgisi : " + expectedUrl);
        LogUtil.log("Gelen sayfa bilgisi : " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.hepsiburada.com/ara?q=samsung&sayfa=2");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Ürünün favorilere eklenip eklenmediğini kontrol et.")
    @Story("Favori Ürün Ekleme Kontrolü")
    @Test(description = "Ürünün favorilere eklendiğini doğrula.", priority = 5)
    public void verifyFavouriteProduct() throws InterruptedException {


        searchList.clickFavourite();
        String expectedProductName = searchList.expectedProductName();

        homePage.favouriteList();
        favourite = new Favourite(driver);

        Assert.assertEquals(favourite.actualProductName(), expectedProductName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Favorilerde olan ürünün silindiğini kontrol et.")
    @Story("Favori Ürün Silme Kontrolü")
    @Test(description = "Ürünün silindiğini doğrula.", priority = 6)
    public void verifyDeleteProduct() throws InterruptedException {

        favourite.selectProduct();
        favourite.deleteProduct();
        String expectedListInfo = ", listen şu an boş.";
        LogUtil.log("Beklenen ekran bilgisi : " + expectedListInfo);
        Assert.assertEquals(favourite.emptyListInfo(), expectedListInfo);
    }
}

