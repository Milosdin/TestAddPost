package cubes.webpages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostsFormPage {

	private WebDriver driver;
	private WebDriverWait driverWait;
	private static final String POSTS_URL = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts";

	// WebElements
	@FindBy(xpath = "//label[.='Title']//following-sibling::input[@name='title']")
	private WebElement titleSearch;
	@FindBy(xpath = " //form[@id='entities-filter-form']//span[text()='--Choose Author --']")
	private WebElement authorClick;
	@FindBy(xpath = " //form[@id='entities-filter-form']//span[text()='--Choose Category --']")
	private WebElement categoryClick;
	@FindBy(xpath = "//select[@name='important']")
	private WebElement importantOption;
	@FindBy(xpath = "//select[@name='status']")
	private WebElement statusOption;
	@FindBy(xpath = "//a[@class='btn btn-success']")
	private WebElement addNewPostButton;
	@FindBy(xpath = "//select[@name='entities-list-table_length']//child::option[@value='10']")
	private WebElement clickOnTheShow10;
	@FindBy(xpath = "//select[@name='entities-list-table_length']//child::option[@value='25']")
	private WebElement clickOnTheShow25;
	@FindBy(xpath = "//select[@name='entities-list-table_length']//child::option[@value='50']")
	private WebElement clickOnTheShow50;
	@FindBy(xpath = "//input[@class='form-control form-control-sm']")
	private WebElement searchField;
	@FindBy(xpath = "//a[@data-dt-idx='2']")
	private WebElement wePage2;
	@FindBy(xpath = "//div[@class='modal-footer justify-content-between']//child::button[@class='btn btn-danger']//i[@class='fas fa-minus-circle']")
	private WebElement dialogDisable;
	@FindBy(xpath = "//form[@id='enable-modal']//i[@class='fas fa-check']")
	private WebElement dialogEnable;
	@FindBy(xpath = "//form[@id='unimportant-modal']//i[@class='fas fa-minus-circle']")
	private WebElement dialogUnimportant;
	@FindBy(xpath = "//form[@id='important-modal']//button[@class='btn btn-success']")
	private WebElement dialogImportant;
	@FindBy(xpath = "//div[@class='modal-dialog']//ancestor::div/div/button[text()='Delete']")
	private WebElement dialogDelete;
	@FindBy(xpath = "//li[@class='breadcrumb-item active']")
	private WebElement editPostPage;
	@FindBy(xpath = "//input[@class='select2-search__field']")
	private WebElement weTag;
	@FindBy(xpath = "//span[@class='select2-search select2-search--dropdown']/input[@class='select2-search__field']")
	private WebElement weAuthor;
	@FindBy(xpath = "//span[@class='select2-search select2-search--dropdown']/input[@class='select2-search__field']")
	private WebElement weCategorySearch;
	@FindBy(xpath = "//select[@name='important']//child::option[text()='yes']")
	private WebElement weImportantYes;
	@FindBy(xpath = "//select[@name='important']//child::option[text()='no']")
	private WebElement weImportantNo;
	@FindBy(xpath = "//select[@name='status']//child::option[text()='enabled']")
	private WebElement statusOptionEnabled;
	@FindBy(xpath = "//select[@name='status']//child::option[text()='disabled']")
	private WebElement statusOptionDisabled;
	@FindBy(xpath = "//a[text()='Next']")
	private WebElement nextButton;
	@FindBy(xpath = "//a[text()='Previous']")
	private WebElement previousButton;
	@FindBy(xpath = "//th[@class='sorting_asc']")
	private WebElement descSorting;
	@FindBy(xpath = "//th[@class='sorting_desc']")
	private WebElement ascSorting;

	public PostsFormPage(WebDriver driver, WebDriverWait driverWait) {
		this.driver = driver;
		this.driverWait = driverWait;
		PageFactory.initElements(driver, this);
	}

	public void postPage() {
		driver.get(POSTS_URL);
	}

	public void titleSearch(String title) {
		driverWait.until(ExpectedConditions.visibilityOf(titleSearch));
		titleSearch.clear();
		titleSearch.sendKeys(title);
	}
	
	public boolean titleConfirm(String title) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean isInList =driver.findElements(By.xpath("//table/tbody/tr/td[text()='"+title+"']")).size() > 0;
		return isInList;
	}

	public void withTag(String title) {
		weTag.sendKeys(title);
		weTag.sendKeys(Keys.ENTER);
	}

	public void authorSearch(String author) {
		authorClick.click();
		weAuthor.sendKeys(author);
		weAuthor.sendKeys(Keys.ENTER);
	}
	
	public void categorySearch(String category) {
		categoryClick.click();
		weCategorySearch.sendKeys(category);
		weCategorySearch.sendKeys(Keys.ENTER);
	}

	public void importantYes() {
		importantOption.click();
		weImportantYes.click();
		importantOption.click();
	}
	
	public void importantNo() {
		importantOption.click();
		weImportantNo.click();
		importantOption.click();
	}

	public void statusEnabled() {
		statusOption.click();
		statusOptionEnabled.click();
		statusOption.click();
	}

	public void statusDisabled() {
		statusOption.click();
		statusOptionDisabled.click();
		statusOption.click();
	}
	
	public void clickOnTheAddNewPostButton() {
		addNewPostButton.click();
	}

	public void clickOnTheShow10EntriesSelectButton() {
		clickOnTheShow10.click();
	}

	public void clickOnTheShow25EntriesSelectButton() {
		clickOnTheShow25.click();
	}

	public void clickOnTheShow50EntriesSelectButton() {
		clickOnTheShow50.click();
	}
	
	public int rowsSize() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='entities-list-table']//ancestor::tbody/tr"));
		return rows.size();
	}
	
	public void searchField(String search) {
		searchField.clear();
		searchField.sendKeys(search);
	}
	
	public void viewPostButton(String view) {
		WebElement viewButton = driver.findElement(By.xpath("//td[text()='"+view+"']//following-sibling::td[7]/div[1]/a[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewButton);
	}
	
	public void clickOnPage2() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", wePage2);
	}
	
	public boolean blogPostConfirm(String blogConfirm) {
		driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
		boolean elementText = driver.findElements(By.xpath("//main[@class='post blog-post col-lg-8']//ancestor::div/div/div/h1[contains(text(),'"+blogConfirm+"')]")).size() > 0;
		return elementText;
	}

	public void disablePostButton(String disable) {
		WebElement disableButton = driver.findElement(By.xpath("//td[text()='"+disable+"']//following-sibling::td[7]/div[2]/button[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", disableButton);
	}
	
	public void dialogDisable() {
		driverWait.until(ExpectedConditions.visibilityOf(dialogDisable));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dialogDisable);
	}
	public void enablePostButton(String enable) {
		WebElement enableButton = driver.findElement(By.xpath("//td[text()='"+enable+"']//following-sibling::td[7]/div[2]/button[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", enableButton);
	}
	
	public void dialogEnable() {
		driverWait.until(ExpectedConditions.visibilityOf(dialogEnable));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dialogEnable);
	}
	
	public void unimportantPostButton(String unimportant) {
		WebElement unimportantButton = driver.findElement(By.xpath("//td[text()='"+unimportant+"']//following-sibling::td[7]/div[2]/button[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", unimportantButton);
	}
	
	public void dialogUnimportantPost() {
        driverWait.until(ExpectedConditions.visibilityOf(dialogUnimportant));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dialogUnimportant);
	}
	
	public void importantPostButton(String important) {
		WebElement importantButton = driver.findElement(By.xpath("//td[text()='"+important+"']//following-sibling::td[7]/div[2]/button[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", importantButton);
	}
	
	public void dialogImportantPost() {
        driverWait.until(ExpectedConditions.visibilityOf(dialogImportant));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dialogImportant);
	}
	
	public void editPostButton(String edit) {
		WebElement editButton = driver.findElement(By.xpath("//td[text()='"+edit+"']//following-sibling::td[7]/div[1]/a[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
	}
	
	public String editPostPageConfirm() {
		return editPostPage.getText();
	}
	
	public void deletePostButton(String delete) {
		WebElement deleteButton = driver.findElement(By.xpath("//td[text()='"+delete+"']//following-sibling::td[7]/div[1]/button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
	}
	
	public void dialogDeletePost() {
        driverWait.until(ExpectedConditions.visibilityOf(dialogDelete));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dialogDelete);
	}
	
	public void clickOnNextPageButton() {
		driverWait.until(ExpectedConditions.visibilityOf(nextButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
	}
	
    public void clickOnPreviousPageButton() {
    	driverWait.until(ExpectedConditions.visibilityOf(previousButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", previousButton);
	}
    public boolean nextButtonConfirm() {
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean nextConfirm = driver.findElements(By.xpath("//li[@class='paginate_button page-item active']/a[text()='2']")).size() > 0;

		return nextConfirm;
	}
    public boolean previousButtonConfirm() {
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean previousConfirm = driver.findElements(By.xpath("//li[@class='paginate_button page-item active']/a[text()='1']")).size() > 0;

		return previousConfirm;
	}
    
    public void clickOnNumberingColumnWithDESCSortButton () {
		driverWait.until(ExpectedConditions.visibilityOf(descSorting));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", descSorting);
	}
    public boolean clickOnNumberingColumnDESCSortButtonConfirm() {
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean descConfirm = driver.findElements(By.xpath(" //th[@class='sorting_desc']")).size() > 0;

		return descConfirm;
	}
    
    public void clickOnNumberingColumnWithASCSortButton () {
		driverWait.until(ExpectedConditions.visibilityOf(ascSorting));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ascSorting);
	}
    
    public boolean clickOnNumberingColumnASCSortButtonConfirm() {
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean ascConfirm = driver.findElements(By.xpath("//th[@class='sorting_asc']")).size() > 0;

		return ascConfirm;
    }
    
    public void clickOnColumnSortButton(String column) {
    	WebElement columnSort = driver.findElement(By.xpath("//th[.='"+column+"']"));
		driverWait.until(ExpectedConditions.visibilityOf(columnSort));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", columnSort);
	}
    
    public boolean clickOnColumnASCSortButtonConfirm(String asc) {
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean ascConfirm = driver.findElements(By.xpath("//th[@aria-label='"+asc+": activate to sort column descending']")).size() > 0;

		return ascConfirm;
	}
    
    public boolean clickOnColumnDESCSortButtonConfirm(String desc) {
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean descConfirm = driver.findElements(By.xpath("//th[@aria-label='"+desc+": activate to sort column ascending']")).size() > 0;

		return descConfirm;
    }
    
	
}
