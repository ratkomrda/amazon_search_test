package amazonPage;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchResultPage {

	// Define Search Result Page result list
	@FindBy(how = How.XPATH, using = "//ul[@id='s-results-list-atf']/li")
	private List<WebElement> resultPageSearchList;

	// Define Search Result Page Brand List
	@FindBy(how = How.XPATH, using = "//ul[@id='ref_2528832011']/li")
	private List<WebElement> resultPageBrandhList;

	// Define Search Result Page Brand list Item
	@FindBy(how = How.XPATH, using = "//ul[@id='ref_2528832011']/li[@class='refinementImage']/a")
	private WebElement brandListItem;
	
	// Define Search Result Page Brand list See More Item
	@FindBy(how = How.XPATH, using = "//ul[@id='ref_2528832011']//span[@class='refinementLink seeMore']")
	private WebElement seeMoreLink;
	
	// Define Search Result Page Brand list alphabetical sort link
	@FindBy(how = How.XPATH, using = "//span[@class='pagnLink']/a[contains(text(),'N')]")
	private WebElement brandAlphabeticalLink;
	
	// Define Search Result Page Brand item
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Nine West")
	private WebElement itemLink;

	// Verify search criteria is displayed almost for one item in the search
	// result list
	public boolean VerifySeachCriteriaIsDisplayed() throws Exception {
		int count = 0;

		for (WebElement we : resultPageSearchList) {

			if (we.getText().contains("Shoe")) {
				count++;
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

	// Filter Search List By Brand
	public boolean clickOnListedBrand() throws Exception {

		boolean value = false;

		for (WebElement we : resultPageBrandhList) {

			if (we.getText().contains("Nine West")) 
			{
				itemLink.click();
				System.out.println("Item was found in the list");
				value = true;
				break;
			}
			else 
			{
				System.out.println("Item was not found in the list");
			}

		}
		return value;

	}
	
	public void lookForBrandListed() throws Exception {
		
		if (this.clickOnListedBrand() == false)
		{
			seeMoreLink.click();
			brandAlphabeticalLink.click();
			itemLink.click();
			System.out.println("Brand was found and clicked");
			
		} else {
			System.out.println("Brand was previously found and clicked");
		}
	}
}
