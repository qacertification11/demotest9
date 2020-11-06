package demotestapp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class weatherForecastPage extends testBase{

	
	@FindBy(xpath = "//input[@id='city']")
	WebElement cityNamesearch;

	@FindBy(xpath = "//input[@id='city']")
	WebElement srcButton;

	public weatherForecastPage() {
		PageFactory.initElements(driver, this );
	}

	// page actions
	public void validateResult(String cityName) {
		cityNamesearch.sendKeys(cityName);
		
		

		
	}
	
}
