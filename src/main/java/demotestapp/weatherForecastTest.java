package demotestapp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class weatherForecastTest extends testBase {
	weatherForecastPage wf;

	@BeforeMethod
	public void setup() {
		initilaisation();
		wf = new weatherForecastPage();
	}

	public weatherForecastTest() {
		super();
		PageFactory.initElements(driver, this);
	}

	@Test
	public void check5daysweather() {

		System.out.println("=========================================================");
		System.out.println("Enter city name, get 5 day weather forecast");

		driver.findElement(By.cssSelector("#city")).click();
		driver.findElement(By.cssSelector("#city")).clear();
		driver.findElement(By.cssSelector("#city")).click();
		driver.findElement(By.cssSelector("#city")).sendKeys("Perth");
		driver.findElement(By.cssSelector("#city")).sendKeys(Keys.ENTER);

		// get 5 day weather forecast
		List<WebElement> tRows = driver.findElements(By.xpath("//div[@id='root']/div[1]/div"));
		System.out.println("Number of rows - " + tRows.size());
		System.out.println("Temperature for next 5 days - ");
		List<WebElement> temps = driver.findElements(By.xpath("//div[@id='root']/div[1]/div/div[1]/span[3]/span[1]"));
		String cellData = null;
		for (int j = 1; j <= temps.size(); j++) {
			for (int i = 1; i <= 5; i++) {
				cellData = driver
						.findElement(By.xpath("//div[@id='root']/div[1]/div[" + j + "]/div[1]/span[" + i + "]"))
						.getText();
				System.out.print(" -- " + cellData);
			}
			System.out.println();
		}
	}

	@Test
	public void get3hrForecast() {
		System.out.println("=========================================================");
		System.out.println(" Select any day, get 3 hourly forecast");
		driver.findElement(By.cssSelector("#city")).click();
		driver.findElement(By.cssSelector("#city")).clear();
		driver.findElement(By.cssSelector("#city")).click();
		driver.findElement(By.cssSelector("#city")).sendKeys("Edinburgh");
		driver.findElement(By.cssSelector("#city")).sendKeys(Keys.ENTER);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.findElement(By.xpath("//div[@id='root']/div[1]/div[2]/div[1]/span[1]/span[1]")).click();

		List<WebElement> subrows = driver
				.findElements(By.xpath("//div[@data-radium='true']/div[2]/div[2]/div/span[1]"));
		System.out.println("Number of Subrows are :: " + subrows.size());

		String subCellData = null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@data-radium='true']/div[2]/div[2]/div[1]/span[1]/span[1]")));

		for (int i = 1; i <= subrows.size(); i++) {
			for (int j = 1; j <= 5; j++) {
				if (j != 2) {
					subCellData = driver
							.findElement(By.xpath(
									"//div[@data-radium='true']/div[2]/div[2]/div[" + i + "]/span[" + j + "]/span[1]"))
							.getText();
					System.out.print(subCellData + " -- ");
				}
			}
			System.out.println();
		}

		// Select day again, hide 3 hourly forecast
		String day = "Fri";
		String title = driver.findElement(By.xpath("//span[normalize-space()='" + day + "']")).getText();
		driver.findElement(By.xpath("//span[normalize-space()='Fri']")).click();
		System.out.println("Expanded Day is :" + day);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Fri']"))).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
