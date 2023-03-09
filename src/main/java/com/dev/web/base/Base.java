package com.dev.web.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.dev.web.reports.ExtentManager;

public class Base {

    public static WebDriver driver;
    public Properties prop;

    @BeforeSuite
    public void beforeExecution() {
        ExtentManager.getReporter();
    }

    public WebDriver initializeDriver() throws IOException {

        prop = loadConfig();
        String browserName = prop.getProperty("browser");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "/src/main/resources/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir") + "/src/main/resources/resources/geckodriver");
            driver = new FirefoxDriver();
        } else if (browserName.equals("ie")) {
            System.setProperty("webdriver.ie.driver",
                    System.getProperty("user.dir") + "/src/main/resources/IEDriverServer");
            driver = new InternetExplorerDriver();
        } else {
            System.out.println(browserName + " is not a valid browser");
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Set<Cookie> cookiesList = driver.manage().getCookies();
        for (Cookie getCookies : cookiesList) {
            driver.manage().addCookie(getCookies);
            System.out.println(getCookies);
        }
        return driver;
    }

    public Properties loadConfig() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/config.properties");
        prop.load(fis);
        return prop;
    }

    @AfterSuite()
    public void afterSuite() {
        ExtentManager.endReport();
        driver.quit();
    }
}