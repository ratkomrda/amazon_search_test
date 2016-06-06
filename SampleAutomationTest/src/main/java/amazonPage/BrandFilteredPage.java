package amazonPage;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import testSuite.BaseTest;

public class BrandFilteredPage{
	
	// Define Brand Filtered Page result list
	@FindBy(how = How.XPATH, using = "//ul[@id='s-results-list-atf']/li//div[@class='a-row a-spacing-mini']")
	private List<WebElement> brandPageItemList;
	
	// Define Product Page description
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Product Description')]")
	private WebElement productDescription;
	
	
	// Verify search criteria is displayed almost for one item in the filtered
	// brand page list
	public boolean VerifySeachCriteriaIsDisplayedInFilteredBrandPage() throws Exception {
		
		int count = 0;

		for (WebElement we : brandPageItemList) {
		System.out.println("THE TEXT IN THE first list tab ::: "+ we.getText());
			if (we.getText().contains("Nine West"))
			{
				count++;
				break;
			}
		}

		if (count > 0) {
			System.out.println("criteria was found in the search result");
			return true;
		} else {
			System.out.println("criteria was not found in the search result");
			return false;
		}

	}
	
	
	// View any of the items displayed in the searched result, wait till the page loads
	public boolean viewAnyProductListedInBrandFilteredPage() throws Exception {

		boolean value = false;
		
		for (WebElement we : brandPageItemList) {

		if (we.getText().contains("Nine West")) 
		{
			we.click();
			//Wait until the page loads...
			//waitForVisibilityOfElement(productDescription, "The product page did not appear after clicking the item", 10);
			Assert.assertTrue(productDescription.isDisplayed());
				value = true;
				System.out.println("Product Page was correctly loaded");
				break;
			}else
			{
				System.out.println("Product Page was not correctly loaded");
			}
		}

		return value;
	}
}
