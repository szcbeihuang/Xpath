package org.example;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumChromeDriver {
    private static WebDriver chromeDriver=null;
    private static WebDriverWait wait10Secs=null;
    private int delayMultiply;

    public SeleniumChromeDriver()
    {
        chromeDriver = new ChromeDriver();
        wait10Secs=null;
        delayMultiply = 3;
    }

    //开始 测试
    public void connect()
    {
        chromeDriver.manage().window().maximize();
    }
    public static void click_xpath(String path) throws InterruptedException {
        chromeDriver.findElement(By.xpath(path)).click();
        Thread.sleep(1000);
    }
    public static void click_id(String id) throws InterruptedException {
        chromeDriver.findElement(By.id(id)).click();
        Thread.sleep(1000);
    }
    //关闭测试
//    public void disconnect()
//    {
//        // closes the test window
//        chromeDriver.close();
//        chromeDriver.quit();
//    }

    //下拉滚动条,首先要允许浏览器运行js脚本
    public  void pag() throws InterruptedException {
        chromeDriver.findElement(By.xpath("/html/body/div[2]/div[4]/div[1]/div[3]/div[1]/div/h3/a[1]")).click();
        Thread.sleep(2000);
    }
    public void pagePullDown()
    {
        JavascriptExecutor jsExecutor=(JavascriptExecutor) chromeDriver;
        jsExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        delay(2*delayMultiply);//Java的暂停。
    }

    /**
     * @param
     * @return 当前打开窗口的最后一个句柄
     */
    private String getLastHandle()
    {
        //获取当前打开窗口的所有句柄
        Set<String> allhandles = chromeDriver.getWindowHandles();
        ArrayList<String> lst = new ArrayList<String>(allhandles);
        return lst.get(lst.size()-1);
    }

    /**进入打开窗口的最后一个。
     *
     */
    public void goLastPage()
    {
        String lastHandle = getLastHandle();
        chromeDriver.switchTo().window(lastHandle);
    }

    /** 切换到窗口（句柄） 离开handle指向的窗口，到另一个窗口。
     * 浏览器有两个窗口，我们在新窗口上操作。
     * @param curHandle  窗口句柄，注意与窗口标题区分开来。
     */
    public void switchToWindowHandle(String curHandle)
    {
        for(String winHandle : chromeDriver.getWindowHandles())
        {
            if (winHandle.equals(curHandle)) {
                continue;
            }
            chromeDriver.switchTo().window(winHandle);
            break;
        }
        delay(1*delayMultiply);//Java的暂停。
    }

    /** 切换到窗口（title） 离开当前窗口。
     * 浏览器有两个或两个以上的窗口，我们在新窗口上操作。
     * @param strTitle  窗口title，注意与窗口句柄区分开来。
     * @return 记录包含strTitle的窗口句柄。因为新打开的窗口与现在窗口同名。
     */
    public String switchToWindowTitle(String strTitle)
    {
        String curHandle = null;//记录包含strTitle的窗口句柄。
        for(String winHandle : chromeDriver.getWindowHandles())
        {
            chromeDriver.switchTo().window(winHandle);
            if (chromeDriver.getTitle().contains(strTitle))
            {
                curHandle = winHandle;
                break;
            } //end of if...
        } //end of for...
        //driver.get("http://jwc.ccsu.cn/");
        delay(1*delayMultiply);//Java的暂停。
        return curHandle;
    }

    /** 切换到窗口（其标题为title） 。句柄为strHandle的可能也是这个标题，排除它。
     * @param strTitle 网页标题
     * @param strHandle  网页句柄
     */
    public void switchToWindowTitleExclusiveHandle(String strTitle,String strHandle)
    {
        for(String winHandle : chromeDriver.getWindowHandles())
        {
            chromeDriver.switchTo().window(winHandle);
            if (chromeDriver.getTitle().contains(strTitle))
            {
                if (winHandle.equals(strHandle))
                {
                    continue;
                }
                else
                {
                    break;
                }//end of else...
            }
        } //end of for...
        //driver.get("http://jwc.ccsu.cn/");
        delay(1*delayMultiply);//Java的暂停。
    }


    /**跳转到某网页，或者打开某个主页。
     * @param strURL URL形式的网址，主页，网页
     * @param ntimes 延时多少秒
     */
    public void goUrl(String strURL,int ntimes)
    {
        chromeDriver.get(strURL);
        delay(2*delayMultiply);//Java的暂停。
    }


    /** 依据网页的字符串信息，来点击。
     * @param strLinkText  字符串，网页上可点击的元素。
     * @param ntimes 延时多少秒
     */
    public void clickLinkText(String strLinkText,int ntimes)
    {
        By orgnLocation = By.linkText(strLinkText);
        // WebDriver的时间，10秒钟内，等待“XXXX ”链接出现。
        wait10Secs.until(ExpectedConditions.visibilityOfElementLocated(orgnLocation));
        //strLinkText链接出现后，点击它。
        WebElement elemOrgn = chromeDriver.findElement(orgnLocation);
        elemOrgn.click();
        delay(1*delayMultiply);//Java的暂停。
    }

    /** 依据XPath路径信息，来点击。
     * @param strXPath  字符串，XML的XPath查询。
     * @param ntimes 延时多少秒
     */
    public void clickXPath(String strXPath,int ntimes)
    {
        By orgnLocation = By.xpath(strXPath);
        // WebDriver的时间，10秒钟内，等待strXPath链接出现。
        wait10Secs.until(ExpectedConditions.visibilityOfElementLocated(orgnLocation));
        //strXPath链接出现后，点击它。
        WebElement elemOrgn = chromeDriver.findElement(orgnLocation);
        elemOrgn.click();
        delay(1*delayMultiply);//Java的暂停。
    }

    /** 依据XPath来切换Frame或者iFrame
     * @param strXPath XPath路径名
     */
    public void switchToFrameByXPath(String strXPath)
    {
        By frameLocation = By.xpath(strXPath);
        WebElement element = chromeDriver.findElement(frameLocation);
        chromeDriver.switchTo().frame(element);
    }

    //切换到页面的第index个Frame,index from 0 start
    public void switchToFrameByIndex(int index)
    {
        chromeDriver.switchTo().frame(index);
    }

    /** 依据CSS来切换Frame或者iFrame
     * @param strSelector CSS路径名
     */
    public void switchToFrameByCSS(String strSelector)
    {
        By frameLocation = By.cssSelector(strSelector);
        WebElement element = chromeDriver.findElement(frameLocation);
        chromeDriver.switchTo().frame(element);
    }

    //切到frame中之后，我们便不能继续操作主文档的元素，这时如果想操作主文档内容，则需切回主文档。
    public void switchToDefaultFrame()
    {
        chromeDriver.switchTo().defaultContent();
    }

    /** 下拉选择框， 先由strXPath定位，再 选择其第index项
     * @param strXPath XPath路径
     * @param index 下拉选择框的取值
     */
    public void optionSelect(String strXPath,int index)
    {
        By location = By.xpath(strXPath);
        WebElement element = chromeDriver.findElement(location);
        Select select = new Select(element);
        select.selectByIndex(index);
        delay(2*delayMultiply);//Java的暂停。
    }

    /** 点击对应的按钮
     * @param strXPath XPath路径
     */
    public void buttonClick(String strXPath)
    {
        By location = By.xpath(strXPath);
        chromeDriver.findElement(location).click();
        delay(2*delayMultiply);//Java的暂停。
    }

    /** 输入内容
     * @param strXPath  XPath路径
     * @param strContent  要键入的内容
     */
    public void inputText(String strXPath,String strContent)
    {
        By location = By.xpath(strXPath);
        chromeDriver.findElement(location).sendKeys(strContent);
        delay(2*delayMultiply);//Java的暂停。
    }


    /** 通过JS注入HTML代码，在富文本输入。 查阅CSS选择器的资料
     * @param cssSelector  css选择器
     * @param htmlText   HTML格式的文本。
     */
    public void jsInputHTMLByCSS(String cssSelector,String htmlText)
    {
        JavascriptExecutor jsExecutor=(JavascriptExecutor) chromeDriver;
        //js代码；DOM
        String strJS="document.querySelector('"+cssSelector+
                "').innerHTML='"+htmlText+"'";
        jsExecutor.executeScript(strJS);
        delay(2*delayMultiply);//Java的暂停。
    }


    /**通过JS注入HTML代码，在富文本输入。
     * @param strXPath  XPath路径
     * @param htmlText   HTML格式的文本。
     */
    public void jsInputHTMLByXPath(String strXPath,String htmlText)
    {
        By location = By.xpath(strXPath);
        WebElement element = chromeDriver.findElement(location);
        JavascriptExecutor jsExecutor=(JavascriptExecutor) chromeDriver;
        String strJS="arguments[0].innerHTML = '"+htmlText+"'";
        jsExecutor.executeScript(strJS,element);
        delay(2*delayMultiply);//Java的暂停。
    }

    //延时，给学生上课。
    private	static void delay(int seconds)
    {
        try {
            Thread.sleep(seconds*1000);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//end of 延时
}
