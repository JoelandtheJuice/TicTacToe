package test.java.is.jatj.tictactoe.game;

import main.java.is.jatj.tictactoe.game.TicTacToeWebUI;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;

public class seleniumTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  TicTacToeWebUI gameTest;


  @Before
  public void setUp() throws Exception {
    gameTest = new TicTacToeWebUI();
    gameTest.start();
    driver = new FirefoxDriver();
    int port = gameTest.port;
    baseUrl = "localhost:" + port;
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSelenium() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("a")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/move/8')]")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/move/1')]")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/move/7')]")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/move/4')]")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/move/5')]")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/move/2')]")).click();
    driver.get(baseUrl + "/");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
