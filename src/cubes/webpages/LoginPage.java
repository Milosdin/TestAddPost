package cubes.webpages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	private static final String PAGE_URL = "http://testblog.kurs-qa.cubes.edu.rs/login";
	private static final String ADDPOST_URL ="https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add";
	
	//WebElements
	@FindBy(name="email")
	private WebElement weEmail;
	@FindBy(name="password")
	private WebElement wePassword;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement weButton;
	
	public LoginPage(WebDriver driver, WebDriverWait driverWait) {
		this.driver = driver;
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver,this);
	}
	
	public void login(String email,String password) {
		inputStringInEmail(email);
		inputStringInPassword(password);
		clickOnSignIn();	
	}
	public void inputStringInEmail(String email) {
		weEmail.clear();
		weEmail.sendKeys(email);
	}
	public void inputStringInPassword(String password) {
		wePassword.clear();
		wePassword.sendKeys(password);
	}
	public void clickOnSignIn() {
		weButton.click();
	}

    public void addPostPage() {
		driver.get(ADDPOST_URL);
	}
}
