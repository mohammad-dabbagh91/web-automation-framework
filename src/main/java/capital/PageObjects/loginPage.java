package capital.PageObjects;

//import static capital.capital.App.driver;
import capital.capital.CustomKeywords;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static capital.capital.App.driver;

public class loginPage {

	WebDriver driver;

	WebDriverWait wait;

	JavascriptExecutor js;


	public loginPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

		this.wait = new WebDriverWait(driver, 40L);

		this.js = (JavascriptExecutor) driver;

	}




CustomKeywords customKeywords = new CustomKeywords();



	// Here's getting the selector using Page Factory methodology and the selectors get retrieved as WebElement type

//	@FindBy(id = "username")
//	private WebElement username;

	@FindBy(id = "username")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(css = "[type = submit]")
	private WebElement submit;

	@FindBy(css = "[value='User menu']")
	private WebElement userMenu;

	@FindBy(id = "New Transfer")
	private WebElement newTransferBtn;

	@FindBy(css = "[title='Own Accounts']")
	private WebElement ownAccountsBtn;

	@FindBy(xpath = "//h1[contains(text(), 'Own Accounts')]")
	private WebElement ownAccountsHeader;

	@FindBy(xpath = "//form[@name='paymentForm']//div[@class='row']//h3[text()='From']/..//div[@data-ng-if='$ctrl.isLoading']")
	private WebElement fromAccountLoader;

	@FindBy(xpath = "//form[@name='paymentForm']//div[@class='row']//h3[text()='From']/..//ui-bb-account-selector-ng//div[@class='arrow-container']")
	private WebElement paymentFromArrow;

	@FindBy(xpath = "//form[@name='paymentForm']//div[@class='row']//h3[text()='From']/..//ul[contains(@class, 'uib-dropdown-menu')]//ui-bb-list-ng//li")
	private List<WebElement> fromAccountListItems;

	@FindBy(xpath = "//form[@name='paymentForm']//div[@class='row']//h3[text()='From']/..//ul[contains(@class, 'uib-dropdown-menu')]//ui-bb-list-ng")
	private WebElement fromAccountList;



	public void entersUsername(String usernameValue){
		wait.until(ExpectedConditions.visibilityOf(username));
		username.sendKeys(usernameValue);
	}

	public void entersPassword(String passwordValue){
		password.sendKeys(passwordValue);
	}

	public void clickingOnSubmit(){
		submit.click();
	}

	public void assertingThatUserIsLoggedin(){
		wait.until(ExpectedConditions.visibilityOf(userMenu));
		Assert.assertTrue(userMenu.isDisplayed(), "User didn't get logged in successfully");
	}

	public void navigateToMyOwnAccount(){
		newTransferBtn.click();
		ownAccountsBtn.click();
		wait.until(ExpectedConditions.visibilityOf(ownAccountsHeader));
		org.junit.Assert.assertTrue(ownAccountsHeader.isDisplayed());
		//customKeywords.fluentWaitIsInvisible(By.xpath("//form[@name='paymentForm']//div[@class='row']//h3[text()='From']/..//div[@data-ng-if='$ctrl.isLoading']"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@name='paymentForm']//div[@class='row']//h3[text()='From']/..//div[@data-ng-if='$ctrl.isLoading']")));

		boolean click = false;
		for(int i =1; i <=5; i ++) {
			try{
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@name='paymentForm']//div[@class='row']//h3[text()='From']/..//div[@data-ng-if='$ctrl.isLoading']")));
				System.out.println("fromAccountLoader is Invisible! " + i + " time");
				wait.until(ExpectedConditions.elementToBeClickable(paymentFromArrow));
				paymentFromArrow.click();
				wait.until(ExpectedConditions.visibilityOfAllElements(fromAccountList));
				click = true;
			} catch(Throwable t) {
				t.printStackTrace();
			}
			if(click == true){
				break;
			}else {
				continue;
			}
		}
	}

















	// Getters go here // Ignore them it's another way as getters not action methods

	public WebElement getusername() {
		return username;
	}

	public WebElement getpassword() {
		return password;
	}

	public WebElement getsubmit() {
		return submit;
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	// Temp for google

//	public WebElement getGoogleSearchField() {
//
//		return googleSearchField;
//	}


	@FindBy(css = "input[title='Search']")
	private WebElement googleSearchField;

	public void insertTextToSearch(String textToSeach){
		googleSearchField.sendKeys(textToSeach);
	}

	@FindBy(css = "input[value='Google Search']")
	private WebElement googleSearchButton;

	public void clickingOnSearchBtn(){
		googleSearchButton.click();
	}



	// This way is to handle verifying non-existing element
	private Optional<WebElement> webElementName(){
		try{
			return Optional.of(driver.findElement(By.cssSelector(".selector")));
		}catch(NoSuchElementException noElement){
			return Optional.empty();
		}
	}


	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	// Another way of getting selectors, it depends on getting them as By type
	
	private static By selector = null;
	
	
	public static By username() {
		selector = (By.id("username"));
		return selector;
	}
	
	public static By password() {
		selector = (By.id("password"));
		return selector;
	}
	
	public static By submit() {
		selector = (By.cssSelector("[type = submit]"));
		return selector;
	}

	public static By loaderFrom() {
		selector = (By.xpath("//form[@name='paymentForm']//div[@class='row']//h3[text()='From']/..//div[@data-ng-if='$ctrl.isLoading']"));
		return selector;
	}
	


}
