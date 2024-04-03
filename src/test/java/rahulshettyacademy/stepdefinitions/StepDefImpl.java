package rahulshettyacademy.stepdefinitions;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.PageHeader;
import rahulshettyacademy.pageobjects.PaymentPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.testcomponents.BaseTest;

public class StepDefImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirm;

	@Given("User landed on the e-commerce page.")
	public void User_landed_on_ecommerce_page() throws IOException {
		landingPage = launchAppication();
	}

	@Given("^User is logged in with user name (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String userName, String password) {
		productCatalog = landingPage.loginApplication(userName, password);
	}

	@When("^User adds product (.+) to cart$")
	public void add_the_Product(String productName) {
		productCatalog.addProductToCart(productName);
	}

	@When("^checks out (.+) and submits the order$")
	public void checkout_and_submit_order(String productName) {

		CartPage cart = new PageHeader(driver).clickOnCart();

		Boolean match = cart.checkIfProductExists(productName);

		assertTrue(match);

		PaymentPage pay = cart.checkOut();

		pay.selectCountry("india", 1);

		pay.placeOrder();

		confirm = pay.placeOrder();

	}

	
	@Then("{string} is displayed on confirmation page.")
	public void messageDisplayed(String string) {

		Boolean match = confirm.getConfirmationText().equalsIgnoreCase(string);
		assertTrue(match);

		driver.quit();

	}
	
	@Then("{string} error message is displayed.")
	public void errorNessageDisplayed(String string) {

		Boolean match = landingPage.getErrorMessage().equalsIgnoreCase(string);
		assertTrue(match);

		driver.quit();

	}
	
	
	
	
}
