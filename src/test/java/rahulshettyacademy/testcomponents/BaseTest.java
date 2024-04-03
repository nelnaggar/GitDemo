package rahulshettyacademy.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initialiseDriver() throws IOException {

		String userDir = System.getProperty("user.dir");

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				userDir + "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");

		prop.load(fis);

		String browserName;

		if (System.getProperty("browser") != null) {
			browserName = System.getProperty("browser");
		} else {
			browserName = prop.getProperty("browser");
		}

		if (browserName.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();

			WebDriverManager.chromedriver().setup();

			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// System.getProperty("webdriver.gecko.driver", "D:\\Firefox Driver");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;

		String filePath = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		File src = ts.getScreenshotAs(OutputType.FILE);

		File dst = new File(filePath);

		FileUtils.copyFile(src, dst);

		return filePath;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchAppication() throws IOException {
		driver = initialiseDriver();
		landingPage = new LandingPage(driver).goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void signOut() {
		driver.quit();

	}

}
