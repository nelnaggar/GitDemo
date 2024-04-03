package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class PageHeader extends AbstractComponent {

	WebDriver driver;

	public PageHeader(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".btn-custom")
	List<WebElement> buttons;

	public CartPage clickOnCart() {

		clickonButton("CART");
		return new CartPage(driver);
	}
	
	public OrdersPage clickonOrders() {
		clickonButton("ORDERS");
		return new OrdersPage(driver);
	}

	public void signOut() {
		clickonButton("SIGN OUT");

	}

	public void clickonButton(String buttonText) {
		buttons.stream().filter(b -> b.getText().toUpperCase().trim().contains(buttonText)).findFirst().orElse(null)
				.click();
	}

}
