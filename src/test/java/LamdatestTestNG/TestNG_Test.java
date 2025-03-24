package LamdatestTestNG;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNG_Test {

	private WebDriver driver;
	private String browserName;
	private String browserVersion;
	private String Platform;
	private String url;
	@Parameters({"browser", "browserVersion", "platform", "url"})
    @BeforeMethod
    public void setUp(String browser, String browserVer, String platform, String url) throws MalformedURLException {
        this.browserName = browser;
        this.browserVersion = browserVer;
        this.Platform = platform;
        this.url = url;
        DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName",browserName);
		capabilities.setCapability("browserVersion", browserVersion);
		capabilities.setCapability("platformName", Platform);
		HashMap<String, Object> ltoption1 = new HashMap<String, Object>();
		ltoption1.put("username", "samarjitkundu99");
		ltoption1.put("accessKey", "LT_Fl6EVG8YdtqArLvzkp8RKtSzVHf72PPjQyzIs03x8hYmoWP");
		ltoption1.put("visual", true);
		ltoption1.put("video", true);
		ltoption1.put("network", true);
		ltoption1.put("build", "LambdaTestNG_01");
		ltoption1.put("project", "LambdaTestNG_01");
		ltoption1.put("name", "TestNG");
		ltoption1.put("console", "true");
		ltoption1.put("w3c", true);
		ltoption1.put("plugin", "java-testNG");
		capabilities.setCapability("LT:Options", ltoption1);	
		driver=new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test(priority = 1)
    public void testPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        SoftAssert softAssert = new SoftAssert();
        String actualTitle = driver.getTitle();
        String expectedTitle = "Selenium Grid Online | Run Selenium Test On Cloud";
        softAssert.assertEquals(actualTitle, expectedTitle, "Page title mismatch!");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testCheckboxDemo() {
        driver.findElement(By.linkText("Checkbox Demo")).click();

        WebElement singleCheckbox = driver.findElement(By.id("isAgeSelected"));

        singleCheckbox.click();
        Assert.assertTrue(singleCheckbox.isSelected(), "Checkbox should be selected.");

        singleCheckbox.click();
        Assert.assertFalse(singleCheckbox.isSelected(), "Checkbox should be unselected.");
    }

    @Test(priority = 3)
    public void testJavascriptAlert() {
        driver.findElement(By.linkText("Javascript Alerts")).click();
        driver.findElement(By.xpath("//button[text()='Click Me']")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        
        Assert.assertEquals(alertText, "I am an alert box!", "Alert text mismatch.");
        alert.accept(); // Click OK
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        
    }

}
