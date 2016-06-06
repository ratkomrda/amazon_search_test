package amazonPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class HomePage {

	// Define Home Page Logo
	@FindBy(how = How.XPATH, using = "//div[@id='nav-logo']//span[contains(text(),'Amazon')]")
	private WebElement homePageLogo;
	
	
	// Define Home Page Search Box
	@FindBy(how = How.ID, using = "twotabsearchtextbox")
	private WebElement searchBoxField;

	// Define Search Submit Button
	@FindBy(how = How.CLASS_NAME , using = "nav-input")
	private WebElement searchSubmitButton;
	
	//Verify Home Page is loaded
	public boolean VerifyHomePageGetsLoaded()
	{		
		return homePageLogo.isDisplayed();
	}
	
	// Define Search Term Enter Method
	public void EnterSearchCriteria(String searchterm) {
		searchBoxField.sendKeys(searchterm);
	}
	
	//Define Search Button Click Method
	public void ClickSearchButton()
	{
		searchSubmitButton.click();
	}
	

    
    

	
	
}
