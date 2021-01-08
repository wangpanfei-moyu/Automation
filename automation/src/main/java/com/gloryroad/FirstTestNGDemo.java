package com.gloryroad;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTestNGDemo {

    public WebDriver driver ;
    String baseurl = "https://stage.ma.scrmtech.com/" ;


    @Test
    public void testSerach() {
        driver.get(baseurl+"user/index/login");  //加载登录地址
        //输入登录使用的账户、密码、验证码、短信验证码
        String emailString="stage@scrmtech.com";
        String passwordString="zhiqu8";
        String verifyString="6666";
        String message_verifyString="123456";
        //获取输入登录使用的账户、密码、验证码、短信验证码元素
        WebElement email= driver.findElement(By.id("login-email"));
        WebElement password= driver.findElement(By.id("login-password"));
        WebElement verify= driver.findElement(By.id("login-verify"));
        WebElement message_verify= driver.findElement(By.id("login-message-verify"));
        //清空输入框
        email.clear();
        password.clear();
        verify.clear();
        message_verify.clear();
        //
        email.sendKeys(emailString);
        password.sendKeys(passwordString);
        verify.sendKeys(verifyString);
        message_verify.sendKeys(message_verifyString);

        WebElement button=driver.findElement(By.id("login-btn"));
        button.click();
        button.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            // TODO: handle exception
        }


    }
    @BeforeMethod

    //打开浏览器
    public void beforeMethod() {
        //System.setProperty("webdriver.firefox.bin", "/Users/wangpanfei/eclipse/java-2020-063/Eclipse.app");
        System.setProperty("webdriver.gecko.driver", "/Applications/Firefox.app/geckodriver");
        driver = new FirefoxDriver();

    }






    @AfterMethod
    //关闭浏览器
    public void afterMethod(){
        driver.quit();
    }

}
