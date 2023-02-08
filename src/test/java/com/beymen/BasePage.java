package com.beymen;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Random;


public class BasePage extends com.beymen.Constant {
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 30, 150);
    }

    public void start() {

        logger.info("Sayfa açılıyor");
        webDriver.get(FIRST_LINK);
    }

    public void assertMainPage(String link) {
        logger.info("Sayfa kontrol ediliyor");
        Assert.assertEquals(FIRST_LINK, webDriver.getCurrentUrl());
    }

    ////--------------------------------------ARAMA--------------------------------
    public void search(String item) throws InterruptedException {
        logger.info("Ürün aranıyor");
        webDriver.findElement(By.xpath(SEARCH_BAR)).clear();
        webDriver.findElement(By.xpath(SEARCH_BAR)).sendKeys(item);

    }


    ////-----------------------------BEKLEME----------------------------------
    public void staticWait() throws InterruptedException {
        logger.info("Sayfa yüklenmesi bekleniyor");
        Thread.sleep(3000);

    }

    public List<WebElement> listElements() {
        return webDriver.findElements(By.xpath(ELEMENT_LIST));
    }

    ////-------------------RASTGELE TIK----------------------
    public void randomClick() {
        logger.info("Rastgele ürün seçiliyor");
        Random random = new Random();
        listElements().get(random.nextInt(listElements().size())).click();
    }


    ////---------------SEPETE EKLEME---------------------
    public void addToCart() {
        logger.info("Sepete ekleniyor");
        webDriver.findElements(By.xpath(SELECT_SIZE));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(SELECT_SIZE))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(ADD_TO_CART))).click();
    }


    public void increaseCount() {
        logger.info("Ürün sayısı arttırılıyor");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(QUANTITY_BUTTON))).click();
        webDriver.findElement(By.xpath(QUANTITY_INCREASE)).click();

    }


    public void deleteProduct() throws InterruptedException {
        logger.info("Çıkan ikinci sayfada ürün temizleye basılıyor");
        webDriver.findElements(By.id(DELETE_ITEM_BUTTON));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(DELETE_ITEM_BUTTON))).click();
        logger.info("SEPETİN BOŞ OLDUĞU KONTROL EDİLİYOR");
        Thread.sleep(3000);
        WebElement element = webDriver.findElement(By.xpath(DELETE_ITEM));
        String elText = element.getText();
        Assert.assertEquals(ASSERT_CART_EMPTY, elText);
        logger.info("SEPET BOŞTUR");

    }

    public void assertBack() {
        logger.info("FİYATLAR KONTROL EDİLİYOR");
        WebElement element = webDriver.findElement(By.id(EXPECTED_PRICE));
        String elText = element.getText();
        System.out.println(elText);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MY_CART))).click();
        WebElement element2 = webDriver.findElement(By.xpath(ACTUAL_PRICE));
        String elText2 = element2.getText();
        Assert.assertEquals(elText2, elText);
        logger.info("FİYATLAR AYNI");
    }


    public void assertBracket() {
        logger.info("Ürün kontrol ediliyor");
        WebElement element = (WebElement) webDriver.findElement(By.xpath(QUANTITY_INCREASE));
        String validationText = element.getText();
        Assert.assertEquals(EXPECTED_QUANTITY, validationText);
        logger.info("ÜRÜNLER KONTROL EDİLDİ");
    }

    public void clear() throws InterruptedException {
        for (int i = 0; i < 4; i++) {

            webDriver.findElement(By.xpath(SEARCH_BAR)).sendKeys(Keys.BACK_SPACE);
            Thread.sleep(500);

        }

    }

    public void cookie() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(COOKIE))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(CLOSE_BUTTON))).click();

    }

    public void keysEnter() {
        webDriver.findElement(By.xpath(SEARCH_BAR)).sendKeys(Keys.ENTER);

    }


}






