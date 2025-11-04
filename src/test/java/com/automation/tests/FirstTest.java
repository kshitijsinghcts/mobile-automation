package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void simpleSearchTest() {
        // initialize the Page Object
        SearchPage searchPage = new SearchPage(getDriver());

        // perform actions 
        searchPage.searchFor("BrowserStack");

        // assertions
        Assert.assertTrue(searchPage.areResultsDisplayed(), "Search results were not displayed.");
        
        String firstResult = searchPage.getFirstResultText();
        Assert.assertNotNull(firstResult);
        Assert.assertTrue(firstResult.toLowerCase().contains("browserstack"), 
                "First result did not contain 'browserstack'. Found: " + firstResult);
    }
}