package testSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;


import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class BaseTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider{
	
	protected ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	
	SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(System.getenv("usrname"), System.getenv("accesskey"));
	
	public WebDriver getDriver() {
		return  driver.get();
	}

	@Parameters({ "browser", "version", "platform", "jobName" })
	@BeforeTest
	public void beforeTest(String browserName, String version, String platform, String jobName) throws MalformedURLException {
		// Running the test on Sauce by creating the remote web driver
		DesiredCapabilities capability = new DesiredCapabilities();
		if (browserName != null)
			capability.setCapability(CapabilityType.BROWSER_NAME, browserName);
		if (version != null)
			capability.setCapability(CapabilityType.VERSION, version);
		if (platform != null)
			capability.setCapability(CapabilityType.PLATFORM, platform);
		if (jobName != null)
			capability.setCapability("name", jobName);
		//Use your own sauce user name and API access key
		driver.set(new RemoteWebDriver(new URL("http://"+System.getenv("usrname")+":"+System.getenv("accesskey")+"@ondemand.saucelabs.com:80/wd/hub"), capability));
		driver.get().navigate().to("http://www.amazon.com");
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterTest() {
		driver.get().close();
		driver.get().quit();

	}

	public SauceOnDemandAuthentication getAuthentication() {
		// TODO Auto-generated method stub
		return authentication;
	}

	public String getSessionId() {
		// TODO Auto-generated method stub
		return ((RemoteWebDriver)getDriver()).getSessionId().toString();
	}
}
