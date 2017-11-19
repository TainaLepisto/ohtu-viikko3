package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/tainalep/ohtu/chromedriver");

        driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        alkuperainen();
        kirjautuminenEpaonnistuu();
        virheellinenTunnus();
        luoUusiTunnus();
        kirjauduUlos();

        driver.quit();
    }

    private static void alkuperainen() {

        sleep(2);

        WebElement element = driver.findElement(By.name("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        element = driver.findElement(By.name("logout"));
        element.click();

    }

    //epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
    private static void kirjautuminenEpaonnistuu() {

        sleep(2);

        WebElement element = driver.findElement(By.name("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vaara");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        element = driver.findElement(By.name("backHome"));
        element.click();

    }

//epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus
    private static void virheellinenTunnus() {

        sleep(2);

        WebElement element = driver.findElement(By.name("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("eiooe");
        element = driver.findElement(By.name("password"));
        element.sendKeys("virheellinen");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        element = driver.findElement(By.name("backHome"));
        element.click();

    }

//uuden käyttäjätunnuksen luominen
    private static void luoUusiTunnus() {

        Random r = new Random();

        sleep(2);

        WebElement element = driver.findElement(By.name("register"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkepIsuu8");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akkepIsuu8");

        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(3);

        element = driver.findElement(By.name("main"));
        element.click();
        sleep(3);

    }

//uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
    private static void kirjauduUlos() {

        sleep(2);

        WebElement element = driver.findElement(By.name("logout"));
        element.click();

        sleep(3);

    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
