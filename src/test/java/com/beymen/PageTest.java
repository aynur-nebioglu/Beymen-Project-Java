package com.beymen;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


import static com.beymen.Constant.*;


public class PageTest extends com.beymen.BaseTest {

    public static String dataCsvFile;

    @Test
    public void login() throws InterruptedException, FileNotFoundException {

        Scanner sc = new Scanner(new File("src/main/resources/CsvDataFiles/csvFile.csv"));
        dataCsvFile = sc.nextLine();
        
        Scanner sc1 = new Scanner(new File("src/main/resources/CsvDataFiles/csvFile2.csv"));
        String dataCsvFile1 = sc1.nextLine();
    



        new com.beymen.BasePage(driver).start();
        new com.beymen.BasePage(driver).assertMainPage(FIRST_LINK);
        new com.beymen.BasePage(driver).cookie();
        new com.beymen.BasePage(driver).search(dataCsvFile);
        new com.beymen.BasePage(driver).staticWait();
        new com.beymen.BasePage(driver).clear();
        new com.beymen.BasePage(driver).search(dataCsvFile1);
        new com.beymen.BasePage(driver).keysEnter();
        new com.beymen.BasePage(driver).staticWait();
        new com.beymen.BasePage(driver).randomClick();
        new com.beymen.BasePage(driver).staticWait();
        new com.beymen.BasePage(driver).addToCart();
        new com.beymen.BasePage(driver).staticWait();
        new com.beymen.BasePage(driver).assertBack();
        new com.beymen.BasePage(driver).staticWait();
        new com.beymen.BasePage(driver).increaseCount();
        new com.beymen.BasePage(driver).staticWait();
        new com.beymen.BasePage(driver).assertBracket();
        new com.beymen.BasePage(driver).staticWait();
        new com.beymen.BasePage(driver).deleteProduct();





    }

}



