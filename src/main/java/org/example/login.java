package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class login {
    public static WebDriver driver;
    public static void main(String []args) throws InterruptedException {
        System.out.println("start selenium");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://iwarship.net/wowsdb/index");
        Thread.sleep(3000);
        click_xpath("/html/body/div/div[2]/div/div/span[2]/div/button/div/div/div");
//		click_id("ship-select");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div[2]/div/div/span[2]/div/div/div[1]/input")).sendKeys("布林迪西");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div/div[2]/div/div/span[2]/div/div/div[1]/input")).sendKeys(Keys.ENTER);
        click_xpath("//*[@id=\"table-hull-toolbar\"]/div[1]/button[1]");
        click_xpath("//*[@id=\"btn-table-hull-model-tonnage\"]");
        Thread.sleep(10000);
//        driver.close();
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