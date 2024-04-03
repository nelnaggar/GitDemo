package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder*='Select']")
	WebElement txtCountry;
	
	@FindBy(xpath =  "//button[contains(@class,'ta-item')]")
	List<WebElement> btnCountry;
	
	@FindBy(css = ".action__submit")
	WebElement btnPlaceOrder;
	
	By results = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String txt, int index) {
		
		Actions a = new Actions(driver);
		a.sendKeys(txtCountry, txt).build().perform();
		waitForElementToAppear(results);

		btnCountry.get(index).click();
		
	}
	
	public ConfirmationPage placeOrder() {
		btnPlaceOrder.click();
		return new ConfirmationPage(driver);
	}
	
	
}
