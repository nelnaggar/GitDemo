package rahulshettyacademy.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement txtUserEmail;

	@FindBy(id = "userPassword")
	WebElement txtPassword;

	@FindBy(id = "login")
	WebElement btnLogin;
	
	@FindBy(css = "div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;
	

	public ProductCatalog loginApplication(String email, String pass) {

		txtUserEmail.sendKeys(email);
		txtPassword.sendKeys(pass);
		btnLogin.click();
		
		return new ProductCatalog(driver);

	}

	public LandingPage goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		return new LandingPage(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}

}
