package cubes.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.webpages.LoginPage;
import cubes.webpages.AddPostFormPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAddPost {

	private static WebDriver driver;
	private static WebDriverWait driverWait;
	private static LoginPage loginPage;
	private static AddPostFormPage addPost;
	private static final String EMAIL = "kursqa@cubes.edu.rs";
	private static final String PASSWORD = "cubesqa";
	private static final String TITLE_INVALID = "abc123";
	private static final String DESCRIPTION_INVALID = "descriptio";
	private static final String TITLE_VALID = "MilosMilosMilosMilos";
	private static final String TITLE_VALID_FOR_DELETE = "DELETE_MilosMilosMilosMilos";
	private static final String DESCRIPTION_VALID = "descriptiodescriptiodescriptiodescriptiodescriptio";
	private static final String TAG = "Default TAG NE BRISATI";
	private static final String CONTENT = "content";
	private static final String CATEGORY = "EastEvafurt";
	private static final String PHOTO = "C:\\Users\\Korisnik\\Desktop\\zeppelin-17445.jpg";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Korisnik\\Desktop\\webdriverCromeFire\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(7));
		driver.manage().window().maximize();

		loginPage = new LoginPage(driver, driverWait);
		addPost = new AddPostFormPage(driver, driverWait);

		loginPage.login(EMAIL, PASSWORD);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		addPost.addPostPage();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc004TestBlogSidebarLinks() {
		checkMenu("Sliders");
		checkMenu("Post Categories");
		checkMenu("Tags");
		checkMenu("Posts");
		checkMenu("Comments");
		checkMenu("Users");
	}

	@Test
	public void tc005TestResponsiveNavigationBar() {
		addPost.clickOnResponsiveNavigationBarButton();

		assertEquals(addPost.miniSideBarCollapse(), true);
	}

	@Test
	public void tc006TestProfileIconDropDownMenu() {
		addPost.dropDownMenuClick();

		assertEquals(addPost.dropDownMenuShow(), true);
	}

	@Test
	public void tc007TestHomeLink() {
		addPost.homeMenuClick();

		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin");
	}

	@Test
	public void tc008TestPostLink() {
		addPost.postMenuClick();

		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
	}

	@Test
	public void tc009TestEmptyFields() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc010TestTitleInvalid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc011TestTitleInvalidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc012TestTitleInvalidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc013TestTitleInvalidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc014TestTitleInvalidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc015TestTitleInvalidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc016TestTitleInvalidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc017TestTitleInvalidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc018TestTitleInvalidDescriptionInvalid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc019TestTitleInvalidDescriptionInvalidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc020TestTitleInvalidDescriptionInvalidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc021TestTitleInvalidDescriptionInvalidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc022TestTitleInvalidDescriptionInvalidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc023TestTitleInvalidDescriptionInvalidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc024TestTitleInvalidDescriptionInvalidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc025TestTitleInvalidDescriptionInvalidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc026TestTitleInvalidDescriptionValid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc027TestTitleInvalidDescriptionValidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc028TestTitleInvalidDescriptionValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc029TestTitleInvalidDescriptionValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc030TestTitleInvalidDescriptionValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc031TestTitleInvalidDescriptionValidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.importantYes();
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc032TestTitleInvalidDescriptionValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc033TestTitleInvalidDescriptionValidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc034TestTitleInvalidTagsValid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc035TestTitleInvalidTagsValidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc036TestTitleInvalidTagsValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc037TestTitleInvalidTagsValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc038TestTitleInvalidTagsValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc039TestTitleInvalidTagsValidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc040TestTitleInvalidTagsValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc041TestTitleInvalidTagsValidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc042TestTitleInvalidContentValid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc043TestTitleInvalidContentValidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc044TestTitleInvalidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc045TestTitleInvalidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc046TestTitleInvalidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc047TestTitleInvalidContentValidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc048TestTitleInvalidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc049TestTitleInvalidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc050TestTitleValid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tag input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc051TestTitleValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc052TestTitleValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc053TestTitleValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc054TestTitleValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc055TestTitleValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc056TestTitleValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc057TestTitleValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc058TestTitleValidDescriptionInvalid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc059TestTitleValidDescriptionInvalidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc060TestTitleValidDescriptionInvalidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc061TestTitleValidDescriptionInvalidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc062TestTitleValidDescriptionInvalidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc063TestTitleValidDescriptionInvalidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc064TestTitleValidDescriptionInvalidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc065TestTitleValidDescriptionInvalidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc066TestTitleValidDescriptionValid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tag input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc067TestTitleValidDescriptionValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc068TestTitleValidDescriptionValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc069TestTitleValidDescriptionValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc070TestTitleValidDescriptionValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc071TestTitleValidDescriptionValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc072TestTitleValidDescriptionValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc073TestTitleValidDescriptionValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc074TestTitleValidTagsValid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc075TestTitleValidTagsValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc076TestTitleValidTagsValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc077TestTitleValidTagsValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc078TestTitleValidTagsValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc079TestTitleValidTagsValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc080TestTitleValidTagsValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc081TestTitleValidTagsValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc082TestTitleValidContentValid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc083TestTitleValidContentValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc084TestTitleValidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc085TestTitleValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc086TestTitleValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc087TestTitleValidContentValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc088TestTitleValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc089TestTitleValidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc090TestDescriptionInvalid() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc091TestDescriptionInvalidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc092TestDescriptionInvalidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc093TestDescriptionInvalidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc094TestDescriptionInvalidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc095TestDescriptionInvalidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc096TestDescriptionInvalidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc097TestDescriptionInvalidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc098TestDescriptionInvalidTagsValid() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc099TestDescriptionInvalidTagsValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc100TestDescriptionInvalidTagsValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc101TestDescriptionInvalidTagsValidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc102TestDescriptionInvalidTagsValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc103TestDescriptionInvalidTagsValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc104TestDescriptionInvalidTagsValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc105TestDescriptionInvalidTagsValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc106TestDescriptionInvalidContentValid() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc107TestDescriptionInvalidContentValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc108TestDescriptionInvalidContentValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc109TestDescriptionInvalidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc110TestDescriptionInvalidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc111TestDescriptionInvalidContentValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc112TestDescriptionInvalidContentValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc113TestDescriptionInvalidContentValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc114TestDescriptionValid() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc115TestDescriptionValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc116TestDescriptionValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc117TestDescriptionValidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc118TestDescriptionValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc119TestDescriptionValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc120TestDescriptionValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc121TestDescriptionValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc122TestDescriptionValidTagsValid() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc123TestDescriptionValidTagsValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc124TestDescriptionValidTagsValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc125TestDescriptionValidTagsValidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc126TestDescriptionValidCategoryTagsValidSelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc127TestDescriptionValidTagsValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc128TestDescriptionValidTagsValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc129TestDescriptionValidTagsValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc130TestDescriptionValidContentValid() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc131TestDescriptionValidContentValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc132TestDescriptionValidContentValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc133TestDescriptionValidContentValidCategorySelectPhotoEntered() {
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.titleInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc134TestDescriptionValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.titleInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc135TestDescriptionValidContentValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc136TestDescriptionValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc137TestDescriptionValidContentValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc138TestTagsValid() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc139TestTagsValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc140TestTagsValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc141TestTagsValidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc142TestTagsValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc143TestTagsValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc144TestTagsValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc145TestTagsValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc146TestTagsValidContentValid() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc147TestTagsValidContentValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc148TestTagsValidContentValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc149TestTagsValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc150TestTagsValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc151TestTagsValidContentValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc152TestTagsValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc153TestTagsValidContentValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
	}

	@Test
	public void tc154TestContentValid() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc155TestContentValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc156TestContentValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc157TestContentValidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc158TestContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc159TestContentValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc160TestContentValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc161TestContentValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc162TestTitleInvalidDescriptionInvalidTagsValid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc163TestTitleInvalidDescriptionInvalidTagsValidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc164TestTitleInvalidDescriptionInvalidTagsValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc165TestTitleInvalidDescriptionInvalidTagsValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc166TestTitleInvalidDescriptionInvalidTagsValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc167TestTitleInvalidDescriptionInvalidTagsValidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc168TestTitleInvalidDescriptionInvalidTagsValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc169TestTitleInvalidDescriptionInvalidTagsValidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc170TestTitleInvalidDescriptionValidTagsValid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc171TestTitleInvalidDescriptionValidTagsValidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc172TestTitleInvalidDescriptionValidTagsValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc173TestTitleInvalidDescriptionValidTagsValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc174TestTitleInvalidDescriptionValidTagsValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc175TestTitleInvalidDescriptionValidTagsValidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc176TestTitleInvalidDescriptionValidTagsValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc177TestTitleInvalidDescriptionValidTagsValidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc178TestTitleInvalidDescriptionInvalidTagsValidContentValid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc179TestTitleInvalidDescriptionInvalidTagsValidContentValidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc180TestTitleInvalidDescriptionInvalidTagsValidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc181TestTitleInvalidDescriptionInvalidTagsValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc182TestTitleInvalidDescriptionInvalidTagsValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc183TestTitleInvalidDescriptionInvalidTagsValidContentValidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc184TestTitleInvalidDescriptionInvalidTagsValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc185TestTitleInvalidDescriptionInvalidTagsValidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc186TestTitleInvalidDescriptionValidTagsValidContentValid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
	}

	@Test
	public void tc187TestTitleInvalidDescriptionValidTagsValidContentValidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
	}

	@Test
	public void tc188TestTitleInvalidDescriptionValidTagsValidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
	}

	@Test
	public void tc189TestTitleInvalidDescriptionValidTagsValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
	}

	@Test
	public void tc190TestTitleInvalidDescriptionValidTagsValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
	}

	@Test
	public void tc191TestTitleInvalidDescriptionValidTagsValidContentValidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
	}

	@Test
	public void tc192TestTitleInvalidDescriptionValidTagsValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
	}

	@Test
	public void tc193TestTitleInvalidDescriptionValidTagsValidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
	}

	@Test
	public void tc194TestTitleInvalidDescriptionInvalidContentValid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc195TestTitleInvalidDescriptionInvalidContentValidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc196TestTitleInvalidDescriptionInvalidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc197TestTitleInvalidDescriptionInvalidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc198TestTitleInvalidDescriptionInvalidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc199TestTitleInvalidDescriptionInvalidContentValidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc200TestTitleInvalidDescriptionInvalidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc201TestTitleInvalidDescriptionInvalidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc202TestTitleInvalidDescriptionValidContentValid() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc203TestTitleInvalidDescriptionValidContentValidCategorySelect() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc204TestTitleInvalidDescriptionValidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc205TestTitleInvalidDescriptionValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc206TestTitleInvalidDescriptionValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc207TestTitleInvalidDescriptionValidContentValidImportantYes() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc208TestTitleInvalidDescriptionValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc209TestTitleInvalidDescriptionValidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_INVALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "Please enter at least 20 characters.",
				"Bad error string on Title input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc210TestTitleValidDescriptionInvalidTagsValid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc211TestTitleValidDescriptionInvalidTagsValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc212TestTitleValidDescriptionInvalidTagsValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc213TestTitleValidDescriptionInvalidTagsValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc214TestTitleValidDescriptionInvalidTagsValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc215TestTitleValidDescriptionInvalidTagsValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc216TestTitleValidDescriptionInvalidTagsValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc217TestTitleValidDescriptionInvalidTagsValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc218TestTitleValidDescriptionValidTagsValid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc219TestTitleValidDescriptionValidTagsValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc220TestTitleValidDescriptionValidTagsValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc221TestTitleValidDescriptionValidTagsValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc222TestTitleValidDescriptionValidTagsValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc223TestTitleValidDescriptionValidTagsValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc224TestTitleValidDescriptionValidTagsValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc225TestTitleValidDescriptionValidTagsValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput("");
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc226TestTitleValidDescriptionInvalidTagsValidContentValid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc227TestTitleValidDescriptionInvalidTagsValidContentValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc228TestTitleValidDescriptionInvalidTagsValidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc229TestTitleValidDescriptionInvalidTagsValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc230TestTitleValidDescriptionInvalidTagsValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc231TestTitleValidDescriptionInvalidTagsValidContentValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc232TestTitleValidDescriptionInvalidTagsValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc233TestTitleValidDescriptionInvalidTagsValidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc234TestTitleValidDescriptionValidTagsValidContentValid() {
		addPost.titleInput(TITLE_VALID_FOR_DELETE);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.isTitleInList(TITLE_VALID_FOR_DELETE), true);
		assertEquals(addPost.tagConfirm(TAG), true);
	}

	@Test
	public void tc235TestTitleValidDescriptionValidTagsValidContentValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.isTitleInList(TITLE_VALID), true);
		assertEquals(addPost.tagConfirm(TAG), true);
		assertEquals(addPost.categoryConfirm(CATEGORY), true);
	}

	@Test
	public void tc236TestTitleValidDescriptionValidTagsValidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.isTitleInList(TITLE_VALID), true);
		assertEquals(addPost.tagConfirm(TAG), true);
		assertEquals(addPost.categoryConfirm(CATEGORY), true);
		assertEquals(addPost.importantYesConfirm(), true);
	}

	@Test
	public void tc237TestTitleValidDescriptionValidTagsValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.isTitleInList(TITLE_VALID), true);
		assertEquals(addPost.tagConfirm(TAG), true);
		assertEquals(addPost.categoryConfirm(CATEGORY), true);
	}

	@Test
	public void tc238TestTitleValidDescriptionValidTagsValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.isTitleInList(TITLE_VALID), true);
		assertEquals(addPost.tagConfirm(TAG), true);
		assertEquals(addPost.categoryConfirm(CATEGORY), true);
		assertEquals(addPost.importantYesConfirm(), true);
	}

	@Test
	public void tc239TestTitleValidDescriptionValidTagsValidContentValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.isTitleInList(TITLE_VALID), true);
		assertEquals(addPost.tagConfirm(TAG), true);
		assertEquals(addPost.importantYesConfirm(), true);
	}

	@Test
	public void tc240TestTitleValidDescriptionValidTagsValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.isTitleInList(TITLE_VALID), true);
		assertEquals(addPost.tagConfirm(TAG), true);
		assertEquals(addPost.importantYesConfirm(), true);
	}

	@Test
	public void tc241TestTitleValidDescriptionValidTagsValidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.isTitleInList(TITLE_VALID), true);
		assertEquals(addPost.tagConfirm(TAG), true);
	}

	@Test
	public void tc242TestTitleValidDescriptionInvalidContentValid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc243TestTitleValidDescriptionInvalidContentValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc244TestTitleValidDescriptionInvalidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc245TestTitleValidDescriptionInvalidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc246TestTitleValidDescriptionInvalidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc247TestTitleValidDescriptionInvalidContentValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc248TestTitleValidDescriptionInvalidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc249TestTitleValidDescriptionInvalidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc250TestTitleValidDescriptionValidContentValid() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc251TestTitleValidDescriptionValidContentValidCategorySelect() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc252TestTitleValidDescriptionValidContentValidCategorySelectImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc253TestTitleValidDescriptionValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc254TestTitleValidDescriptionValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc255TestTitleValidDescriptionValidContentValidImportantYes() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc256TestTitleValidDescriptionValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc257TestTitleValidDescriptionValidContentValidPhotoEntered() {
		addPost.titleInput(TITLE_VALID);
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
	}

	@Test
	public void tc258TestDescriptionInvalidTagsValidContentValid() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc259TestDescriptionInvalidTagsValidContentValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc260TestDescriptionInvalidTagsValidContentValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc261TestDescriptionInvalidTagsValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc262TestDescriptionInvalidTagsValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc263TestDescriptionInvalidTagsValidContentValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc264TestDescriptionInvalidTagsValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc265TestDescriptionInvalidTagsValidContentValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_INVALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "Please enter at least 50 characters.",
				"Bad error string on Description input");
	}

	@Test
	public void tc266TestDescriptionValidTagsValidContentValid() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
	}

	@Test
	public void tc267TestDescriptionValidTagsValidContentValidCategorySelect() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
	}

	@Test
	public void tc268TestDescriptionValidTagsValidContentValidCategorySelectImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
	}

	@Test
	public void tc269TestDescriptionValidTagsValidContentValidCategorySelectPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
	}

	@Test
	public void tc270TestDescriptionValidTagsValidContentValidCategorySelectImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.categoryInput(CATEGORY);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
	}

	@Test
	public void tc271TestDescriptionValidTagsValidContentValidImportantYes() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.contentInput(CONTENT);
		addPost.tagsClick(TAG);
		addPost.importantYes();
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
	}

	@Test
	public void tc272TestDescriptionValidTagsValidContentValidImportantYesPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.importantYes();
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
	}

	@Test
	public void tc273TestDescriptionValidTagsValidContentValidPhotoEntered() {
		addPost.titleInput("");
		addPost.descriptionInput(DESCRIPTION_VALID);
		addPost.tagsClick(TAG);
		addPost.contentInput(CONTENT);
		addPost.photoInput(PHOTO);
		addPost.addButton();

		assertEquals(addPost.photoInputError(), false);
		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
	}

	@Test
	public void tc274TestCategorySelect() {
		addPost.categoryInput(CATEGORY);
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc275TestImportantYes() {
		addPost.importantYes();
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc276TestPhotoEntered() {
		addPost.photoInput(PHOTO);
		addPost.titleInput("");
		addPost.descriptionInput("");
		addPost.contentInput("");
		addPost.addButton();

		assertEquals(addPost.getTitleInputError(), "This field is required.", "Bad error string on Title input");
		assertEquals(addPost.getDescriptionInputError(), "This field is required.",
				"Bad error string on Description input");
		assertEquals(addPost.getTagsInputError(), "This field is required.", "Bad error string on Tags input");
		assertEquals(addPost.getContentInputError(), "The content field is required.",
				"Bad error string on Content input");
	}

	@Test
	public void tc277TestClickOnCancelButton() {
		addPost.cancelClick();

		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");

	}

	@Test
	public void tc278TestClickOnCubesSchoolLink() {
		addPost.clickOnCubesSchoolLink();

		assertEquals(driver.getCurrentUrl(), "https://cubes.edu.rs/");

	}

	public void checkMenu(String title) {

		addPost.openMenu(title);

		assertEquals(addPost.menuConfirm(title), true);

		addPost.addPostPage();
	}
}
