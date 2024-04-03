package rahulshettyacademy.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("raul@ortega.net");
		driver.findElement(By.id("userPassword")).sendKeys("Start123!");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animated")));

		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		
		/*
		driver.findElement(By.cssSelector("input[placeholder*='Select']")).sendKeys("Swi");

		
	
		List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-result"));

		Boolean matchCountry = countryList.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equals("Switzerland"));

		Assert.assertTrue(matchCountry);
		*/
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder*='Select']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		
		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
		assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.quit();
	}

}
