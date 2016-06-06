package testSuite;



import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import amazonPage.BrandFilteredPage;
import amazonPage.HomePage;
import amazonPage.SearchResultPage;

public class AmazonTest extends BaseTest 
{


	/*This method searches for a term in amazon.com and returns results
	*/
	@Test
	public void amazonSearchTest() throws Exception 
	{
		//Initialize Home Page
		HomePage homePage=PageFactory.initElements(getDriver(), HomePage.class);
		
		//Verify Home Page is loaded
		Assert.assertTrue(homePage.VerifyHomePageGetsLoaded());		
		
		//Enter user name
		homePage.EnterSearchCriteria("Shoes");
		
		//Click on Search Button
		homePage.ClickSearchButton();
		
		//Initialize Search Result Page
		SearchResultPage searchResultPage=PageFactory.initElements(getDriver(), SearchResultPage.class);
		
		//Verify search criteria is displayed in atmost one product in the search result page list
		Assert.assertTrue(searchResultPage.VerifySeachCriteriaIsDisplayed());
		
		//Look for brand and click on it
		searchResultPage.lookForBrandListed();
		
		//Initialize Brand Filtered Page
		BrandFilteredPage brandFilteredPage=PageFactory.initElements(getDriver(), BrandFilteredPage.class);
		
		//Verify search criteria is displayed in atmost one product in the search result page list
		Assert.assertTrue(brandFilteredPage.VerifySeachCriteriaIsDisplayedInFilteredBrandPage());
		
		//Verify user is able to click on any product listed and the page is correctly loaded
		Assert.assertTrue(brandFilteredPage.viewAnyProductListedInBrandFilteredPage());	
		
	}



}
