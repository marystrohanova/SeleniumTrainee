import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RedBoxTest {
	
	public WebDriver driver;

	@BeforeMethod
	public void setup(){
		driver = new FirefoxDriver();
		System.out.println(">>> Firefox Driver has been succesfully started");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();
		System.out.println(">>> Firefox Driver has been succesfully closed!");
	}
	
	public void initializeSearch(String searchString){
		By searchField = new By.ByCssSelector("#search0_SearchBox");
		WebElement weSearchField = driver.findElement(searchField);
		weSearchField.click();
		
		weSearchField.sendKeys(searchString + Keys.RETURN);
	}
		
	@Test
	public void PixelSearchTest() {
		String baseURL       = "http://www.redbox.com/";
		String movieTitle    = "Pixels";
		String movieRating 	 = "Rating: PG-13";
		String movieRuntime  = "Runtime: 1:46";
		WebDriverWait wait   = new WebDriverWait(driver, 13);
		
		driver.get(baseURL);
		initializeSearch(movieTitle);
		By movieIconLocator = new By.ByCssSelector(".box-art.box-hover");
		wait.until(ExpectedConditions.visibilityOfElementLocated(movieIconLocator));
		
		java.util.List<WebElement> movieIcons = driver.findElements(movieIconLocator);
		
		int actualCountOfMovies = movieIcons.size();
		int expectedCountOfMovies = 1;

		if (movieIcons.size() == expectedCountOfMovies){
			System.out.println("Number of results: " + movieIcons.size());
		}else {
			System.out.println("Number of results: " + movieIcons.size());
			System.out.println("Number of results was different then: " + actualCountOfMovies);
		}
		
		Assertion movieCountsAssert = new Assertion();
		movieCountsAssert.assertEquals(actualCountOfMovies, expectedCountOfMovies, 
								"Number of movies was found are different then: " + expectedCountOfMovies);
		
		
		By productNameLocator = new By.ByCssSelector(".product-name");
		WebElement weProductName = driver.findElement(productNameLocator);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator));

		String actualMovieName = weProductName.getText();
		System.out.println("Movie name was found: " + actualMovieName);
		
		Assertion movieNameAssert = new Assertion();
		movieNameAssert.assertEquals(actualMovieName, movieTitle, 
								"Movie title is different then " +  movieTitle);
		
		WebElement pixelIcon = driver.findElement(movieIconLocator);
		pixelIcon.click();
		
		By ratingLocator = new By.ByCssSelector(".meta-rating");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ratingLocator));
		
		WebElement weRating = driver.findElement(ratingLocator);
		String actualRatingText = weRating.getText();
		System.out.println("Rating was found: " + actualRatingText);
		
		Assertion ratingAssert = new Assertion();
		ratingAssert.assertEquals(actualRatingText, movieRating, 
								"Movie rating is different then " +  movieRating);
		
		By runtimeLocator = new By.ByCssSelector(".meta-running-time");
		wait.until(ExpectedConditions.visibilityOfElementLocated(runtimeLocator));
		
		WebElement weRuntime = driver.findElement(runtimeLocator);
		String actualRuntimeText = weRuntime.getText();
		System.out.println("Rating was found: " + actualRuntimeText);
		
		Assertion runtimeAssert = new Assertion();
		runtimeAssert.assertEquals(actualRuntimeText, movieRuntime, 
								"Movie rating is different then " +  movieRuntime);
		
		
	}
	
	@Test
	public void BatmanSearchTest(){
		String baseURL       = "http://www.redbox.com/";
		String movieTitle    = "Batman";
		WebDriverWait wait   = new WebDriverWait(driver, 13);

		driver.get(baseURL);
		initializeSearch(movieTitle);
		
		By movieIconLocator = new By.ByCssSelector(".box-art.box-hover");
		wait.until(ExpectedConditions.visibilityOfElementLocated(movieIconLocator));
		
		java.util.List<WebElement> movieIcons = driver.findElements(movieIconLocator);
		
		int expectedCountOfMovies = 15;
		int actualCountOfMovies = movieIcons.size();
		if (movieIcons.size() == expectedCountOfMovies){
			System.out.println("Number of results: " + movieIcons.size());
		}else {
			System.out.println("Number of results: " + movieIcons.size());
			System.out.println("Number of results was different then: " + actualCountOfMovies);

		}
		
		Assertion movieCountsAssert = new Assertion();
		movieCountsAssert.assertEquals(actualCountOfMovies, expectedCountOfMovies, 
								"Number of movies was found are different then: " + expectedCountOfMovies);
		
		movieIcons.get(0).click();
		By MovieNameLocator = new By.ByCssSelector("h1.details-title-name");

		WebElement weMovieName = driver.findElement(MovieNameLocator);
		String actualMovieName = weMovieName.getText();
		System.out.println("Movie name was found: " + actualMovieName);
		
		Assertion movieNameAssert = new Assertion();
		movieNameAssert.assertEquals(true, actualMovieName.contains(movieTitle), "Movie title doesn't contain " + movieTitle);
		
	}
	

}
