package com.gloryroad;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Basic_method {
    WebDriver driver;
    String baseUrl ;

    @Test
    //10.1 访问某网站网址
    public void visitURL() {
        String baseurl ="http://sougou.com";
        //方法1
        //driver.get(baseurl);
        //方法2
        driver.navigate().to(baseurl);

    }

    //10.2 返回上一个访问的网页（模拟浏览器回退操作）
    public void visitRecentURL(){
        String baseUrl1="http://sougou.com";
        String baseUrl2="http://www.baidu.com";
        driver.navigate().to(baseUrl1);
        driver.get(baseUrl2);

        driver.navigate().back(); //返回上一个页面
    }

    //10.3 从上一个网页进入到下一个网页 （模拟浏览器前进功能）
    public void visitNextURL() {
        String baseUrl1 = "http://sougou.com";
        String baseUrl2 = "http://www.baidu.com";
        driver.navigate().to(baseUrl1);
        driver.get(baseUrl2);

        driver.navigate().back(); //返回上一个页面
        driver.navigate().forward(); //模拟浏览器前进功能

    }

    //10.4刷新当前页面
    public void freshCurrentPage(){
        String baseurl ="http://sougou.com";
        driver.navigate().to(baseUrl);
        driver.navigate().refresh();//刷新当前页面

    }

    //10.5 操作浏览器窗口
    public void operateBrowser(){
        //声明一个point对象，两个150浏览器的未知相当与屏幕的左上角（0，0）的横坐标和纵坐标距离
        Point point =new Point(150,150);
        //声明Dimension对象，两个500表示浏览器的
        Dimension dimension=new Dimension(500,500);

        driver.manage().window().setPosition(point);
        driver.manage().window().setSize(dimension);

        //获取浏览器在屏幕的位置
        System.out.println(driver.manage().window().getPosition());
        //获取当前浏览器的大小
        System.out.println(driver.manage().window().getSize());
        //最大化浏览器
        driver.manage().window().maximize();

        driver.get("http://www.baidu.com");
    }

    //10.6获取页面的Title属性
    public void getTitle(){
        driver.get("http://www.baidu.com");
        //调用driver的getTitle方法获取Title属性
        String title =driver.getTitle();
        //打印当前页面的Title内容
        System.out.println(title);
        //断言判断页面是否正确 判断是否相同 =


    }

    //10.7获取页面源代码
    public void getPagesource(){
        driver.navigate().to("http://www.baidu.com");
        //调用getPageSource获取页面源代码
        String pageSource =driver.getPageSource();
        System.out.println(pageSource);
        //断言源代码是否包含某个关键字
        Assert.assertTrue(pageSource.contains("百度一下"));
    }

    //10.8 获取当前页面的url地址
    public void gerCurrentPageUrl(){
        driver.navigate().to("http://www.baidu.com");
        //获取当清页面的url地址
        String CurrentPageUrl= driver.getCurrentUrl();

        System.out.println(CurrentPageUrl);
        //断言Url是否相同
        Assert.assertEquals("http://www.baidu.com","CurrentPageUrl");
    }

    //10.9在输入框请空原有的文字内容
    public void ClearInputBoxText(){
        //定位到某个元素
        WebElement TextInput = driver.findElement(By.id("text"));
        //清除输入框默认元素
        TextInput.clear();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //10.10 在输入框输入指定内容
    public void SendTextToInputBoxText(){
        String inputString = "测试指定输入";
        WebElement TextInput=driver.findElement(By.id("Text"));
        TextInput.clear();
        //将自定义变量输入指定的文本框
        TextInput.sendKeys(inputString);
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //10.11 单击按钮
    public void ClickButton(){
        WebElement button = driver.findElement(By.id("button"));
        //单击选中的按钮元素
        button.click();
    }

    //10.12双击某个元素
    public void Doubleclick(){
        WebElement inputBox =driver.findElement(By.id("inputBox"));
        //声明Action方法
        Actions builder =new Actions(driver);
        //使用doubleClick方法在输入框中进行鼠标的双击操作
        builder.doubleClick(inputBox).build().perform();
    }

    //10.13操作单选下拉列表
    public void operateDropList(){
        //使用name属性找到页面上name为fruit的下拉列表
        Select dropList = new Select(driver.findElement(By.name("fruit")));
        //isMultiple方法判断下拉列表是否为多选，多选返回Ture，单选返回False，测试是为单选
        Assert.assertFalse(dropList.isMultiple());
        //getFirstSelectedOption().getText()方法 获取下拉列表的选项文本，并判断默认是否为桃子
        Assert.assertEquals("桃子",dropList.getFirstSelectedOption().getText());
         //selectByIndex（3） 方法表示选中下拉列表的第四个选项，从0开始
        dropList.selectByIndex(3);
        Assert.assertEquals("猕猴",dropList.getFirstSelectedOption().getText());
        //selectByValue("shanzhai") 方法表示选中下拉列表选项值value=shanzhai 的值
        dropList.selectByValue("shanzhai");
        Assert.assertEquals("山楂",dropList.getFirstSelectedOption().getText());
        //selectByVisibleText("荔枝") 方法表示通过选项中的文字进行选中
        dropList.selectByVisibleText("荔枝");
        Assert.assertEquals("荔枝",dropList.getFirstSelectedOption().getText());
    }

    //10.14检查单选列表中的选项文字是否符合预期
    public void checkselectTest(){
        //使用name属性找到页面上name为fruit的下拉列表
        Select dropList = new  Select(driver.findElement(By.name("fruit")));
        /* 声明一个Lsit对象存储下拉列表中所期望的选项文字，并且通过泛型<String>限定List
        * 对象中的存储类型是String，"Arrays.asList"表示将一个数组转换成一个List对象
        */
        List<String> expect_options = Arrays.asList(new String[]{"桃子","西瓜","橘子","山楂","荔枝"});
        /* 声明一个新的List对象，用于存储从页面上获取的所有选项问题*/
        List<String > actual_option =new ArrayList<String>();
        //dropList.getOptions()获取下列列表中的所有选项
            for (WebElement  option : dropList.getOptions())
                //actual_option.add 方法将取得的所有选项添加到  actual_option中
                actual_option.add(option.getText());
         //断言期望的选项与实际对象的数组值完全一致
        Assert.assertEquals(expect_options.toArray(),actual_option.toArray());
    }

    //10.15 操作多选的选择列表
    public void operateMultipleOptionDropList(){
        //使用name属性找到页面上name为fruit的下拉列表
        Select dropList =new  Select(driver.findElement(By.name("fruit")));
        //isMultiple方法判断下拉列表是否为多选，多选返回Ture，单选返回False，测试是为多选
        Assert.assertTrue(dropList.isMultiple());
        //使用索引选中第四个选项，"猕猴桃"
        dropList.selectByIndex(3);
        //使用value属性值选中"山楂"
        dropList.selectByValue("shanzha");
        //使用选项文字选择"荔枝"
        dropList.selectByVisibleText("荔枝");
        //使用deselectAll方法取消所有选项的选中状态
        dropList.deselectAll();

        dropList.selectByIndex(3);
        dropList.selectByValue("shanzha");
        dropList.selectByVisibleText("荔枝");

        //通过索引取消第四个选项
        dropList.deselectByIndex(4);
        //通过value取消山楂选中状态
        dropList.deselectByValue("shanzha");
        //通过选项文字取消荔枝选中状态
        dropList.deselectByVisibleText("荔枝");
    }

    //10.16操作单选项
    public void operateRadio(){
        //查找属性为berry的按钮对象
        WebElement radioOption =driver.findElement(By.xpath("//input[@value='berry']"));
        //判断按钮是否被选中，如果没有被选中，则点击按钮
        if (!radioOption.isSelected())
            radioOption.click();
        //断言选项"berry"是否被选中
        Assert.assertTrue(radioOption.isSelected());
        /*查找name属性为fruit的所有单选按钮，并将其存储到一个List容器中*/
        List<WebElement> fruits =driver.findElements(By.name("fruit"));
        /* 使用for循环对List容器中所有的单选按钮进行遍历，查找namen属性为watermelon的按钮，如果此按钮未必*/
            for (WebElement fruit : fruits){
                if(fruit.getAttribute("value").equals("watermelon")){
                    if(!fruit.isSelected())
                        fruit.click();
                    Assert.assertTrue(fruit.isSelected());
                }
                break;
            }

    }

    //10.17操作复选框
    public void operateCheckBox(){
        ///查找属性为orange的按钮对象
        WebElement orangeCheckBox=driver.findElement(By.xpath("//input[@value='orange']"));
        //判断orangeCheckBox是否被选中，如果未未被选中，则点击选中
        if(!orangeCheckBox.isSelected())
            orangeCheckBox.click();
        Assert.assertTrue(orangeCheckBox.isSelected());
        //通过第二次点击取消选中状态
        if(orangeCheckBox.isSelected())
            orangeCheckBox.click();
        //断言选项为未选中状态
        Assert.assertFalse(orangeCheckBox.isSelected());

        //将搜有选项存储到List中 ，然后遍历元素，并点击
        List<WebElement> checkboxs=driver.findElements(By.name("fruit"));
        for(WebElement checkbox:checkboxs) checkbox.click();

    }

    //10.18 杀掉windows的浏览器进程
    public void operateWindowsProcess(){
    }

    //10.19对当前浏览器窗口进行截屏
    public void captureScreenInCurrentWindoes(){
        driver.navigate().to("http://sougou.com");
        File scrFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile,new File("c:\\testing\\test.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //10.20 检查页面元素的文本内容是否出现
    public void isElementTestPresent(){
        WebElement text =driver.findElement(By.xpath("//p[1]"));
        String contentText = text.getText();
        //全匹配检查
        Assert.assertEquals("《光荣之路》这个电影很棒",contentText);
        //模糊检查
        Assert.assertTrue(contentText.contains("光荣之路"));
        //开头匹配检查
        Assert.assertTrue(contentText.startsWith("《光荣"));
        //结尾匹配检查
        Assert.assertTrue(contentText.endsWith("很棒"));
    }

    //10.21执行JavaScript脚本
    public void executeJavaScript (){
        driver.get("http://sougou.com");
        //声明一个JavaScript执行器对象
        JavascriptExecutor js=(JavascriptExecutor) driver;
        //调用执行器对象的JavaScript方法执行JavaScript脚本
        //return document.title是JavaScript脚本，返回当前浏览器窗口的Title
        String title=(String) js.executeScript("return document.title");
        Assert.assertEquals("搜狗引擎-上网从搜狗开始",title);
        //document.getElementByID('stb')是JavaScript代码，表示获取当前页面的搜索按钮对象，return button.value"表示返回按钮上的文字
        String serachButtonText=(String) js.executeScript("var button=document.getElementByID('stb');return button.value");
        System.out.println(serachButtonText);
    }

    //10.22拖拽页面元素
    public void dragPageElement(){
        driver.get("http://sougou.com");
        WebElement dragable =driver.findElement(By.id("dragable"));
        for (int i=0;i<5;i++){

            new Actions(driver).dragAndDropBy(dragable,0,10).build().perform();
        }
        for (int i=0;i<5;i++){
            new Actions(driver).dragAndDropBy(dragable,10,0).build().perform();
        }
    }

    //10.23模拟键盘操作
    public void clickKeys(){
        driver.navigate().to("http://sougou.com");
        Actions action =new Actions(driver);
        action.keyDown(Keys.CONTROL); //按下ctrl键
        action.keyDown(Keys.SHIFT);//按下shift键
        action.keyDown(Keys.ALT); //按下alt键
        action.keyUp(Keys.CONTROL);//释放ctrl键
        action.keyUp(Keys.ALT);//释放alt键
        action.keyUp(Keys.SHIFT);//释放shift键
        //模拟在键盘中输入大写的ABCD
        action.keyDown(Keys.SHIFT).sendKeys("abcd").perform();
    }

    //10.24 模拟鼠标右键操作
    public void RightClickMouse(){
        driver.get("http://www.baidu.com");
        //声明Action对象
        Actions action =new Actions(driver);
        //调用action对象的contextClick在指定元素的输入框上右键
        action.contextClick(driver.findElement(By.id("query"))).perform();
    }

    //10.25在指定元素上方尽心鼠标悬浮
    public void roverOnElement(){
        //查找页面上的链接对象
        WebElement link1=driver.findElement(By.xpath("//a[@id='link1']"));
        WebElement link2=driver.findElement(By.xpath("//a[@id='link2']"));
        //声明一个action对象
        Actions action=new Actions(driver);
        //调用Action对象中moveToElement方法，将鼠标移动到某个链接的上方
        action.moveToElement(link1).perform();
        //暂停3s，看到显示的蓝色方框
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //将鼠标移动到另一个链接上方，方框消失
        action.moveToElement(link2).perform();

    }

    //10.26 在指定元素上进行鼠标单击左键和释放的操作
    public void mouseClickAndRelease(){

        WebElement div=driver.findElement(By.xpath("//[@id='div1']"));

        Actions action =new Actions(driver);
        //Actions对象中的clickAndHold方法，表示按住某个元素不释放
        action.clickAndHold(div).perform();
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        ////Actions对象中release方法，表示释放鼠标左键
        action.release(div).perform();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    //10.27查看页面元素的属性
    public void getWebElementAttribute(){
        driver.get("http://sougou.com");
        String inputString="测试输入";
        WebElement input=driver.findElement(By.id("Query"));
        input.sendKeys(inputString);
        //调用getAttribute方法，获取搜索框的value的值
        String inputText=input.getAttribute("value");
        Assert.assertEquals(inputText,inputString);
    }

    //10.28 获取页面元素的CSS属性值
    public void getWebElementCssValue(){
        driver.get("http://sougou.com");
        WebElement input=driver.findElement(By.id("Query"));
        //调用getCssValue方法获取指定宽度
        String InnputWidth=input.getCssValue("width");
        Assert.assertEquals("499px",InnputWidth);

        }

    //10.29 隐式等待
    public void ImplicitWait(){
        /*  使用implicitlyWait方法设定查找页面元素的最大等待时间，调用findElement方法查找元素，没有立即找到元素，则每隔一段时间就
            *查找元素，知道找到元素或者超过超时时间抛出异常
        */
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            WebElement searchInput=driver.findElement(By.id("query"));
            WebElement searchButton=driver.findElement(By.id("stb"));
            searchInput.sendKeys("测试");
            searchButton.click();
        }catch (NoSuchElementException e){
            Assert.fail("没有找到搜索的输入框");
            e.printStackTrace();

        }

    }

    //10.30常用显式等待
    public void ExplicitWait(){
        //声明一个WebDriverWait对象，设定触发条件的最长等待时间为10s
        WebDriverWait wait = new WebDriverWait(driver,10);
        //调用ExpectedConditions的titleContains方法判断Title属性是否包含"水果"
        wait.until(ExpectedConditions.titleContains("水果"));
        System.out.println("标题存在水果");
        //获取列表中桃子的选项对象
        WebElement select = driver.findElement(By.xpath("//option@[id='peach']"));
        //调用ExpectedConditions中的elementToBeSelected方法判断"桃子是否属于选中状态"
        wait.until(ExpectedConditions.elementToBeSelected(select));

        System.out.println("下拉列表中的桃子处于被选中状态");
        //调用ExpectedConditions中elementToBeClickable方法判断元素是否可可见，并且被点击
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox']")));
        System.out.println("页面复选项处于显示和可被单击状态");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p")));
        System.out.println("页面P元素以显示");
        WebElement p=driver.findElement(By.xpath("//p"));
        wait.until(ExpectedConditions.textToBePresentInElement(p,"水果"));
        System.out.println("页面标签p包含水果");

    }

    //10.31自定义显式等待

    //10.32判断页面元素是否存在

    //10.33使用Title属性识别和操作弹出的浏览器窗口

    //10.34

    //10.35

    //10.36
    //10.31
    //10.31
    //10.31
    //10.31

}

