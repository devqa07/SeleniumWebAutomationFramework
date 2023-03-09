package com.dev.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.dev.web.utils.CommonActions;

public class LoginPageElements {

    public WebDriver driver;
    public CommonActions commonActions;

    public LoginPageElements(WebDriver driver) {
        this.driver = driver;
        commonActions = new CommonActions(driver);
    }

    // All objects should be defined here
    private By email_Txt = By.id("username");
    private By password_Txt = By.id("password");
    private By login_Btn = By.cssSelector("#kc-login");
    private By rememberMe_Btn = By.id("rememberMe");
    private By forgotPassword_Lbl = By.linkText("Forgot Password?");
    private By errorMsg = By.cssSelector("#input-error");
    public By forgotPassword_Txt = By.xpath("//h1[@id='kc-page-title']");
    private By backToLogin_Btn = By.linkText("« Back to Login");
    private By register_Btn = By.linkText("Register");

    // To fill the login form using email and password and click on sign in button
    public void completeLoginForm(String email, String password) {
        Assert.assertTrue(commonActions.checkElementIsDisplayed(rememberMe_Btn));
        Assert.assertTrue(commonActions.checkElementIsDisplayed(forgotPassword_Lbl));
        commonActions.clearFieldAndEnterText(email_Txt, email);
        commonActions.clearFieldAndEnterText(password_Txt, password);
        commonActions.click(login_Btn);
    }

    // To check error message in case of invalid login credentials.
    public void checkErrorMessageForInvalidLogin(String error) {
        Assert.assertTrue(commonActions.checkElementIsDisplayed(errorMsg));
        Assert.assertTrue(commonActions.compareText(errorMsg, error));
    }

    // verify the forgot password page
    public void checkForgotPasswordPage() {
        commonActions.click(forgotPassword_Lbl);
        Assert.assertTrue(commonActions.checkElementIsDisplayed(forgotPassword_Txt));
    }

    // go back to login screen
    public void goBackToLoginScreen() {
        commonActions.click(backToLogin_Btn);
    }

    // checked the remember me option
    public void clickOnRememberMe() {
        commonActions.click(rememberMe_Btn);
    }

    // logged out and check the email is showing or not
    public void checkEmailIdAfterLogOut(String email) {
        String emailAfterLoggedOut = commonActions.getText(email_Txt);
        Assert.assertTrue(emailAfterLoggedOut.equalsIgnoreCase(email));
    }

    public void clickOnRegister() {
        commonActions.click(register_Btn);
    }
}