package rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.PageHeader;
import rahulshettyacademy.pageobjects.PaymentPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		productCatalog.addProductToCart(input.get("product"));

		CartPage cart = new PageHeader(driver).clickOnCart();

		AssertJUnit.assertTrue(cart.checkIfProductExists(input.get("product")));

		PaymentPage pay = cart.checkOut();

		pay.selectCountry("india", 1);

		pay.placeOrder();

		ConfirmationPage confirm = pay.placeOrder();

		AssertJUnit.assertTrue(confirm.getConfirmationText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void orderHistoryTest() {

		landingPage.loginApplication("raul@ortega.net", "Start123!");

		OrdersPage ordersPage = new PageHeader(driver).clickonOrders();

		Assert.assertTrue(ordersPage.checkIfProductExists(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		/*
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "raul@ortega.net"); map1.put("password", "Start123!");
		 * map1.put("product", "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map2 = new HashMap<String, String>();
		 * map2.put("email", "carlos@ortega.net"); map2.put("password", "Start124!");
		 * map2.put("product", "ADIDAS ORIGINAL");
		 */

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
