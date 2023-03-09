package com.dev.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.dev.web.utils.CommonActions;

public class RegisterPageElements {

    public WebDriver driver;
    public CommonActions commonActions;

    public RegisterPageElements(WebDriver driver) {
        this.driver = driver;
        commonActions = new CommonActions(driver);
    }

    // All objects should be defined here
    private By email_Txt = By.cssSelector("#email");
    private By password_Txt = By.cssSelector("#password");
    private By passwordConfirm_Txt = By.cssSelector("#password-confirm");
    private By register_Btn = By.xpath("//input[@type='submit']");
    private By register_Lbl = By.cssSelector("#kc-page-title");
    private By errorMsg = By.xpath("(//div/span)[3]");
    private By backToLogin_Btn = By.linkText("« Back to Login");

    // To fill the login form using email and password and click on sign in button
    public void completeRegisterForm(String email, String password, String confirmPassword) {
        Assert.assertTrue(commonActions.checkElementIsDisplayed(register_Lbl));
        Assert.assertTrue(commonActions.checkElementIsDisplayed(backToLogin_Btn));
        commonActions.clearFieldAndEnterText(email_Txt, email);
        commonActions.clearFieldAndEnterText(password_Txt, password);
        commonActions.clearFieldAndEnterText(passwordConfirm_Txt, confirmPassword);
        commonActions.click(register_Btn);
    }

    // To check error message in case of invalid login credentials.
    public void checkErrorMessageForInvalidRegister(String error) {
        Assert.assertTrue(commonActions.checkElementIsDisplayed(errorMsg));
        Assert.assertTrue(commonActions.compareText(errorMsg, error));
    }
    public void checkAndClickOnBackButton() {
        Assert.assertTrue(commonActions.checkElementIsDisplayed(backToLogin_Btn));
        commonActions.click(backToLogin_Btn);
    }
}