package com.nvg.anibis.testcases.nativeapp;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.nvg.anibis.base.nativeapp.TestBase;
import com.nvg.anibis.screens.nativeapp.pagefactory.SearchPage;
import com.nvg.anibis.screens.nativeapp.pagefactory.SearchResult;
import com.nvg.anibis.swaggerapi.SearchController;
import com.nvg.anibis.utils.CommonUtils;
import io.swagger.client.ApiException;
import io.swagger.client.model.SearchResultDto;

public class SearchTest extends TestBase {
	@BeforeTest
	// Go to Search Tab
	public void goToSearchScreen() throws InterruptedException{	

	}
	
	/**
	 * Verify that the results of Keyword search are exactly the same as on Swagger API.
	 * @throws ApiException *
	 */
	// Search with keyword include only one word: "GTA"
	@Test
	public void searchKeyWordOneWord() throws InterruptedException, ApiException{
		SearchPage sp = new SearchPage(driver, CommonUtils.platform);
		SearchResult sr = new SearchResult(driver, CommonUtils.platform);
		// Do the search on mobile app
		sp.search("GTA");
		// Do the search on Api 
		SearchResultDto srApi = SearchController.apiSearch("GTA");
		// Get the number of the result from api
		System.out.println("Total records searched by API swagger:"+ srApi.getTotalCount());
		// Compare the Total of Results
		if (sr.getNumberOfResults()==srApi.getTotalCount()){
			System.out.println("successful");
		}
		else
			{
			System.out.println("fail");
			}
	}

}
