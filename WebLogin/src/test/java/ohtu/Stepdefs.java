package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.Before;

import io.github.bonigarcia.wdm.ChromeDriverManager;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    WebDriver driver;
    String baseUrl = "http://localhost:4567";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/tainalep/ohtu/chromedriver");

        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        // en vaan millään saanut tätä toimimaan 
        //driver = new HtmlUnitDriver(true);

    }

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        //WebElement element = driver.findElement(By.name("login"));
        //element.click();

        clickLinkWithName("login");
    }

    @Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        //WebElement element = driver.findElement(By.name("register"));
        //element.click();

        clickLinkWithName("register");

    }

    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void new_user_created(String username, String password) throws Throwable {
        driver.get(baseUrl);
        // WebElement element = driver.findElement(By.name("register"));
        //element.click();
        clickLinkWithName("register");

        createNewUser(username, password);
        pageHasContent("Welcome to Ohtu Application!");
        //element = driver.findElement(By.name("main"));
        //element.click();
        clickLinkWithName("main");

        //element = driver.findElement(By.name("logout"));
        //element.click();
        clickLinkWithName("logout");

    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void new_user_creation_failed(String username, String password) throws Throwable {
        driver.get(baseUrl);
        //WebElement element = driver.findElement(By.name("register"));
        //element.click();
        clickLinkWithName("register");

        createNewUser(username, password);
        pageHasContent("Create username and give password");
        //element = driver.findElement(By.name("backHome"));
        //element.click();

        clickLinkWithName("backHome");
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^incorrect username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void incorrrect_username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void new_userinfo_valid_username_and_valid_password_are_given(String username, String password) throws Throwable {
        createNewUser(username, password);
    }

    @When("^too short username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void new_userinfo_too_short_username_and_valid_password_are_given(String username, String password) throws Throwable {
        createNewUser(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and too short password \"([^\"]*)\" and matching password confirmation are entered$")
    public void new_userinfo_valid_username_and_too_short_password_are_given(String username, String password) throws Throwable {
        createNewUser(username, password);
    }

    @When("^a taken username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void new_userinfo_used_username_and_password_are_given(String username, String password) throws Throwable {
        createNewUser(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" but not matching password \"([^\"]*)\" confirmation are entered$")
    public void new_userinfo_username_and_different_passwords_are_given(String username, String password, String passwordConf) throws Throwable {
        createNewUser(username, password, passwordConf);
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void new_user_is_not_created(String errorMessage) throws Throwable {
        pageHasContent(errorMessage);
    }

    @Then("^a new user is created$")
    public void new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createNewUser(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void createNewUser(String username, String password, String passwordConf) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConf);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void clickLinkWithName(String text) {
        int trials = 0;
        while (trials++ < 5) {
            try {
                WebElement element = driver.findElement(By.name(text));
                element.click();
                break;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                sleep(2);

            }
        }
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }

}
