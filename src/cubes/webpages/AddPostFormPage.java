package cubes.webpages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddPostFormPage {

	private WebDriver driver;
	private WebDriverWait driverWait;
	private static final String ADDPOST_URL = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add";

	// WebElements
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement addButton;
	@FindBy(xpath = "//i[@class='fas fa-bars']")
	private WebElement responsiveBarButton;
	@FindBy(xpath = "//i[@class='far fa-user']")
	private WebElement dropMenu;
	@FindBy(xpath = "//a[text()='Home']")
	private WebElement homeButton;
	@FindBy(xpath = "//a[text()='Post']")
	private WebElement postButton;
	@FindBy(id = "title-error")
	private WebElement weErrorTitle;
	@FindBy(id = "description-error")
	private WebElement weErrorDescription;
	@FindBy(id = "tag_id[]-error")
	private WebElement weErrorTag;
	@FindBy(id = "invalid-feedback")
	private WebElement weErrorContent;
	@FindBy(name = "title")
	private WebElement weTitleInput;
	@FindBy(name = "description")
	private WebElement weDescriptionInput;
	@FindBy(name = "post_category_id")
	private WebElement categoryClick;
	@FindBy(xpath = "//label[@for='set-as-important']")
	private WebElement importantYesOption;
	@FindBy(xpath = "//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	private WebElement weContentInput;
	@FindBy(xpath = "//input[@name='photo']")
	private WebElement photoInput;
	@FindBy(xpath = "//a[@class='btn btn-outline-secondary']")
	private WebElement cancelButton;
	@FindBy(xpath = "//select[@name='entities-list-table_length']//child::option[@value='100']")
	private WebElement clickOnTheShow100;
	@FindBy(xpath = "//a[@data-dt-idx='2']")
	private WebElement wePage2;
	@FindBy(xpath = "//a[text()='Cubes School']")
	private WebElement cubesSchool;

	public AddPostFormPage(WebDriver driver, WebDriverWait driverWait) {
		this.driver = driver;
		this.driverWait = driverWait;
		PageFactory.initElements(driver, this);
	}
	public void openMenu(String title) {
        WebElement weMenu = driver.findElement(By.xpath("//p[contains(text(),'"+title+"')]//parent::a[@href='#']"));
			weMenu.click();
	}
	
	public boolean menuConfirm(String title) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean menuBarConfirm = driver.findElements(By.xpath("//li[@class='nav-item has-treeview menu-open']//child::a[contains(.,'"+title+"')]")).size() > 0;

		return menuBarConfirm;
	}
	
	public void clickOnResponsiveNavigationBarButton() {
		responsiveBarButton.click();
	}
	
	public boolean miniSideBarCollapse() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean responsiveBarConfirm = driver.findElements(By.xpath("//body[@class='sidebar-mini sidebar-collapse']")).size() > 0;

		return responsiveBarConfirm;
	}
	
	public void dropDownMenuClick() {
		dropMenu.click();
	}
	
	public boolean dropDownMenuShow() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean dropDownMenu = driver.findElements(By.xpath("//li[@class='nav-item dropdown show']")).size() > 0;

		return dropDownMenu;
	}
	public void homeMenuClick() {
		homeButton.click();
	}
	
	public void postMenuClick() {
		postButton.click();
	}

	public void addPostPage() {
		driver.get(ADDPOST_URL);
	}

	public void addButton() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
	}

	public String getTitleInputError() {
		return weErrorTitle.getText();
	}

	public String getDescriptionInputError() {
		return weErrorDescription.getText();
	}

	public String getTagsInputError() {
		return weErrorTag.getText();
	}

	public String getContentInputError() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return weErrorContent.getText();
	}

	public void clickOnTheShow100EntriesSelectButton() {
		clickOnTheShow100.click();
	}

	public void clickOnPage2searchTitle() {
		driverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='toast-message']"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", wePage2);
	}

	public boolean isTitleInList(String title) {
		clickOnTheShow100EntriesSelectButton();
		clickOnPage2searchTitle();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean isInList = driver.findElements(By.xpath("//td[text()='" + title + "']")).size() > 0;

		return isInList;
	}

	public boolean authorConfirm(String author) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean isAuthorInList = driver.findElements(By.xpath("//table/tbody/tr/td[text()='" + author + "']")).size() > 0;

		return isAuthorInList;
	}

	public boolean categoryConfirm(String category) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean isCategoryInList = driver.findElements(By.xpath("//table/tbody/tr/td[contains(text(),'" + category + "')]")).size() > 0;

		return isCategoryInList;
	}

	public boolean tagConfirm(String tag) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean isTagInList = driver.findElements(By.xpath("//table/tbody/tr/td[contains(text(),'" + tag + "')]")).size() > 0;

		return isTagInList;
	}

	public boolean importantYesConfirm() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean isImportantYesInList = driver.findElements(By.xpath("//table/tbody/tr/td/span[text()='Yes']")).size() > 0;

		return isImportantYesInList;
	}

	public boolean importantNoConfirm() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean isImportantNoInList = driver.findElements(By.xpath("//tr//span[@class='text-danger']")).size() > 0;

		return isImportantNoInList;
	}

	public boolean statusEnabledConfirm() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean isStatusEnabledInList = driver.findElements(By.xpath("//table/tbody/tr/td/span[text()='enabled']")).size() > 0;

		return isStatusEnabledInList;
	}

	public boolean statusDisabledConfirm() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean isStatusDisabledInList = driver.findElements(By.xpath("//span[text()='disabled']")).size() > 0;

		return isStatusDisabledInList;
	}

	public boolean photoInputError() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean photoError = driver.findElements(By.xpath("//span[text()='This page isn’t working']")).size() > 0;

		return photoError;
	}

	public void titleInput(String title) {
		weTitleInput.clear();
		weTitleInput.sendKeys(title);
	}

	public void descriptionInput(String description) {
		weDescriptionInput.clear();
		weDescriptionInput.sendKeys(description);
	}

	public void tagsClick(String tags) {
		WebElement weTagClick = driver.findElement(By.xpath("//label[text()='" + tags + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", weTagClick);
	}

	public void contentInput(String content) {
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='cke_1_contents']//child::iframe")));
		weContentInput.clear();
		weContentInput.sendKeys(content);
		driver.switchTo().parentFrame();
	}

	public void categoryInput(String category) {
		categoryClick.click();
		WebElement weCategory = driver.findElement(By.xpath("//select[@name='post_category_id']//ancestor::option[contains(text(),'" + category + "')]"));
		weCategory.click();
	}

	public void importantYes() {
		importantYesOption.click();
	}

	public void photoInput(String filePath) {
		photoInput.sendKeys(filePath);
	}

	public void cancelClick() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cancelButton);
	}
	
	public void clickOnCubesSchoolLink() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cubesSchool);
	}
	

}
