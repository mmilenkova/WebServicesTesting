package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

public class LoginPage extends BasePage {
    private static final String URL = "/";
    private static final By emialFieldLocator = By.id("loginusername");
    private static final By passwordFieldLocator = By.id("loginpassword");
    private static final By loginButtonLocator = By.id("loginsubmit");
    private static final By companyNameLocator = By.xpath("//div[@id='wellcome']/h2");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigate(){
        navigate(URL);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public String getCompanyName(){
        return getText(companyNameLocator);
    }
    private void enterPassword(String password){
        type(passwordFieldLocator, password);
    }

    private void enterEmail(String email){
        type(emialFieldLocator, email);
    }

    private void clickLoginButton(){
        click(loginButtonLocator,"Login button" );
    }
}
