package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        System.out.println("start selenium");
//        SeleniumChromeDriver driver = new SeleniumChromeDriver();
//        driver.connect();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
//        driver.goUrl("https://www.baidu.com/?tn=15007414_8_dg", 2);
//        driver.inputText("/html/body/div[1]/div[1]/div[5]/div/div/form/span[1]/input", "bilibili");
//        driver.buttonClick("/html/body/div[1]/div[1]/div[5]/div/div/form/span[2]/input");
////        driver.clickXPath("/html/body/div[2]/div[4]/div[1]/div[3]/div[1]/div/h3/a[1]",1);
//        Thread.sleep(3000);
//        driver.goUrl("https://www.bilibili.com/",1);
//        driver.pag();

//        driver.inputText("/html/body/div[1]/div[1]/div[5]/div/div/form/span[1]/input", "bilibili");
//        driver.buttonClick("/html/body/div[1]/div[1]/div[5]/div/div/form/span[2]/input");
        driver.get("https://www.baidu.com/?tn=15007414_8_dg");
//
        driver.findElement(By.id("kw")).sendKeys("bilibili");
        driver.findElement(By.id("su")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[1]/div[3]/div[1]/div/h3/a[1]")).click();
        Thread.sleep(10000);
//        driver.close();
        driver.switchTo().window(getLastHandle());
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/div/div/form/div/input")).click();
        Thread.sleep(10000);
//        driver.get("https://search.bilibili.com/all?vt=47729360&keyword=%E7%83%9F%E9%9B%A8%E6%B1%9F%E6%B9%96&from_source=webtop_search&spm_id_from=333.1007&search_source=5");
//        driver.close();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/div/div/form/div[1]/input")).sendKeys("烟雨江湖");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/div/div/form/div[2]")).click();
        Thread.sleep(3000);
        driver.switchTo().window(getLastHandle());
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div/div/div/div[1]/div/div/div/div/div/a")).click();
        Thread.sleep(3000);
        driver.switchTo().window(getLastHandle());
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[5]/div/div/small/div[1]/div[1]/div/div/ul/li[3]/a")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[4]/div[5]/div/div/small/div[1]/div[1]/div/div/div[3]/p/a[3]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[4]/div/div[3]/div/div[1]/div/div[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[4]/div[5]/div/div/div/div[4]/div/div/p[1]/small/a[2]")).click();
//        driver.close();
    }
    private static String getLastHandle()
    {
        //获取当前打开窗口的所有句柄
        Set<String> allhandles = driver.getWindowHandles();
        ArrayList<String> lst = new ArrayList<String>(allhandles);
        return lst.get(lst.size()-1);
    }
    public void goLastPage()
    {
        String lastHandle = getLastHandle();
        driver.switchTo().window(lastHandle);
    }
    public static void click_xpath(String path) throws InterruptedException {
        driver.findElement(By.xpath(path)).click();
        Thread.sleep(1000);
    }
    public static void click_id(String id) throws InterruptedException {
        driver.findElement(By.id(id)).click();
        Thread.sleep(1000);
    }
}

