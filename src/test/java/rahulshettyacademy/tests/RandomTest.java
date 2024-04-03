package rahulshettyacademy.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RandomTest {

	public static void main(String[] args) throws InterruptedException {
		setDriver("https://rahulshettyacademy.com/client");

		WebElement txtUserEmail = driver.findElement(By.id("userEmail"));
		WebElement txtPassword = driver.findElement(By.id("userPassword"));
		WebElement btnLogin = driver.findElement(By.id("login"));
		
		
		
		txtUserEmail.sendKeys("raul@ortega.net");
		txtPassword.sendKeys("Start123!");
		btnLogin.click();

		Thread.sleep(2000);
		
		
		
		
		
		
		driver.findElements(By.cssSelector(".btn-custom")).stream().filter(b -> b.getText().trim().equalsIgnoreCase("CART")).findFirst().orElse(null).click();
		
		driver.findElements(By.cssSelector(".btn-custom")).stream().forEach(b -> System.out.println(b.getText().trim()));
		
		Thread.sleep(2000);
		
		driver.quit();

	}

	static WebDriver driver;

	static void setDriver(String url) {
		System.setProperty("webdriver.com.driver", "D:\\Chrome Driver\\chrome.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);

	}

}
