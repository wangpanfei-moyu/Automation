package com.gloryroad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Visit_stage {
    WebDriver driver;
    String baseUrl ;


    @BeforeMethod
    public void setUp() throws Exception{
        baseUrl = "https://stage.ma.scrmtech.com/";
        System.setProperty("webdriver.gecko.driver", "/Applications/Firefox.app/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    //登录系统
    public void Login() {
        driver.get(baseUrl+"user/index/login");  //加载登录地址
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
    //刷行登录后的界面
    public void freshCurrentPage(){
        driver.navigate().refresh(); //刷行当前页面

    }

    @AfterMethod
    //关闭浏览器
    public void tearDown() throws Exception{
        driver.quit();
    }
}
