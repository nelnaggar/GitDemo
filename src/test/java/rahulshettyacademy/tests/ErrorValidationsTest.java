package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.PageHeader;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.testcomponents.BaseTest;
import rahulshettyacademy.testcomponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginValidation() throws IOException {

		landingPage.loginApplication("raul@ortega.net", "Start123#");

		AssertJUnit.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";

		ProductCatalog productCatalog = landingPage.loginApplication("raul@ortega.net", "Start123!");

		productCatalog.addProductToCart(productName);

		CartPage cart = new PageHeader(driver).clickOnCart();

		AssertJUnit.assertFalse(cart.checkIfProductExists("ZARA COAT 33"));

	}
}
