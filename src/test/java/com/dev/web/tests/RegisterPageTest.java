package com.dev.web.tests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.codoid.products.exception.FilloException;
import com.dev.web.base.Base;
import com.dev.web.pages.HomePageElements;
import com.dev.web.pages.LoginPageElements;
import com.dev.web.pages.RegisterPageElements;
import com.dev.web.utils.CommonActions;
import com.dev.web.utils.ListenerClass;
import com.dev.web.utils.Utils;

@Listeners(ListenerClass.class)
public class RegisterPageTest extends Base {

    public WebDriver driver;
    public CommonActions commonActions;
    LoginPageElements loginPageElements;
    RegisterPageElements registerPageElements;
    HomePageElements homePageElements;
    private Logger log = LogManager.getLogger(RegisterPageTest.class.getName());
    private HashMap<String, String> data;

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized.");
        commonActions = new CommonActions(driver);
        loginPageElements = new LoginPageElements(driver);
        homePageElements = new HomePageElements(driver);
        registerPageElements = new RegisterPageElements(driver);
        commonActions.navigateTo(prop.getProperty("url"));
    }

    @Test(priority = 0, description = "This method is to check the valid scenarios for register page")
    public void performRegistrationWithValidCredentials() throws IOException, FilloException {
        // get data from excel sheet for TC5
        data = new Utils().getTestData("register", "TC6");
        // enter valid email, password and confirm password and click on register
        loginPageElements.clickOnRegister();
        registerPageElements.completeRegisterForm(commonActions.randomEmailGenerator(), data.get("Password"),
                data.get("ConfirmPassword"));
        // verify homepage url and check home page is displayed.
        homePageElements.checkHomePageAfterLogin(data.get("url"));
        log.info("Successfully Registered");
    }

    @Test(priority = 1, description = "This method is to check all the invalid scenarios for register page")
    public void performRegistrationWithInvalidCredentials() throws IOException, FilloException {
        // get data from excel sheet for TC1
        data = new Utils().getTestData("register", "TC1");
        // enter invalid email and password and click on register
        loginPageElements.clickOnRegister();
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified inavlid register error message for TC1");
        // get data from excel sheet for TC2
        data = new Utils().getTestData("register", "TC2");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified inavlid register error message for TC2");
        // get data from excel sheet for TC3
        data = new Utils().getTestData("register", "TC3");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified inavlid register error message for TC3");
        // get data from excel sheet for TC4
        data = new Utils().getTestData("register", "TC4");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC4");
        // get data from excel sheet for TC4
        data = new Utils().getTestData("register", "TC5");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC5");
    }

    @Test(priority = 2, description = "Check back button on register page")
    public void verifyBackButtonOnRegister() {
        loginPageElements.clickOnRegister();
        registerPageElements.checkAndClickOnBackButton();
        Assert.assertTrue(commonActions.checkElementIsDisplayed(loginPageElements.forgotPassword_Txt));

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}